package thread;

public class Demo01 {
    static{
        System.out.println("静态初始化Demo01");
    }
    public static void main(String[] args) {
        System.out.println("Demo01的main方法");
        A a = new A();
//        System.out.println(A.width);
//        A a2 = new A();
		/*
		静态初始化Demo01
		Demo01的main方法
		静态初始化类A_Father
		静态初始化类A
		创建A类对象
		300
		创建A类对象 // 类加载初始化只有一次
		*/
    }
}
class A extends A_Father{
    public static int width=100;
    static{
        System.out.println("静态初始化类A");
        width=300;
    }
    public A(){
        System.out.println("创建A类对象  A init");
        System.out.println(this);
    }
}
class A_Father{
    static{
        System.out.println("静态初始化类A_Father");
    }

    public A_Father(){
        System.out.println("创建A_Father类对象 A_Father  init");
        System.out.println(this);
    }
}