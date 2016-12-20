package fileSystem.javaapi;

import alluxio.AlluxioURI;
import alluxio.client.file.FileSystem;
import alluxio.client.file.URIStatus;
import alluxio.exception.AlluxioException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

public class AlluxioFsUitls {
    //获取文件系统FileSystem
    private static final FileSystem fs = FileSystem.Factory.get();

    /**
     * 此方法用于添加挂载点
     *
     * @param alluxioFilePath 文件路径
     */
    public static void mount(String alluxioFilePath, String underFileSystemPath) {
        //1.创建文件路径 AlluxioURI
        AlluxioURI apath = new AlluxioURI(alluxioFilePath);
        AlluxioURI upath = new AlluxioURI(alluxioFilePath);
        try {
            //2.添加挂载点
            if (!fs.exists(apath)) {
                fs.mount(apath, upath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法用于删除挂载点
     *
     * @param filePath 文件路径
     */
    public static void unmount(String filePath) {
        //1.创建文件路径 AlluxioURI
        AlluxioURI path = new AlluxioURI(filePath);
        try {
            //2.删除挂载点
            if (fs.exists(path)) {
                fs.unmount(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法用于创建文件，并向文件中输出内容
     *
     * @param filePath 文件路径
     */
    public static void createFile(String filePath, String content) {
        //1.创建文件路径 AlluxioURI
        AlluxioURI path = new AlluxioURI(filePath);
        BufferedOutputStream bo = null;
        try {
            //2.打开文件输出流,使用BufferedOutputStream输出
            if (!fs.exists(path)) {
                bo = new BufferedOutputStream(fs.createFile(path));
                //3.输出文件内容
                bo.write(content.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        } finally {
            try {
                //4.关闭输入流，释放资源
                if (bo != null) {
                    bo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 此方法用于读取alluxio文件
     *
     * @param filePath 文件路径
     */
    public static void openFile(String filePath) {
        //2.创建文件路径 AlluxioURI
        AlluxioURI path = new AlluxioURI(filePath);
        BufferedInputStream bf = null;
        try {
            //3.打开文件输入流，使用BufferedInputStream读取
            if (fs.exists(path)) {
                bf = new BufferedInputStream(fs.openFile(path));
                //4.读取文件内容
                byte[] buffer = new byte[1024];
                for (int len = 0; (len = bf.read(buffer)) != -1; ) {
                    String content = new String(buffer, 0, len);
                    System.out.println(content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        } finally {
            try {
                //5.关闭输入流，释放资源
                if (bf != null) {
                    bf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 此方法用于释放alluxio中的文件或路径
     *
     * @param filePath 文件路径
     */
    public static void free(String filePath) {
        //1.创建文件路径 AlluxioURI
        AlluxioURI path = new AlluxioURI(filePath);
        try {
            //2.释放文件
            if (fs.exists(path)) {
                fs.free(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法用于删除文件或路径
     *
     * @param filePath 文件路径
     */
    public static void delete(String filePath) {
        //1.创建文件路径 AlluxioURI
        AlluxioURI path = new AlluxioURI(filePath);
        try {
            //2.删除文件
            if (fs.exists(path)) {
                fs.delete(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        }
    }


    /**
     * 此方法用于创建文件夹
     *
     * @param dirPath 文件夹路径
     */
    public static void createDirectory(String dirPath) {
        //1.创建文件路径 AlluxioURI
        AlluxioURI path = new AlluxioURI(dirPath);
        try {
            //2.创建文件夹
            if (!fs.exists(path)) {
                fs.createDirectory(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法用于获取文件状态信息
     *
     * @param filePath 文件路径
     */
    public static void listStatus(String filePath) {
        //1.创建文件路径 AlluxioURI
        AlluxioURI path = new AlluxioURI(filePath);
        try {
            //2.获取文件状态信息
            final List<URIStatus> uriStatuses = fs.listStatus(path);
            System.out.println(uriStatuses);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法用于获取文件状态信息
     *
     * @param filePath 文件路径
     */
    public static void getStatus(String filePath) {
        //1.创建文件路径 AlluxioURI
        AlluxioURI path = new AlluxioURI(filePath);
        try {
            //2.获取文件状态信息
            URIStatus status = fs.getStatus(path);
            System.out.println(status);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法用于重命名文件
     *
     * @param sourcePath 原文件路径
     * @param distPath   目的文件路径
     */
    public static void rename(String sourcePath, String distPath) {
        //1.创建文件路径 AlluxioURI
        AlluxioURI sourcepath = new AlluxioURI(sourcePath);
        AlluxioURI distpath = new AlluxioURI(distPath);
        try {
            //2.重命名操作
            fs.rename(sourcepath, distpath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        }
    }
}
