package bigdata.mr.wcdemo1;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordcountDriver1 {
	public static void main(String[] args) throws Exception {
		System.out.println(Arrays.toString(args));
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		/*conf.set("mapreduece.framework.name", "yarn");
		conf.set("fs.defaultFS", "hdfs://server0:9000/");*/
		Job job = Job.getInstance(conf);
		job.setJarByClass(WordcountDriver1.class);
		
		job.setMapperClass(WordcountMapper.class);
		job.setReducerClass(WordcountReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setCombinerClass(WordcountCombiner.class);
		
		job.setInputFormatClass(CombineTextInputFormat.class);
		CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);
		CombineTextInputFormat.setMinInputSplitSize(job, 2097152);
		
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		
		Path outPath = new Path(args[1]);
		if (fs.exists(outPath)) {
			fs.delete(outPath);
		}
		FileOutputFormat.setOutputPath(job, outPath);
		try {
			
			boolean res = job.waitForCompletion(true);
			//System.out.println(res?"�ɹ�":"ʧ��");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
