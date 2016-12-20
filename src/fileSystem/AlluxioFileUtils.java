package fileSystem;

import alluxio.AlluxioURI;
import alluxio.client.file.FileOutStream;
import alluxio.client.file.FileSystem;
import alluxio.exception.AlluxioException;

import java.io.IOException;

public class AlluxioFileUtils {
    public static void main(String[] args) {
        FileSystem fs = FileSystem.Factory.get();
        AlluxioURI path = new AlluxioURI("alluxio://qingcheng11:19998/myFile");
        FileOutStream out = null;
        try {
            out = fs.createFile(path);
            out.write("this is test ".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
