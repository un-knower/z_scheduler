package com.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by GP39 on 2016/8/19.
 */
public class HbaseTest {
    public static void main(String args[]) throws IOException {
        HbaseTest hbaseTest = new HbaseTest();
        hbaseTest.testCreateTable();
    }


    public Configuration getConfiguration(){
        Configuration conf = HBaseConfiguration.create();
        return conf;
    }


    public void testIfLoaded(){
        HbaseTest hbaseTest = new HbaseTest();
        boolean result = false;
        //指定表名
        String tableName="test_user";
        //创建configuration实例
        System.setProperty("hadoop.home.dir","E://BaiduYunDownload");
        Configuration configuration = hbaseTest.getConfiguration();
        String key = "hbase.zookeeper.quorum";
        String value = configuration.get(key);
        System.out.println(value);
    }



    public void testCreateTable() throws IOException {
        HbaseTest hbaseTest = new HbaseTest();
        boolean result = false;
        //指定表名
        String tableName="test_user";
        //创建configuration实例
        System.setProperty("hadoop.home.dir","E://BaiduYunDownload");
        Configuration configuration = hbaseTest.getConfiguration();
        String key = "hbase.zookeeper.quorum";
        String value = configuration.get(key);
        System.out.println(value);


        Connection connection = ConnectionFactory.createConnection();
        Admin admin = connection.getAdmin();
        //查看所有的表
        HTableDescriptor[] tableDescriptors = admin.listTables();
        for (int i = 0; i < tableDescriptors.length; i++) {
            HTableDescriptor tableDescriptor = tableDescriptors[i];
            String tablename = tableDescriptor.getNameAsString();
            System.out.println(tablename);
        }
        //创建表
        TableName tname = TableName.valueOf(tableName);
        if(admin.tableExists(tname)){
            System.out.println("has exist table "+ tableName);
        }else{
            System.out.println("create table :"+tableName);
            HTableDescriptor htableDescriptor = new HTableDescriptor(tname);
            htableDescriptor.addFamily(new HColumnDescriptor("one"));
            htableDescriptor.addFamily(new HColumnDescriptor("two"));
            admin.createTable(htableDescriptor);
        }
        //查看表
        tableDescriptors = admin.listTables();
        for (int i = 0; i < tableDescriptors.length; i++) {
            HTableDescriptor tableDescriptor = tableDescriptors[i];
            String tablename = tableDescriptor.getNameAsString();
            System.out.println(tablename);
        }
        //删除表
        if(admin.isTableDisabled(tname)){
            System.out.println("被禁用了表");
        }else{
            admin.disableTable(tname);
            admin.deleteTable(tname);
            System.out.println("表被删除");
        }









    }


}
