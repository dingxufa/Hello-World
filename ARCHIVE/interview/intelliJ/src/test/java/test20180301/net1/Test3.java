package test20180301.net1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by ding on 2018/3/1.
 */
public class Test3 {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("请输入名称:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if(name.equals("")){
            return;
        }

        Socket client = new Socket("localhost",9991);
        new Thread(new Send(client,name)).start(); //一条路径
        new Thread(new Receive(client)).start(); //一条路径

    }
}
