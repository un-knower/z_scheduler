package com.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.HdfsConfiguration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by GP39 on 2016/8/15.
 */
public class HdfsMain {

    public static void main(String[] args) throws IOException {
//        Configuration hdfsConfiguration = new Configuration();
//        hdfsConfiguration.addResource("core-site.xml");
//        hdfsConfiguration.addResource("hdfs-site.xml");
        //configuration.set("fs.defaultFS","hdfs://nameservice1");
        //configuration.addResource(new File(""));
        System.setProperty("hadoop.home.dir","E://BaiduYunDownload");
        System.setProperty("HADOOP_USER_NAME","hive");
        HdfsConfiguration hdfsConfiguration = new HdfsConfiguration();
        hdfsConfiguration.init();
        Iterator<Map.Entry<String, String>> iterator = hdfsConfiguration.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey()+"----------->"+entry.getValue());
        }
        FileSystem fileSystem = FileSystem.get(hdfsConfiguration);
//        Path path = new Path("/tmp");
//        //读取
//        FileStatus[] fileStatuses = fileSystem.listStatus(path);
//        for (int i =0 ;i<fileStatuses.length;i++){
//            System.out.println(fileStatuses[i]);
//        }
        //创建文件夹
//        byte[] bytes = "hello world".getBytes();
//        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/tmp/abc2"));
//        fsDataOutputStream.write(bytes,0,bytes.length);
        //上传文件
        String localSrc="e://m_da_kdj_mult_buy_month.q.sql";
        String dest="/user/hive/b9.q";
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(localSrc));
        FSDataOutputStream out = fileSystem.create(new Path(dest), new Progressable() {
            @Override
            public void progress() {
                System.out.println("上传完了一个文件");
            }
        });
        IOUtils.copyBytes(in,out,4096,true);
    }
}
