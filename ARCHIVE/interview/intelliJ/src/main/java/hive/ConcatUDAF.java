package hive;
import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;
public class ConcatUDAF extends UDAF{
    public static class ConcatUDAFEvaluator implements UDAFEvaluator{
        String line = "";
        @Override
        public void init() {
        // TODO Auto-generated method stub
            line = "";
        }
        public boolean iterate(String value,String separator){
            if(value != null || separator != null){
                line += value + separator;
                return true;
            }
            line += "";
            return true;
        }
        public String terminate(){
            return line;
        }
        public String terminatePartial(){
            return line;
        }
        public boolean merge(String another){
            return iterate(line, another);
        }
    }
}