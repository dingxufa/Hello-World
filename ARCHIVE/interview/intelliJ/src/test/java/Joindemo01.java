/**
 * join:合并线程
 * @author Administrator
 *
 */
public class Joindemo01 extends Thread {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Joindemo01 demo = new Joindemo01();
        Thread t = new Thread(demo); //新生
        t.start();//就绪
        //cpu调度 运行


        for(int i=0;i<1000;i++){
            if(50==i){
                t.join(); //main阻塞...
            }
            System.out.println("main...."+i);
        }
    }

    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            System.out.println("join...."+i);
        }
    }

}
