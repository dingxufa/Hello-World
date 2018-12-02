package bigdata.mr.hadooprpc.service;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.RPC.Server;
import org.junit.Test;

import bigdata.mr.hadooprpc.protocol.ClientNamenodeProtocol;
import bigdata.mr.hadooprpc.protocol.IUserLoginService;

public class PublishServiceUtil {
	@Test
	public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
		Builder builder = new RPC.Builder(new Configuration());
		builder.setBindAddress("localhost")
		.setPort(8888)
		.setProtocol(ClientNamenodeProtocol.class)
		.setInstance(new MyNamenode());
		
		Server server = builder.build();
		server.start();
		
		
		Builder builder2 = new RPC.Builder(new Configuration());
		builder2.setBindAddress("localhost")
		.setPort(9999)
		.setProtocol(IUserLoginService.class)
		.setInstance(new UserLoginServiceImpl());
		
		Server server2 = builder2.build();
		server2.start();
		
	}
}
