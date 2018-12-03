package net;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * Created by ding on 2018/3/1.
 */
public class Receive implements Runnable {
    private DataInputStream dis;
    private boolean isRunning=true;
    public Receive(){}
    public Receive(Socket client){
        try{
            dis = new DataInputStream(client.getInputStream());
        }catch (Exception e){
            isRunning = false;
            Utils.close(dis);
        }
    }

    public String receive(){
        String msg="";
        try{
            msg = dis.readUTF();

        }catch (Exception e){
            isRunning = false;
            Utils.close(dis);
        }
        return msg;
    }
    @Override
    public void run() {
        while (isRunning){
            System.out.println(receive());
        }
    }
}
