package bigdata.mr.hadooprpc.client;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import bigdata.mr.hadooprpc.protocol.ClientNamenodeProtocol;

public class MyHdfsClient {
	public static void main(String[] args) throws IOException {
		ClientNamenodeProtocol namenode = RPC.getProxy(ClientNamenodeProtocol.class, 1L,
				new InetSocketAddress("localhost", 8888),
				new Configuration());
		String metaData = namenode.getMetaData("/wordcount/input");
		System.out.println(metaData);
	}
}
