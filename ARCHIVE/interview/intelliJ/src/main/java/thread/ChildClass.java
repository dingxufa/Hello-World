package thread;

/**
 * Created by ding on 2018/10/11.
 */
public class ChildClass extends BaseClass
{

    public synchronized void childMethod()
    {
        System.out.println("---child class：begin.....childMethod");
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("---child class：end.....childMethod");

    }


}
