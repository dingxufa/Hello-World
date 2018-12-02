package bigdata.mr.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

public class HdfsClientDemo2 {
	static FileSystem fs;
	static Configuration conf;
	
	@Before
	public static void  init() throws Exception{
		conf = new Configuration();
		//conf.set("fs.defaultFS", "hdfs://wordcount");
		
		//fs = FileSystem.get(conf);
		fs = FileSystem.get(new URI("hdfs://server0:9000"), new Configuration(), "hadoop");
		
	}
	
	@Test
	public  void  testupload() {
		
		conf = new Configuration();
		//conf.set("fs.defaultFS", "hdfs://wordcount");
		
		//fs = FileSystem.get(conf);
		try {
			fs = FileSystem.get(new URI("hdfs://server0:9000"), new Configuration(), "hadoop");
			fs.copyFromLocalFile(new Path("D:/download/flow.log"),new Path("/flow.log_3") );
			fs.close();
		} catch (Exception e) {//IOException | InterruptedException | URISyntaxException e
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		HdfsClientDemo2 hdfsClientDemo2 = new HdfsClientDemo2();
		hdfsClientDemo2.init();
		hdfsClientDemo2.testupload();
	}
	
	@Test
	public void test(){
		System.out.println("aaaaaaaaaa");
	}
}
