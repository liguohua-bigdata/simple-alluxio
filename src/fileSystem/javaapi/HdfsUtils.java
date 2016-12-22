package fileSystem.javaapi;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by liguohua on 22/12/2016.
 */
public class HdfsUtils {
    /**
     * 此方法用于判断是否存在文件
     *
     * @param fileSystemInfo 文件系统信息
     * @param patho          原文件路径
     * @param pathn          目标文件路径
     * @return 文件是否重命名成功
     */
    public static boolean rename(FileSystemInfo fileSystemInfo, String patho, String pathn) {
        FileSystem fs = getFileSystem(fileSystemInfo);
        Path urio = new Path(patho);
        Path urin = new Path(pathn);
        try {
            //重命名
            if (fs.exists(urio)) {
                fs.rename(urio, urin);
                return fs.exists(urin);
            } else {
                throw new RuntimeException("file not exist: " + urio);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFileSystem(fs);
        }
        return false;
    }

    /**
     * 此方法用于判断是否存在文件
     *
     * @param fileSystemInfo 文件系统信息
     * @param path           文件路径
     * @return 文件是否存在
     */
    public static boolean exists(FileSystemInfo fileSystemInfo, String path) {
        FileSystem fs = getFileSystem(fileSystemInfo);
        Path uri = new Path(path);
        try {
            return fs.exists(uri);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFileSystem(fs);
        }
        return false;
    }

    /**
     * 此方法用于，获取FileSystem
     *
     * @param fileSystemInfo 文件系统信息
     * @return FileSystem
     */
    private static FileSystem getFileSystem(FileSystemInfo fileSystemInfo) {
        System.setProperty("HADOOP_USER_NAME", "root");
        FileSystem fs = null;
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", fileSystemInfo.getFileSystemType().toLowerCase().trim() + "://" + fileSystemInfo.getMaster().trim() + ":" + fileSystemInfo.getPort());
        try {
            return fs = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fs;
    }

    /**
     * 关闭FileSystem
     *
     * @param fs FileSystem
     */
    private static void closeFileSystem(FileSystem fs) {
        try {
            if (fs != null) {
                fs.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于限定文件系统类型
     */
    public static interface FileSystemType {
        public static final String ALLUXIO = "alluxio";
        public static final String HDFS = "hdfs";
    }

    /**
     * 用于封装文件系统信息
     */
    public static class FileSystemInfo {
        private String FileSystemType;
        private String master;
        private int port;

        public FileSystemInfo(String fileSystemType, String master, int port) {
            FileSystemType = fileSystemType;
            this.master = master;
            this.port = port;
        }

        public String getFileSystemType() {
            return FileSystemType;
        }

        public String getMaster() {
            return master;
        }

        public int getPort() {
            return port;
        }
    }

}
