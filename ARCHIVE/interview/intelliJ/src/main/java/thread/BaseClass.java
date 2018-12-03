package thread;

/**
 * Created by ding on 2018/10/11.
 */
public class BaseClass
{
    public synchronized void doSomeThing()
    {
        System.out.println("parent class：begin.....doSomeThing");
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("parent class：end.....doSomeThing");
    }


}
