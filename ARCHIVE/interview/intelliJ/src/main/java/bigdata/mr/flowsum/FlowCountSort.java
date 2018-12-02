package bigdata.mr.flowsum;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class FlowCountSort {
	static class FlowCountSortMapper extends Mapper<LongWritable, Text, FlowBean, Text>{
		FlowBean bean = null;
		Text v = new Text();
		
		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] fields = line.split("\t");
			String phoneNbr = fields[0];
			long upFlow = Long.parseLong(fields[1]);
			long dFlow = Long.parseLong(fields[1]);
			bean.set(upFlow, dFlow);
			v.set(phoneNbr);
			context.write(bean, v);
		}
		
	}
	
	static class FlowCountSortReducer extends Reducer<FlowBean, Text, Text, FlowBean>{
		@Override
		protected void reduce(FlowBean bean, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			
			context.write(values.iterator().next(), bean);
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("HADOOP_USER_NAME", "hadoop");
		conf.set("mapreduce.framework.name", "yarn");
		
		Job job = Job.getInstance(conf);
		job.setJarByClass(FlowCountSort.class);
		job.setMapperClass(FlowCountSortMapper.class);
		job.setReducerClass(FlowCountSortReducer.class);
		job.setMapOutputKeyClass(FlowBean.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);
		
		Path outPath = new Path(args[1]);
		FileSystem fs = FileSystem.get(conf);
		if (fs.exists(outPath)) {
			fs.delete(outPath,true);
		}
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, outPath);
		
		boolean res = job.waitForCompletion(true);
		System.err.println(res?0:1);
	}
}
