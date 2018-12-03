package thread;

import org.dom4j.io.SAXReader;

/**
 * Created by ding on 2018/10/12.
 */
public class Demo03 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().hashCode()+" = ");
        B b = new B();
        b.show();

    }
}

class B_Father{

    private String key = "B_Father";
    public B_Father() {
        SAXReader saxReader = new SAXReader();
        System.out.println("B_Father : "+ saxReader );
        key = "B_Father11";
    }

    public void show(){
//        System.out.println(Thread.currentThread().hashCode()+" = "+this);
        System.out.println("show key  " + this.key +" "+ this.key.hashCode());
    }
}

class B extends B_Father{
    private String key = "B";
    public B() {
        SAXReader saxReader = new SAXReader();
        System.out.println("B : "+ saxReader );
//        this.show();
//        System.out.println(key.hashCode());
        super.show();
    }
    public void show(){
//        System.out.println(Thread.currentThread().hashCode()+" = "+this);
        System.out.println("show key  " + this.key +" "+ this.key.hashCode());
    }
}