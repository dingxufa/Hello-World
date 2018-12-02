package bigdata.mr.hadooprpc.client;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import bigdata.mr.hadooprpc.protocol.IUserLoginService;

public class UserLoginAction {
	public static void main(String[] args) throws IOException {
		IUserLoginService userLoginService = RPC.getProxy(IUserLoginService.class, 100L, 
				new InetSocketAddress("localhost", 9999), 
				new Configuration());
		String login = userLoginService.login("ding", "123");
		System.out.println(login);
	}
}
