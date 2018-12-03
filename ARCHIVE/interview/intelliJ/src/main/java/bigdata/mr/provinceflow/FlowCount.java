package bigdata.mr.provinceflow;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class FlowCount {
	
	static class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean>{
		
		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			 
			String line = value.toString();
			String[] fields = line.split("\t");
			String phoneNbr = fields[1];
			long upFlow = Long.parseLong(fields[fields.length-3]);
			long dFlow = Long.parseLong(fields[fields.length-2]);
			
			context.write(new Text(phoneNbr), new FlowBean(upFlow, dFlow));
			
			
		}
		
		
		
	}
	
	
	static class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean>{
		
		//<183323,bean1><183323,bean2><183323,bean3><183323,bean4>.......
		@Override
		protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

			long sum_upFlow = 0;
			long sum_dFlow = 0;
			
			for(FlowBean bean: values){
				sum_upFlow += bean.getUpFlow();
				sum_dFlow += bean.getdFlow();
			}
			
			FlowBean resultBean = new FlowBean(sum_upFlow, sum_dFlow);
			context.write(key, resultBean);
			
			
		}
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		/*conf.set("mapreduce.framework.name", "yarn");
		conf.set("yarn.resoucemanager.hostname", "mini1");*/
		Job job = Job.getInstance(conf);
		
		/*job.setJar("/home/hadoop/wc.jar");*/
		//ָ���������jar�����ڵı���·��
		job.setJarByClass(FlowCount.class);
		
		//ָ����ҵ��jobҪʹ�õ�mapper/Reducerҵ����
		job.setMapperClass(FlowCountMapper.class);
		job.setReducerClass(FlowCountReducer.class);
		
		//ָ�������Զ�������ݷ�����
		job.setPartitionerClass(ProvincePartitioner.class);
		//ͬʱָ����Ӧ��������������reducetask
		job.setNumReduceTasks(5);
		
		//ָ��mapper������ݵ�kv����
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FlowBean.class);
		
		//ָ��������������ݵ�kv����
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);
		
		//ָ��job������ԭʼ�ļ�����Ŀ¼
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		//ָ��job������������Ŀ¼
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//��job�����õ���ز������Լ�job���õ�java�����ڵ�jar�����ύ��yarnȥ����
		/*job.submit();*/
		boolean res = job.waitForCompletion(true);
		System.exit(res?0:1);
		
	}
	

}
