package com.beifeng.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import com.beifeng.hbase.utli.HBaseUtil;

public class TestHBaseAdmin {
	public static void main(String[] args) throws Exception {
		Configuration conf = HBaseUtil.getHBaseConfiguration();
		HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
		try {
			testCreateTable(hBaseAdmin);
			//testGetTableDescribe(hBaseAdmin);
			//testDeleteTable(hBaseAdmin);
		} finally {
			hBaseAdmin.close();
		}
	}

	/**
	 * 测试创建table
	 * 
	 * @throws java.io.IOException
	 */
	static void testCreateTable(HBaseAdmin hbAdmin) throws IOException {
		TableName tableName = TableName.valueOf("users");
		if (!hbAdmin.tableExists(tableName)) { // 判断表是否存在
			HTableDescriptor htd = new HTableDescriptor(tableName);
			htd.addFamily(new HColumnDescriptor("f"));
			htd.setMaxFileSize(10000L);
			hbAdmin.createTable(htd);
			System.out.println("创建表成功");
		} else {
			System.out.println("表存在");
		}
	}

	/**
	 * 测试获取表信息
	 * 
	 * @param hbAdmin
	 * @throws java.io.IOException
	 */
	static void testGetTableDescribe(HBaseAdmin hbAdmin) throws IOException {
		TableName name = TableName.valueOf("users");
		if (hbAdmin.tableExists(name)) {// 判断表是否存在
			HTableDescriptor htd = hbAdmin.getTableDescriptor(name);
			System.out.println(htd);
		} else {
			System.out.println("表不存在");
		}
	}

	/**
	 * 测试删除
	 * 
	 * @param hbAdmin
	 * @throws java.io.IOException
	 */
	static void testDeleteTable(HBaseAdmin hbAdmin) throws IOException {
		TableName name = TableName.valueOf("users");
		if (hbAdmin.tableExists(name)) {// 判断表是否存在
			if (hbAdmin.isTableEnabled(name)) {// 判断表的状态是出于enabled还是disabled状态。
				hbAdmin.disableTable(name);
			}
			hbAdmin.deleteTable(name);
			System.out.println("删除成功");
		} else {
			System.out.println("表不存在");
		}
	}

}
