package fileSystem.kv;

public class AlluxioKvUtilsTest {
    public static void main(String[] args) {
        AlluxioKvUtils.write("/alluxiotest/kvstore2", "400", "hello");

        String v = AlluxioKvUtils.get("/alluxiotest/kvstore2", "400");
        System.out.println(v);
    }
}
