package bigdata.mr;


import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

public class HdfsClientDemo {
	
	
	FileSystem fs = null;
	@Before
	public void init() throws Exception{
		Configuration conf = new Configuration();
		/*conf.set("fs.defaultFS", "hdfs://server0:9000");
		fs = FileSystem.get(conf);*/
		fs = FileSystem.get(new URI("hdfs://server0:9000") , conf, "hadoop");
	}
	
	@Test
	public void testUpload() throws Exception{ //"d:/test.txt"  /test.txt.copy
		fs.copyFromLocalFile(new Path("d:/data.csv"), new Path("/data.csv.copy"));
		fs.close();
	}
	
	@Test
	public void testDownload() throws Exception{
		fs.copyToLocalFile(new Path("/test.txt.copy"), new Path("f:/"));
		fs.close();
	}
	
	
}
