package fileSystem.javaapi;

import alluxio.AlluxioURI;
import alluxio.client.file.FileSystem;
import alluxio.exception.AlluxioException;

import java.io.IOException;

public class AlluxioReNameFile001 {
    public static void main(String[] args) {
        //1.获取文件系统FileSystem
        FileSystem fs = FileSystem.Factory.get();
        //2.创建文件路径 AlluxioURI
        AlluxioURI sourcepath = new AlluxioURI("/alluxiotest/rename.MD");
        AlluxioURI distpath = new AlluxioURI("/alluxiotest/README.MD");
        try {
            //3.重命名操作
            fs.rename(sourcepath, distpath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        }
    }
}
