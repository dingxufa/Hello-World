package hive;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
public class ConcatUDF extends UDF{
    private Text text = new Text();
    public Text evaluate(Text str1,Text str2){
        if(str1 == null || str2 == null){
            text.set("unvalid");
            return text;
        }
        text.set(String.valueOf(str1.toString().charAt(0))
                + String.valueOf(str2.toString().charAt(str2.getLength() - 1)));
        return text;
    }
}