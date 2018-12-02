package hadoop;

import java.util.StringTokenizer;

/**
 * Created by ding on 2018/4/22.
 */
public class StringToken {
    public static void main(String[] args){
        String str = "100|66,55:200|567,90:102|43,54";

        StringTokenizer strToke = new StringTokenizer(str, ":,|");// 默认不打印分隔符
// StringTokenizer strToke=new StringTokenizer(str,":,|",true);//打印分隔符
// StringTokenizer strToke=new StringTokenizer(str,":,|",false);//不打印分隔符
        while(strToke.hasMoreTokens()){
            System.out.print(strToke.nextToken() + " ");//100 66 55 200 567 90 102 43 54
        }
    }
}
