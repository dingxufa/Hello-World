package hbase;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
//import org.apache.hadoop.hbase.thrift2.generated.THBaseService.Processor.put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.TableName;
public class Test {
	static Configuration  config = null;
	static{
		config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", "slave1,slave2,slave3");
		config.set("hbase.zookeeper.properties.clientport", "2181");
	}
	
	public static void createTable(String strTableName,String[] families){
		HBaseAdmin admin = null;
		try {
			admin = new HBaseAdmin(config);
			if (admin.tableExists(strTableName)) {
				System.out.println("table has existed");
			}else {
				TableName tableName = TableName.valueOf(strTableName); // 表名称
				HTableDescriptor desc = new HTableDescriptor(tableName);
				for (int i = 0; i < families.length; i++) {
					HColumnDescriptor family = new HColumnDescriptor(families[i]);
					desc.addFamily(family);
				}
				admin.createTable(desc);
				System.out.println("create table "+ strTableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (admin!= null) {
				try {
					admin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void deleteTable(String strTableName){
		HBaseAdmin admin = null;
		try {
			admin = new HBaseAdmin(config);
			if (!admin.tableExists(strTableName)) {
				System.out.println("table not exitsts");
			}else {
				admin.disableTable(strTableName);
				admin.deleteTable(strTableName);
				System.out.println("delete table" +strTableName);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (admin!= null) {
				try {
					admin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void insertData(String strTableName,String rowKey,String family,
			String qualifier,String value){
		Connection conn = null;
		Table table = null;
		try {
			conn = ConnectionFactory.createConnection(config);
			table = conn.getTable(TableName.valueOf(strTableName));
			Put put = new Put(Bytes.toBytes(rowKey));
			put.add(Bytes.toBytes(family),Bytes.toBytes(qualifier),
					Bytes.toBytes(value));
			table.put(put);
			
//			ArrayList<Put> list = new ArrayList<Put>();
//			Put put2 = new Put(Bytes.toBytes(rowKey));
//			put2.add(Bytes.toBytes(family),Bytes.toBytes(qualifier),
//					Bytes.toBytes(value));
//			list.add(put2);
//			table.put(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}

	public static void updateData(String strTableName,String rowKey,String family,
			String qualifier,String newValue){
		Connection conn= null;
		Table table = null;
		try {
			conn = ConnectionFactory.createConnection(config);
			table = conn.getTable(TableName.valueOf(strTableName));
			Put put = new Put(Bytes.toBytes(rowKey));
			put.add(Bytes.toBytes(family),Bytes.toBytes(qualifier),
					Bytes.toBytes(newValue));
			table.put(put);
			System.out.println("更新成功");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			Utils.close(table ,conn);
		}
	}
	
	public static void deleteData(String strTableName,String rowKey){
		Connection conn = null;
		Table table = null;
		try {
			conn = ConnectionFactory.createConnection(config);
			table = conn.getTable(TableName.valueOf(strTableName));
			Delete delete = new Delete(Bytes.toBytes(rowKey));
			table.delete(delete);
			System.out.println("delete successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Utils.close(table , conn);
		}
	}
	
	public static void deleteColumn(String strTableName,String rowKey,String family,
			String column){
		Connection conn = null;
		Table table = null;
		try {
			conn = ConnectionFactory.createConnection(config);
			table = conn.getTable(TableName.valueOf(strTableName));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void queryByRowKey(String strTableName,String rowKey){
		Connection conn = null;
		Table table = null;
		try {
			conn = ConnectionFactory.createConnection(config);
			table = conn.getTable(TableName.valueOf(strTableName));
			Get get = new Get(Bytes.toBytes(rowKey));
			Result result = table.get(get);
			for (Cell cell:result.rawCells()) {
				System.out.println("列族："
						+ Bytes.toString(CellUtil.cloneFamily(cell)));
				System.out.println("列名： "
						+ Bytes.toString(CellUtil.cloneQualifier(cell)));
				System.out.println("列值： "
						+ Bytes.toString(CellUtil.cloneValue(cell)));
				System.out.println("行名： "
						+ Bytes.toString(CellUtil.cloneRow(cell)));
				System.out.println("时间戳： " + cell.getTimestamp());
				System.out.println("-----------------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			Utils.close(table,conn);
		}
	}
}
