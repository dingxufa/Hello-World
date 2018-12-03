package bigdata.mr.hadooprpc.service;

import bigdata.mr.hadooprpc.protocol.ClientNamenodeProtocol;

public class MyNamenode implements ClientNamenodeProtocol{

	@Override
	public String getMetaData(String path) {
		return path+" : {BLK_1,BLK_2}....";
		
	}

}
