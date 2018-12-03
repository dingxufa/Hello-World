package hadoop.join;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
public class JoinMapper extends Mapper<LongWritable, Text, Text, Text>{
    public static final String LEFT_FILENAME = "student_info.txt";
    public static final String RIGHT_FILENAME = "student_class_info.txt";
    public static final String LEFT_FILENAME_FLAG = "l";
    public static final String RIGHT_FILENAME_FLAG = "r";
    protected void map(LongWritable key, Text value,Context context) throws
            IOException ,InterruptedException {
        //获取记录的 HDFS 路径
        String filePath = ((FileSplit) context.getInputSplit()).getPath().toString();
        System.out.println(filePath+"==============="+value.toString());
        String fileFlag = null;
        String joinKey = null;
        String joinValue = null;
        //判断记录来自哪个文件
        if(filePath.contains(LEFT_FILENAME)){
            fileFlag = LEFT_FILENAME_FLAG;
            joinKey = value.toString().split(",")[1];
            joinValue = value.toString().split(",")[0];
        }else if(filePath.contains(RIGHT_FILENAME)){
            fileFlag = RIGHT_FILENAME_FLAG;
            joinKey = value.toString().split(",")[0];
            joinValue = value.toString().split(",")[1];
        }
        //输出键值对并标示该结果是来自于哪个文件
        System.out.println("key="+joinKey+" value="+joinValue);
        context.write(new Text(joinKey), new Text(joinValue + "," + fileFlag));
    };
}
