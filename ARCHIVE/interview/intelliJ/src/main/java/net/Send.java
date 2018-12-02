package net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by ding on 2018/3/1.
 */
public class Send implements Runnable {
    private BufferedReader console;
    private DataOutputStream dos;
    private boolean isRunning = true;
    private String name;
    public Send() {
        this.console = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket client,String name){
        this();
        try {
            dos = new DataOutputStream(client.getOutputStream());
            this.name =name;
            send(this.name);
        } catch (IOException e) {
            //e.printStackTrace();
            isRunning =false;
            Utils.close(dos, console);

        }
    }

    public Send(Socket client){
        this();
        try{
         dos =    new DataOutputStream(client.getOutputStream());
        }catch (Exception e){
            isRunning = false;
            Utils.close(dos,console);
        }
    }
    public void send(String msg){
        try {
            if(null!=msg&& !msg.equals("")){
                dos.writeUTF(msg);
                dos.flush(); //强制刷新
            }
        } catch (IOException e) {
            //e.printStackTrace();
            isRunning =false;
            Utils.close(dos, console);
        }
    }

    private String getMsgFromConsole(){
        try {
            return console.readLine();
        }catch (Exception e){

        }
        return "";
    }


    public void send(){
        String msg = getMsgFromConsole();
        try {
            if( null!= msg && !"".equals(msg)){
                dos.writeUTF(msg);
                dos.flush();
            }
        }catch (Exception e){
            isRunning = false;
            Utils.close(dos,console);
        }
    }
    @Override
    public void run() {
        while (isRunning){
            send();
        }
    }
}
