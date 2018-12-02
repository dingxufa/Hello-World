

public class YieldDemo01 extends Thread {

    /**
     * @param args
     */
    public static void main(String[] args) {
        YieldDemo01 demo = new YieldDemo01();
        Thread t = new Thread(demo); //新生
        t.start();//就绪
        //cpu调度 运行


        for(int i=0;i<1000;i++){
            if(i%20==0){
                //暂停本线程 main
                Thread.yield();  //并不是严格的暂停，如果暂停后CPU又调度到了它还是会执行main
                System.out.println("main yield=================");
            }
            System.out.println("main...."+i);
        }
    }

    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            System.out.println("yield...."+i);
        }
    }

}
