package hadoop.join_2;



import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

/**
 * @ProjectName JoinDemo
 * @PackageName com.buaa
 * @ClassName TextPair
 * @Description TODO
 * @Author 刘吉超
 * @Date 2016-05-24 22:54:05
 */
public class TextPair implements WritableComparable<TextPair>{
    // Text类型的实例变量first
    private Text first;
    // Text类型的实例变量second
    private Text second;

    public TextPair(){
        set(new Text(),new Text());
    }

    public TextPair(String first,String second){
        set(new Text(first),new Text(second));
    }

    public void set(Text first,Text second){
        this.first = first;
        this.second = second;
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        first.readFields(in);
        second.readFields(in);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        first.write(out);
        second.write(out);
    }

    public int hashCode() {
        return first.hashCode() * 163 + second.hashCode();
    }

    public boolean equals(TextPair tp) {
        return first.equals(tp.first) && second.equals(tp.second);
    }

    public String toStirng() {
        return first + "\t" + second;
    }

    @Override
    public int compareTo(TextPair o) {
        if(!first.equals(o.first)){
            return first.compareTo(o.first);
        }else if(!second.equals(o.second)){
            return second.compareTo(o.second);
        }

        return 0;
    }

    public Text getFirst() {
        return first;
    }

    public void setFirst(Text first) {
        this.first = first;
    }

    public Text getSecond() {
        return second;
    }

    public void setSecond(Text second) {
        this.second = second;
    }
}