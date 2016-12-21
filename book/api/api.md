#一、alluxio编程API概述
```
alluxio为我们提供了两种编程API。
1.为了更好的性能，它提供了原生的JavaAPI.
2.为了兼容Hadoop代码它提供了HadoopAPI。
```
#二、alluxio本地运行及数据准备
```
由于JavaAPI在开发时在本地，它默认会连接本地alluxio,所以在开发时，建议启动本地alluxio.
```
##1.启动本地alluxio
```
1.生成本地配置文件
./bin/alluxio bootstrapConf localhost local

2.格式化alluxio文件系统
./bin/alluxio format

3.启动本地alluxio
./bin/alluxio-start.sh local
```
![](images/Snip20161220_5.png) 
##2.准备测试数据
```
1.创建测试文件夹
./bin/alluxio fs mkdir /alluxiotest

2.上传测试文件
./bin/alluxio fs copyFromLocal /Applications/alluxio-1.3.0/README.MD /alluxiotest/
```
![](images/Snip20161220_6.png) 

#三、alluxio原生JavaAPI读取实战
###执行程序
```java
package fileSystem.javaapi;
import alluxio.AlluxioURI;
import alluxio.client.file.FileInStream;
import alluxio.client.file.FileSystem;
import alluxio.exception.AlluxioException;

import java.io.IOException;
public class AlluxioReadFile001 {
    public static void main(String[] args) {

        //1.获取文件系统FileSystem
        FileSystem fs = FileSystem.Factory.get();
        //2.创建文件路径 AlluxioURI
        AlluxioURI path = new AlluxioURI("/alluxiotest/README.MD");
        FileInStream in = null;
        try {
            //3.打开文件输入流
            in = fs.openFile(path);

            //4.读取文件内容
            byte[] buffer = new byte[1024];
            for (int len = 0; (len = in.read(buffer)) != -1; ) {
                String content = new String(buffer, 0, len);
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        } finally {
            try {
                //5.关闭输入流，释放资源
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```
###执行效果
```
能成功读取文件中的内容！
```


#四、alluxio原生JavaAPI写出实战
###执行程序
```
package fileSystem.javaapi;

import alluxio.AlluxioURI;
import alluxio.client.file.FileOutStream;
import alluxio.client.file.FileSystem;
import alluxio.exception.AlluxioException;

import java.io.IOException;

public class AlluxioWriteFile001 {
    public static void main(String[] args) {
        //1.获取文件系统FileSystem
        FileSystem fs = FileSystem.Factory.get();
        //2.创建文件路径 AlluxioURI
        AlluxioURI path = new AlluxioURI("/alluxiotest/writetest001.txt");
        FileOutStream out = null;
        try {
            //3.打开文件输出流
            out = fs.createFile(path);
            //4.输出文件内容
            out.write("this is test ".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlluxioException e) {
            e.printStackTrace();
        } finally {
            try {
                //5.关闭输入流，释放资源
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```
###执行效果
![](images/Snip20161220_7.png) 
#五、alluxio的存储系统和IO选项
##1.alluxio的存储系统
```
alluxio视野中有两种存储
1.alluxio自己管理的存储，包括内存，SSD,HDD.
2.alluxio的底层文件系统，包括HDFS,S3,Swift,ceph等
```
##2.alluxio的IO选项
```
因为有自己管理的存储和底层存储两种系统，因此在操作自己管理的存储系统的时候如何操作底层存储就成了问题。
1.在读取自己管理的存储系统时候怎样操作底层存储系统。ReadType
2.在写入自己管理的存储系统时候怎样操作底层存储系统。WriteType
```

##3.alluxio的ReadType
|读类型|行为|
|---|---|
|CACHE_PROMOTE|如果读取的数据在Worker上时，该数据被移动到Worker的最高层。如果该数据不在本地Worker的Alluxio存储中，那么就将一个副本添加到本地Alluxio Worker中，用于每次完整地读取数据块。这是默认的读类型。|
|CACHE|如果该数据不在本地Worker的Alluxio存储中，那么就将一个副本添加到本地Alluxio Worker中，用于每次完整地读取数据块。|
|NO_CACHE|不会创建副本|

##4.alluxio的WriteType
|写类型|行为|
|---|---|
|CACHE_THROUGH|数据被同步地写入到Alluxio的Worker和底层存储系统。|
|MUST_CACHE|数据被同步地写入到Alluxio的Worker。但不会被写入到底层存储系统。这是默认写类型。|
|THROUGH|数据被同步地写入到底层存储系统。但不会被写入到Alluxio的Worker。|
|ASYNC_THROUGH|	数据被同步地写入到Alluxio的Worker，并异步地写入到底层存储系统。处于实验阶段。|

#六、alluxio的定位策略
##1.alluxio的定位策略问题产生
```
因为alluxio是一个分布式的系统，因此在读写文件时的另一问题是，如何定位分散在多个worker上的数据。
1.配置文件中的定位策略
用户可以简单的覆盖默认策略类通过修改配置文件alluxio.user.file.write.location.policy.class内的属性

2.api中的定位策略
使用Alluxio的Java API，用户可以在CreateFileOptions中设置该策略以用于写文件，也可在OpenFileOptions中设置该策略用于向Alluxio中读文件。
```
##2.alluxio内置的定位策略
|策略|意义|
|---|---|
|LocalFirstPolicy(alluxio.client.file.policy.LocalFirstPolicy)|首先返回本地主机，如果本地Worker没有足够的容量容纳一个数据块，那么就会从有效的Worker列表中随机选择一个Worker。这也是默认策略。|
|MostAvailableFirstPolicy (alluxio.client.file.policy.MostAvailableFirstPolicy)|返回拥有最多可用容量的Worker|
|RoundRobinPolicy (alluxio.client.file.policy.RoundRobinPolicy)|以循环的方式选取存储下一个数据块的Worker，如果该Worker没有足够的容量，就将其跳过。|
|SpecificHostPolicy (alluxio.client.file.policy.SpecificHostPolicy)|返回指定主机名的Worker。该策略不能被设置为默认策略。|
##3.alluxio自定义的定位策略
```
Alluxio支持自定义策略，所以你可以通过实现接口alluxio.client.file.policyFileWriteLocationPolicy，开发自己的
定位策略来迎合应用需求。注意默认策略必须要有一个空构造函数。要想使用ASYNC_THROUGH写类型，所有的文件数据块必须被写到
相同的Worker上。
```


#七、封装alluxio原生JavaAPI工具类
###封装原理
```
根据alluxio为我们提供的原生JavaAPI编程接口，我们可以自行封装一个比较好用的工具类，以便我们开发使用。
```
###执行程序
```java

```
