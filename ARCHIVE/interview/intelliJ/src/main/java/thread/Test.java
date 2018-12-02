package thread;

/**
 * Created by ding on 2018/10/11.
 */
public class Test {


    public static void main(String[] args) {
        ChildClass childClass = new ChildClass();
        new DemoThread1(childClass).start();
        new DemoThread2(childClass).start();

        new DemoThread1(childClass).start();
        new DemoThread2(childClass).start();

        new DemoThread1(childClass).start();
        new DemoThread2(childClass).start();

    }

}

class DemoThread1 extends Thread {
    private BaseClass base = null;

    public DemoThread1(BaseClass base) {
        this.base = base;
    }

    @Override
    public void run() {
        base.doSomeThing();
    }

}


class DemoThread2 extends Thread {
    private ChildClass child = null;

    public DemoThread2(ChildClass child) {
        this.child = child;
    }

    @Override
    public void run() {
        child.childMethod();
    }

}
