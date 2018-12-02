package net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by ding on 2018/3/1.
 */
public class Test {
    public static void main(String[] args)throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 9991);
        new Thread(new Send(socket)).start();
        new Thread(new Receive(socket)).start();
    }
}
