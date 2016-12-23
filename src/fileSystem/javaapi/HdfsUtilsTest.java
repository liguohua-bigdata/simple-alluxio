package fileSystem.javaapi;

public class HdfsUtilsTest {
    private static HdfsUtils.FileSystemInfo alluxio = new HdfsUtils.FileSystemInfo(HdfsUtils.FileSystemType.ALLUXIO, "qingcheng11", 19998);
    private static HdfsUtils.FileSystemInfo hdfs = new HdfsUtils.FileSystemInfo(HdfsUtils.FileSystemType.HDFS, "qingcheng11", 9000);

    public static void main(String[] args) {
        long used1 = HdfsUtils.getUsed(alluxio);
        System.out.println(used1);
        long used2 = HdfsUtils.getUsed(hdfs);
        System.out.println(used2);
    }
//    public static void main(String[] args) {
//        java.net.URI uri = HdfsUtils.getUri(alluxio);
//        System.out.println(uri);
//    }
//    public static void main(String[] args) {
//        Path path = HdfsUtils.getWorkingDirectory(hdfs);
//        System.out.println(path);
//    }
//    public static void main(String[] args) {
//        Path path = HdfsUtils.getWorkingDirectory(hdfs);
//        System.out.println(path);
//    }
//    public static void main(String[] args) {
//        Path path = HdfsUtils.getHomeDirectory(alluxio);
//        System.out.println(path);
//    }

//    public static void main(String[] args) {
//        String s1 = "/input/";
//        FileChecksum checksum = HdfsUtils.getFileChecksum(hdfs, s1);
//        System.out.println(checksum);
//    }

//    public static void main(String[] args) {
//        String s1 = "/input/";
//        ContentSummary summary = HdfsUtils.getContentSummary(alluxio, s1);
//        System.out.println(summary);
//    }


//    public static void main(String[] args) {
//        String s1 = "/input/kv8";
//        String d1 = "/Users/liguohua/Documents/F/code/idea/git/simple-alluxio/";
//        HdfsUtils.moveToLocalFile(alluxio, s1, d1);
//    }


//    public static void main(String[] args) {
//        String s1 = "/input";
//        final List<FileStatus> statuses = HdfsUtils.listStatus(alluxio, s1);
//        System.out.println(statuses);
//
//        final List<FileStatus> statuses2 = HdfsUtils.listStatus(hdfs, s1);
//        System.out.println(statuses2);
//    }


//    public static void main(String[] args) {
//        String s1 = "/input/README.txt";
//        HdfsUtils.open(alluxio, s1);
//
//        HdfsUtils.open(hdfs, s1);
//    }

//    public static void main(String[] args) {
//        String s1 = "/Users/liguohua/Documents/F/code/idea/git/";
//        String d1 = "/input/kv8";
//        HdfsUtils.copyFromLocalFile(alluxio, s1, d1);
//        HdfsUtils.delete(alluxio, d1);
//    }


//    public static void main(String[] args) {
//        String patho = "/input/1.txt";
//        HdfsUtils.create(alluxio, patho, true, "1");
//        String pathn = "/input/2.txt";
//        HdfsUtils.create(hdfs, pathn, true, "2");
//    }


//    public static void main(String[] args) {
//        String patho = "/input/c.txt";
//        boolean ab = HdfsUtils.createNewFile(alluxio, patho);
//        System.out.println(ab);
//        String pathn = "/input/b.cvs";
//        boolean hb = HdfsUtils.createNewFile(hdfs, pathn);
//        System.out.println(hb);
//    }

//    public static void main(String[] args) {
//        String patho = "/input/";
//        boolean ab = HdfsUtils.isFile(alluxio, patho);
//        System.out.println(ab);
//        String pathn = "/input/";
//        boolean hb = HdfsUtils.isFile(hdfs, pathn);
//        System.out.println(hb);
//    }

//    public static void main(String[] args) {
//        String patho = "/input/";
//        boolean ab = HdfsUtils.isDirectory(alluxio, patho);
//        System.out.println(ab);
//        String pathn = "/input/";
//        boolean hb = HdfsUtils.isDirectory(hdfs, pathn);
//        System.out.println(hb);
//    }

//    public static void main(String[] args) {
//        String patho = "/input/c.txt";
//        boolean ab = HdfsUtils.delete(alluxio, patho);
//        System.out.println(ab);
//        String pathn = "/input/b.cvs";
//        boolean hb = HdfsUtils.delete(hdfs, pathn);
//        System.out.println(hb);
//    }


//    public static void main(String[] args) {
//        String patho = "/input/test1";
//        boolean ab = HdfsUtils.mkdirs(alluxio, patho);
//        System.out.println(ab);
//        String pathn = "/input/test2";
//        boolean hb = HdfsUtils.mkdirs(hdfs, pathn);
//        System.out.println(hb);
//    }


//    public static void main(String[] args) {
//        String patho = "/input/README2.txt";
//        String pathn = "/input/README.txt";
//        boolean ab = HdfsUtils.rename(alluxio, patho,pathn);
//        System.out.println(ab);
//        boolean hb = HdfsUtils.rename(hdfs, patho, pathn);
//        System.out.println(hb);
//    }


//    public static void main(String[] args) {
//        String path = "/input/README.txt";
//        boolean ab = HdfsUtils.exists(alluxio, path);
//        System.out.println(ab);
//        boolean hb = HdfsUtils.exists(hdfs, path);
//        System.out.println(hb);
//    }

}
