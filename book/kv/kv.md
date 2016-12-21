#一、配置支持键值对存储
```
alluxio支持键值对存储的机制。默认没有开启，需要自己去开启
```

##1.测试是否开启键值对存储
```
1.测试命令
./bin/alluxio runKVTest

2.回显如下
Alluxio key value service is disabled. To run this test, please set alluxio.keyvalue.enabled
to be true and restart the cluster.
```
##2.配置开启kev-value存储
```
1.创建alluxio-site.properties
cp $ALLUXIO_HOME/conf/alluxio-site.properties.template $ALLUXIO_HOME/conf/alluxio-site.properties

2. 编辑配置文件
vim  $ALLUXIO_HOME/conf/alluxio-site.properties

3.添加内容如下
alluxio.keyvalue.enabled = true
alluxio.keyvalue.partition.size.bytes.max = 512MB
```
| 属性名 |默认值 |意义|
|---|---|---|
|alluxio.keyvalue.enabled |false |是否开启key-value键值存储服务|
|alluxio.keyvalue.partition.size.bytes.max |512MB|每个分区的大小上限|

##3.重启alluxio
```
1.关闭alluxio
./bin/alluxio-stop.sh all

2.开启alluxio
./bin/alluxio-start.sh local
```
##4.再次测试是否开启键值对存储
```
1.测试命令
./bin/alluxio runKVTest

2.回显如下
Passed the test!
```		
		
#二、键值对存储编程实战		



