package fileSystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

/**
 * Created by liguohua on 20/12/2016.
 */
public class OldApi {
    public static void main(String[] args) {
        //设置使用root用户操作
        System.setProperty("HADOOP_USER_NAME", "root");

        Configuration conf = new Configuration();

        //设置hdfs和yarn地址
        conf.set("fs.defaultFS", "hdfs://10.45.10.33:8020");
        conf.set("yarn.resourcemanager.hostname","10.45.10.33");

        //设置需要访问的文件在HDFS上的URL
        String uri = "/linecount/random_file.txt";

        Path path = new Path(uri);



    }
}
