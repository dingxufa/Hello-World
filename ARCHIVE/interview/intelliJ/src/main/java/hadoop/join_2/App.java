package hadoop.join_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Hashtable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


import java.io.IOException;

/**
 * Created by ding on 2018/5/7.
 */
public class App extends Configured implements Tool {

    public static class TemperatureMapper extends Mapper {
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] arr = value.toString().split("\t", 2);
            if(arr.length == 2){
                context.write(new Text(arr[0]),value);
            }
        }
    }
    public static class TemperatureReducer extends Reducer<Text, Text, Text, Text> {
        // 定义Hashtable存放缓存数据
        private Hashtable<String,String> table = new Hashtable<String, String>();
        /**
         * 获取分布式缓存文件
         */
        protected void setup(Context context) throws IOException, InterruptedException {
            Path[] localPaths = context.getLocalCacheFiles();
            if(localPaths.length==0){
                throw new FileNotFoundException("Distributed cache file not found.");
            }
            // 获取本地 FileSystem实例
            FileSystem fs = FileSystem.getLocal(context.getConfiguration());
            // 打开输入流
            FSDataInputStream in = fs.open(new Path(localPaths[0].toString()));
            // 创建BufferedReader读取器
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            // 按行读取并解析气象站数据
            String infoAddr = null;
            while ((infoAddr = br.readLine()) != null) {
                String[] records = infoAddr.split("\t");
                // key为stationID，value为stationName
                table.put(records[0], records[1]);
            }
        }
        public void reduce(Text key, Iterable< Text> values, Context context) throws IOException, InterruptedException {
            // 天气记录根据stationId获取stationName
            String stationName = table.get(key.toString());
            for (Text value : values) {
                context.write(new Text(stationName), value);
            }
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        // 读取配置文件
        Configuration conf = new Configuration();
        // 判断路径是否存在，如果存在，则删除
        Path mypath = new Path(args[2]);
        FileSystem hdfs = mypath.getFileSystem(conf);
        if (hdfs.isDirectory(mypath)) {
            hdfs.delete(mypath, true);
        }
        // 获取一个job实例
        Job job = Job.getInstance(conf,"join");
        // 主类
        job.setJarByClass(App.class);
        // 设置record.txt文件作为输入
        FileInputFormat.addInputPath(job, new Path(args[0]));
        // 添加station.txt到分布式缓存
        job.addCacheFile(new URI(args[1]));
        // 输出目录
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        // mapper
        job.setMapperClass(TemperatureMapper.class);
        // reduce
        job.setReducerClass(TemperatureReducer.class);

        // 输出key类型
        job.setOutputKeyClass(Text.class);
        // 输出value类型
        job.setOutputValueClass(Text.class);

        return job.waitForCompletion(true)?0:1;
    }
    public static void main(String[] args) throws Exception{
        String[] args0 = {
                "hdfs://hadoop1:9000/buaa/join/record.txt",
                "hdfs://hadoop1:9000/buaa/join/station.txt",
                "hdfs://hadoop1:9000/buaa/join/out/"
        };
        int ec = ToolRunner.run(new Configuration(), new App(), args0);
        System.exit(ec);
    }

}
