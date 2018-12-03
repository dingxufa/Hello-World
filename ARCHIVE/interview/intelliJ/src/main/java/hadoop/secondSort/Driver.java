package hadoop.secondSort;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
public class Driver {
    public static void main(String[] args) throws IOException, ClassNotFoundException,
            InterruptedException {
        // TODO Auto-generated method stub
        Configuration conf = new Configuration();
        Job job = new Job(conf, "secondarysort");
        job.setJarByClass(Driver.class);

        job.setMapperClass(SeconadryMapper.class);
        job.setReducerClass(SecondaryReducer.class);

        job.setPartitionerClass(KeyPartitioner.class);
        job.setSortComparatorClass(SortComparator.class);
        job.setGroupingComparatorClass(GroupingComparator.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        job.setGroupingComparatorClass(GroupingComparator.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //需要将 Reducer 的个数强制设定为 1
        job.setNumReduceTasks(1);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
