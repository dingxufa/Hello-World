package algorithm_exercise;



import java.util.HashMap;

/**
 * 自定义自己的HashSet
 * @author Administrator
 *
 */
public class MyHashSet {

    HashMap map;
    private static final Object PRESENT = new Object();

    public MyHashSet(){
        map = new HashMap();
    }

    public int size(){
        return map.size();
    }

    public void add(Object o){
        map.put(o, PRESENT);   //set的不可重复就是利用了map里面键对象的不可重复！
    }

    public static void main(String[] args) {
        MyHashSet s = new MyHashSet();
        s.add("aaa");
        s.add(new String("aaa"));
        System.out.println(s.size()); //1

        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2); // false
    }

}
