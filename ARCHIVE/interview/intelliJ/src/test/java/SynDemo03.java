/**
 * 过多的同步方法可能造成死锁
 * @author Administrator
 *
 */
public class SynDemo03 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Object g =new Object();
        Object m = new Object();
        Test t1 =new Test(g,m);
        Test2 t2 = new Test2(g,m);
        Thread proxy = new Thread(t1);  // Runable proxy = new Thread(t1); proxy.start()不能调用，多态不能使用新增方法
        //thread实现runnable接口，新增了start方法
        Thread proxy2 = new Thread(t2);
        proxy.start();
        proxy2.start();
    }

}
class Test implements Runnable{
    Object goods ;
    Object money ;

    public Test(Object goods, Object money) {
        super();
        this.goods = goods;
        this.money = money;
    }

    @Override
    public void run() {
        while(true){
            test();
        }
    }

    public void test(){
        synchronized(goods){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(money){

            }

        }
        System.out.println("一手给钱");
    }







}

class Test2  implements Runnable{
    Object goods ;
    Object money ;
    public Test2(Object goods, Object money) {
        super();
        this.goods = goods;
        this.money = money;
    }

    @Override
    public void run() {
        while(true){
            test();
        }
    }

    public void test(){
        synchronized(money){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(goods){

            }

        }
        System.out.println("一手给货");
    }



}



