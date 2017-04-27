package com.chinagreentown.dmp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Java API 读写Hbase
 * Created by saicao on 2017/3/13.
 */
public class HbaseDemo {
    public static void main(String[] args) throws IOException {
        System.out.print("hello world   ");
        System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.3");
        getData();

        //getData();
    }

    public static HTable getHTableByTableName(String tbname) throws IOException {
        // get the hbase conf
        Configuration conf = HBaseConfiguration.create();

        // new the Hbase table instance
        HTable table = new HTable(conf, tbname);

        return table;
    }

    /**
     * 查询
     */
    public static void getData() throws IOException {
        Scan scan = new Scan(Bytes.toBytes(0), Bytes.toBytes(1));
        scan.setCaching(1);


        String tableName = "newtest";

        HTable table = getHTableByTableName(tableName);

        // create get with rowkey
        Get get = new Get(Bytes.toBytes("row1"));

        // add column
        get.addColumn(Bytes.toBytes("cf"),
                Bytes.toBytes("name"));

        // get data
        Result res = table.get(get);

        // key: rowkey + cf + c + version
        // value: value
        for (Cell cell : res.rawCells()) {
            System.out.println(cell);
            System.out.println("********************" + Bytes.toString(CellUtil.cloneFamily(cell)) + ":" +
                    Bytes.toString(CellUtil.cloneQualifier(cell)) + ":" +
                    Bytes.toString(CellUtil.cloneValue(cell)) + ":" +
                    Bytes.toString(CellUtil.cloneRow(cell)));
        }

        table.close();
    }

    /**
     * 添加数据
     * 实际开发中建议tablename & column family封装为常量
     * 插入时数据格式最好为Map<String, Object></>
     */
    public static void putData() throws IOException {
        String tableName = "newtest";
        HTable table = getHTableByTableName(tableName);

        Put put = new Put(Bytes.toBytes("10004"));

        put.add(Bytes.toBytes("cf"),
                Bytes.toBytes("name"),
                Bytes.toBytes("lisi"));

        table.put(put);

        table.close();

    }

}
