package com.beifeng.shuffle;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import sun.rmi.rmic.IndentingWriter;

/**
 * 主类
 * 
 * @author gerry
 *
 */
public class DemoRunner {
	/**
	 * 处理mapper类
	 * @author gerry
	 *
	 */
	static class DemoMapper extends Mapper<Object, Text, IntPair, IntWritable> {
		@Override
		protected void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] strs = line.split(" ");
			if (strs.length == 2) {
				int first = Integer.valueOf(strs[0]);
				int second = Integer.valueOf(strs[1]);
                System.out.println("first="+first+" second="+second);
                context.write(new IntPair(first, second), new IntWritable(second));
			} else {
				System.err.println("数据异常:" + line);
			}
		}
	}

	/**
	 * 自定义的实现reducer
	 * @author gerry
	 *
	 */
	static class DemoReducer extends Reducer<IntPair, IntWritable, IntWritable, Text> {
		@Override
		protected void reduce(IntPair key, Iterable<IntWritable> values, Context context)
						throws IOException, InterruptedException {
			Integer preKey = key.getFirst();
			StringBuffer sb = new StringBuffer();

			for (IntWritable value : values) {
				int curKey = key.getFirst();
				if (preKey == curKey) {
					// 表示是同一个key，但是value是不一样的或者是value是排序号的
					sb.append(value.get()).append(",");
				} else {
					// 表示是新的一个key，先输出旧的key对应的value信息，然后修改key值和stringbuffer的值
					context.write(new IntWritable(preKey), new Text(sb.toString()));
					preKey = curKey;
					sb = new StringBuffer();
					sb.append(value.get()).append(",");
				}
			}

			// 输出最后的结果信息
			context.write(new IntWritable(preKey), new Text(sb.toString()));
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		//conf.set("fs.defaultFS", "hdfs://192.168.100.120");
        conf.set("mapreduce.framework.name", "local");
        conf.set("fs.defaultFS", "file:///");

		Job job = Job.getInstance(conf, "demo-job");
		job.setJarByClass(DemoRunner.class);
		job.setMapperClass(DemoMapper.class);
		job.setReducerClass(DemoReducer.class);

		job.setMapOutputKeyClass(IntPair.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);

		// group by class
		job.setGroupingComparatorClass(IntPairGrouping.class);
		// 设置partitioner，要求reducer个数必须大于1
		job.setPartitionerClass(IntPairPartitioner.class);
		job.setNumReduceTasks(2);
	
		// 输入输出路径
		FileInputFormat.addInputPaths(job, "src/main/resources/beifeng/input");
		FileOutputFormat.setOutputPath(job, new Path("src/main/resources/beifeng/output/" + System.currentTimeMillis()));

		// 提交
		job.waitForCompletion(true);
		
	}
}
