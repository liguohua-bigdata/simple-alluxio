package fileSystem.javaapi;

public class HdfsUtilsTest {
    private static HdfsUtils.FileSystemInfo alluxio = new HdfsUtils.FileSystemInfo(HdfsUtils.FileSystemType.ALLUXIO, "qingcheng11", 19998);
    private static HdfsUtils.FileSystemInfo hdfs = new HdfsUtils.FileSystemInfo(HdfsUtils.FileSystemType.HDFS, "qingcheng11", 9000);

    public static void main(String[] args) {
        String patho = "/input/README2.txt";
        String pathn = "/input/README.txt";
//        boolean ab = HdfsUtils.rename(alluxio, patho,pathn);
//        System.out.println(ab);
        boolean hb = HdfsUtils.rename(hdfs, patho, pathn);
        System.out.println(hb);
    }


//    public static void main(String[] args) {
//        String path = "/input/README.txt";
//        boolean ab = HdfsUtils.exists(alluxio, path);
//        System.out.println(ab);
//        boolean hb = HdfsUtils.exists(hdfs, path);
//        System.out.println(hb);
//    }

}
