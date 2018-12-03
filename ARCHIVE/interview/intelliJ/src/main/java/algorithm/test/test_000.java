package algorithm.test;

import java.io.IOException;

/**
 * Created by ding on 2018/5/3.
 */
public class test_000 {
    public static void swap(Point p1,Point p2){
        Point tmp = p1;
        p1 = p2;
        p2 =tmp;
        System.out.println("in swap: p1 is "+ p1+"  p2 is:"+p2);
    }

    public static void main(String[] args){
        Point pt1 = new Point(0, 0);
        Point pt2 = new Point(10, 10);
        swap(pt1,pt2);
        System.out.println("in swap: pt1 is "+ pt1+"  pt2 is:"+pt2);
        try {
            Runtime.getRuntime().exec("calc");
            System.out.println("====");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

}

class Point{
    private int x;
    private int y;
    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x="+this.x+" "+this.y;
    }
}
