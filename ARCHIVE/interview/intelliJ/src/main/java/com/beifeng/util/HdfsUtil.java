package com.beifeng.util;

import java.io.IOError;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsUtil {
	public static boolean deleteFile(String path) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.100.120");
		FileSystem fs = null;
		try {
			fs = FileSystem.get(conf);
			return fs.delete(new Path(path), true);
		} finally {
			fs.close();
		}
	}
}
