package hadoop.join;

import java.io.File;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Driver {
    public static void main(String[] args) throws IOException, InterruptedException,
            ClassNotFoundException {
        File file = new File("d:/download/data/join/out");
        if(file.exists() && file.isDirectory()){file.delete(); }

        Configuration configuration = new Configuration();
        Job job = new Job(configuration,"MRJoin");
        job = Job.getInstance();
        job.setJarByClass(Driver.class);
        FileInputFormat.addInputPath(job,new Path("file:///d:/download/data/join") ); //new Path(args[0])


        FileOutputFormat.setOutputPath(job, new Path("file:///d:/download/data/join/out"));
        job.setMapperClass(JoinMapper.class);
        job.setReducerClass(JoinReducer.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}