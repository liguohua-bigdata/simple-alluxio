##1.alluxio命令行说明
```
1.全路径使用方式
$ALLUXIO_HOME/bin/alluxio fs
2.短路径使用方式

3.通配符使用方式
```


##2.alluxio命令行列表
```
[root@qingcheng12 software]# $ALLUXIO_HOME/bin/alluxio fs
Usage: java AlluxioShell
[cat <path>]                                         Prints the file's contents to the console.
[checksum <Alluxio path>]                            Calculates the md5 checksum of a file in the Alluxio filesystem.
[chgrp [-R] <group> <path>]                          Changes the group of a file or directory specified by args. Specify -R to change the group recursively.
[chmod [-R] <mode> <path>]                           Changes the permission of a file or directory specified by args. Specify -R to change the permission recursively.
[chown [-R] <owner> <path>]                          Changes the owner of a file or directory specified by args. Specify -R to change the owner recursively.
[copyFromLocal <src> <remoteDst>]                    Copies a file or a directory from local filesystem to Alluxio filesystem.
[copyToLocal <src> <localDst>]                       Copies a file or a directory from the Alluxio filesystem to the local filesystem.
[count <path>]                                       Displays the number of files and directories matching the specified prefix.
[cp [-R] <src> <dst>]                                Copies a file or a directory in the Alluxio filesystem. The -R flag is needed to copy directories.
[createLineage <inputFile1,...> <outputFile1,...> [<cmd_arg1> <cmd_arg2> ...]]   Creates a lineage.
[deleteLineage <lineageId> <cascade(true|false)>]    Deletes a lineage. If cascade is specified as true, dependent lineages will also be deleted.
[du <path>]                                          Displays the size of the specified file or directory.
[fileInfo <path>]                                    Displays all block info for the specified file.
[free <path>]                                        Frees the space occupied by a file or a directory in Alluxio.
[getCapacityBytes]                                   Gets the capacity of the Alluxio file system.
[getUsedBytes]                                       Gets number of bytes used in the Alluxio file system.
[leader]                                             Prints the current leader master host name.
[listLineages]                                       Lists all lineages.
[load <path>]                                        Loads a file or directory in Alluxio space, makes it resident in memory.
[loadMetadata <path>]                                Loads metadata for the given Alluxio path from the under file system.
[location <path>]                                    Displays the list of hosts storing the specified file.
[ls [-R] [-f] <path>]                                Displays information for all files and directories directly under the specified path. Specify -R to display files and directories recursively. Specify -f to force loading files in the directory.
[mkdir <path1> [path2] ... [pathn]]                  Creates the specified directories, including any parent directories that are required.
[mount [-readonly] [-shared] [-P <properties file name>] <alluxioPath> <ufsURI>]   Mounts a UFS path onto an Alluxio path.
[mv <src> <dst>]                                     Renames a file or directory.
[persist <alluxioPath1> [alluxioPath2] ... [alluxioPathn]]   Persists files or directories currently stored only in Alluxio to the UnderFileSystem.
[pin <path>]                                         Pins the given file or directory in memory (works recursively for directories). Pinned files are never evicted from memory, unless TTL is set.
[report <path>]                                      Reports to the master that a file is lost.
[rm [-R] <path>]                                     Removes the specified file. Specify -R to remove file or directory recursively.
[setTtl <path> <time to live(in milliseconds)>]      Sets a new TTL value for the file at path.
[tail -c <number of bytes> <path>]                   Prints the file's last n bytes (by default, 1KB) to the console.
[touch <path>]                                       Creates a 0 byte file. The file will be written to the under file system.
[unmount <alluxioPath>]                              Unmounts an Alluxio path.
[unpin <path>]                                       Unpins the given file or folder from memory (works recursively for a directory).
[unsetTtl <path>]                                    Unsets the TTL value for the given path.
```
|操作|语法|描述|
|---|---|---|
|load|load "path"|将底层文件系统的文件或者目录加载到Alluxio中|
|rm|rm "path"|删除一个文件，如果输入路径是一个目录该命令失败|
|cat|cat "path"|将Alluxio中的一个文件内容打印在控制台中|
|ls|ls "path"|列出给定路径下的所有直接文件和目录的信息，例如大小|
|cp|cp "src" "dst"|复制文件|
|mv|mv "source" "destination"|将"source"指定的文件或文件夹移动到"destination"指定的新路径，如果"destination"已经存在该命令失败。|
|mkdir|mkdir "path1" ... "pathn"|在给定路径下创建文件夹，以及需要的父文件夹，多个路径用空格或者tab分隔，如果其中的任何一个路径已经存在，该命令失败|
|checksum |checksum "path"	 | |
|chgrp  | chgrp "group" "path" |修改Alluxio中的文件或文件夹的所属组  |
|chmod |chmod "permission" "path"|修改Alluxio中文件或文件夹的访问权限 |
|chown |chown "owner" "path" |修改Alluxio中文件或文件夹的所有者|
|copyFromLocal|copyFromLocal "source path" "remote path"|将“source path”指定的本地文件系统中的文件拷贝到Alluxio中"remote path"指定的路径 如果"remote path"已经存在该命令会失败 |
|copyToLocal|	copyToLocal "remote path" "local path"|将"remote path"指定的Alluxio中的文件复制到本地文件系统中|
|count|count "path"|输出"path"中所有名称匹配一个给定前缀的文件及文件夹的总数|
|du|du "path"|输出一个指定的文件或文件夹的大小|
|fileInfo|fileInfo "path"|输出指定的文件的数据块信息|
|free|free "path"|将Alluxio中的文件或文件夹移除，如果该文件或文件夹存在于底层存储中，那么仍然可以在那访问|
|getCapacityBytes|getCapacityBytes|获取Alluxio文件系统的容量|
|getUsedBytes|getUsedBytes|获取Alluxio文件系统已使用的字节数|
|leader|leader|打印当前Alluxio leader master节点主机名|
|loadMetadata|loadMetadata "path"|将底层文件系统的文件或者目录的元数据加载到Alluxio中|
|location|location "path"|输出包含某个文件数据的主机|
|mount|mount "path" "uri"|将底层文件系统的"uri"路径挂载到Alluxio命名空间中的"path"路径下，"path"路径事先不能存在并由该命令生成。 没有任何数据或者元数据从底层文件系统加载。当挂载完成后，对该挂载路径下的操作会同时作用于底层文件系统的挂载点。|
|unmount|unmount "path"|卸载挂载在Alluxio中"path"指定路径上的底层文件路径，Alluxio中该挂载点的所有对象都会被删除，但底层文件系统会将其保留。|
|persist|persist "path1" ... "pathn"|将仅存在于Alluxio中的文件或文件夹持久化到底层文件系统中|
|pin|pin "path"|将给定文件锁定到内容中以防止剔除。如果是目录，递归作用于其子文件以及里面新创建的文件|
|unpin|unpin "path"|将一个文件解除锁定从而可以对其剔除，如果是目录则递归作用|
|report|report "path"|向master报告一个文件已经丢失|
|setTtl|setTtl "path" "time"|设置一个文件的TTL时间，单位毫秒|
|unsetTtl|unsetTtl "path"|删除文件的ttl值|
|tail|tail "path"|将指定文件的最后1KB内容输出到控制台|
|touch|touch "path"|在指定路径创建一个空文件|

	
	
		
		
		

