package hbase;

import java.io.Closeable;
import java.io.IOException;

public class Utils {
	public static <T extends Closeable>void close(T...conns){
		for (T t : conns) {
			try {
				t.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static <T extends org.apache.hadoop.io.Closeable> void close(T...conns){
		for (T t : conns) {
			try {
				t.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
