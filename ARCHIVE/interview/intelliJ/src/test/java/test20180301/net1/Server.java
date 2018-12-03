package test20180301.net1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ding on 2018/3/1.
 */
public class Server  {
    public static void main(String[] args)throws IOException {
        ServerSocket server = new ServerSocket(9999);
        while(true){
            Socket socket= server.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            while(true){
                String msg = dis.readUTF();
                System.out.println(msg);

                dos.writeUTF("server "+msg);
                dos.flush();

            }
        }

    }
}
