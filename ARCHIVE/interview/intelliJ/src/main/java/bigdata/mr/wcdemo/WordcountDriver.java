package bigdata.mr.wcdemo;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordcountDriver {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		/*conf.set("mapreduce.framework.name", "local");
		conf.set("fs.defaultFS", "file:///");*/
		
		Job job = Job.getInstance(conf);
		job.setJarByClass(WordcountDriver.class);
		
		job.setMapperClass(WordcountMapper.class);
		job.setReducerClass(WordcountReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.setInputPaths(job,new Path("D:/download/local/wordcount/input/wordcount.txt"));
		FileOutputFormat.setOutputPath(job, new Path("D:/download/local/wordcount/output/"));
		
		boolean res = job.waitForCompletion(true);
		//job.submit();
		//System.err.println(res?0:1);
	}
}
