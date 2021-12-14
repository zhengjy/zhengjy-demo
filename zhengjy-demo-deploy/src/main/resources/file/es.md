Lucene:
​	文档：索引与搜索的主要数据载体，它包含一个或多个字段，存放将要写入索引或将从所有索引处理的数据。
​	字段：文档的一个片段，它包括两个部分：字段的名称和内容。
​	词项：词项在字段中的一次出现，包括词项的文本、开始和结束的位移以及类型。

	Lucene将写入索引的所有信息组织成一种名为倒排序索引的结构。该结构是一种将词项映射到文档的数据结构。
		每个文档字段都对应着一个倒排序索引。	
		倒排序索引存储结构：
			ElasticSearch Server （文档1）
			MasteringElasticSearch（文档2）
			Apache solr 4 Cookbook（文档3）
			词项				计数		文档
			Apache				1			3
			Cookbook			1			3
			ElasticSearch		2			1,2
			Mastering 			1			2
			Server 				1			1
			Solr				1			3
	段：
		每个索引由多个段组成(segment)组成，每个段只会被创建一次但会被查询多次。索引期间，段经创建就不会在修改。
	段合并：
		多个段会在一个叫做段合并的阶段被合并在一起，Lucene的内在机制决定在某个适合执行，合并后段的数量更少，但是更大。段合并非常耗IO/CPU。
	
	转换倒排序索引：
		文本分析由分析器来执行，分析器由分词器、过滤器和字符映射器组成。
			分词器：
				分词器用来将文本切割成词条，其中携带各种额外的信息的词项，这些信息包括：词项在原始文本中的位置、词项的长度。分词器的工作成果称为
				词条流，因为这些词条被一个接一个推送给过滤器处理。
			过滤器：
				过滤器额外可选，可以为0-n个，用于处理词条流中的词条。它可以移除、修改词条流中的词条，甚至可以创造新的词条。
				自带的过滤器：
				1)小写过滤
				2)ASCII过滤器：移除词条中所有非ASCII字符
				3)同义词过滤器：根据同义词规则，将一个词条转换为另外一个词条
				4)多语言词干还原过滤器

elasticSearch
​		关系数据库     ⇒ 数据库        ⇒ 表         ⇒ 行              ⇒ 列(Columns)

		Elasticsearch  ⇒ 索引(Index)   ⇒ 类型(type)  ⇒ 文档(Docments)  ⇒ 字段(Fields)  
		
	索引：
		es将数据存储在一个或多个索引中。在sql领域中索引就像数据库。
		索引实际上是指向一个或多个物理分片的逻辑命名空间。一个分片是底层的工作单元，它仅保存了全部数据的一部分。
	文档：文档是es中主要的实体，文档由字段构成，每个字段有它的字段名以及一个或多个字段值。文档之间可能有各自不同的字段集合，且文档并没有固定的
			模式或强制的结构。
	映射：所有文档在写入索引前都需要进行分析。
	类型：es中每个文档都与之对应的类型定义。允许用户在一个索引中存储多种文档类型，并为不同文档类型提供不同的映射。
	节点：单个es服务实例称为节点。
	集群：多个节点协同处理，所有这些节点组成的系统称为集群。
	分片：集群允许系统存储的数据总量超过单机容量。为了满足这个需求，es将数据散步到多个物理lucene索引上。这些Lucene索引称为分片。一个索引的数据保存在多个分片中，相当于水平分表。一个分片是一个lucene的实例，它本身就是一个完整的搜索引擎。分片是数据的容器，文档保存在分片内，分片被分配到集群内的各个节点。当集群规模扩大或缩小，es自动的在各节点中迁移分片，使得数据仍然均匀分布在集群中。
	副本：副本解决了访问压力过大时单机无法处理所有请求问题。即为每个分片创建冗余的副本。
	
	es工作流程：
		启动过程：
			当es节点启动时，它使用广播技术来发现同一个集群中的其他节点(关键是配置文件中的集群名称)并与他们连接。见面
			集群中会有一个节点被选为管理节点。该节点负责集群的状态管理以及在集群拓扑变化时作出反应，分发索引分片至集群的相应节点上。
			管理节点读取集群的状态信息，并在必要时恢复出来。在该阶段，管理节点会检查所有索引分片并决定哪些分片将用于主分片。然后整个
			集群进入黄色状态。这时集群可以执行查询，但是系统的吞吐量以及各种可能的状态是未知的(主分片已经分配出去了，而副本没有)，
			因而接下来就是寻找到冗余的分片并且用作副本。如果某个主分片的副本数量过少，管理节点将决定基于某个主分片创建分片和副本。如果
			顺利，集群进入绿色状态(主分片和副本均匀分片好)。
		故障检测：
			es集群管理节点会监控所有可用节点，检查它们是否正在工作。如果任何节点在预期时间没有响应，则认为该节点已经断开，然后启动错误
			处理过程。
			如果是三个节点的集群，即包含一个管理节点，两个数据节点。管理节点会发送ping请求至其他节点，然后等待响应，如果没有响应，则该
			节点会从集群中移除。
	
	es建索引操作只会发生在主分片上。当把一个索引请求发送到某个节点，如果该节点没有对应的主分片或者只有副本，那么请求会被转发到拥有正确的
	主分片的节点。
	es查询分为两个阶段：分散阶段、合并阶段
		分散阶段：将query分发到包含相关文档的多个分片中取执行查询。
		合并阶段：从众多分片中收集返回结果，然后对它们进行合并、排序、后续处理，返回给客户端。
	
	分布式文档存储：
		路由一个文档到分片：
			es在创建索引的时候就确定好分片数量并且永远不会改变这个数量。
			shard = hash(routing) % number_of_primary_shards
			routing:是一个可变值，默认是文档的id
			number_of_primary_shards:主分片的数量
			hash(routing) % number_of_primary_shards在 0-number_of_primary_shards之间的余数，就是寻求文档所在分片的位置。
		主分片和副本均匀分片如何交互：
			一个请求可以发送到集群中任意一个节点。每个节点都知道集群中任意一个文档的位置，所以可以直接将请求转发需要的节点上。
			当发送请求时，内部实现了负载均衡，可以实现轮询访问集群中的所有节点。
		新建、索引和删除文档：
			客户端发个删除文档请求倒es的master主节点，主节点会找到通过_id找到归属的主分片节点，主分片执行成功后，会同步到副分片，一旦
			所有副分片都报告成功，会通知master主节点master主节点再返回客户端成功。
		多文档模式：
			多文档模式类似单文档模式。master节点知道每个文档存储在那个分片中。将多个文档请求分解为每个分片的多文档请求，并且将这些请求并发
			转发到每个参与节点。master主节点收到每个节点的应答，将每个节点的响应收集整成单响应返回给客户端。


	segment(段)：
		es中每一个段都是一个倒排序索引。
		一个Lucene索引在es中称为分片，一个es索引是分片的集合。当es在搜索的时候，他发送查询到每一个属于索引的分片，然后合并每个分片的结果到一个
		全局的结果集。
		新收到的数据写入新的索引文件里。Lucene把每次生成的倒排序索引，叫做一个段。然后另外使用一个commint文件，记录索引内的所有segment。
		而生成sentiment的数据来源，则是内存中的Buffer。
			动态更新过程如下：
				1)当前磁盘有segment可用，同时有一个commit文件记录当前的segment
				2)新收到的数据进入内存buffer
				3)buffer刷到磁盘，生成新的segment,commit文件同步更新。
	
	分片的过度分片：
		分片处理是将一个索引分割成若干个更小的索引过程，从而能够在同一个集群的不同节点上散步它们。在查询的时候，结果是索引中每个分片返回结果的汇总。
		es必须在创建索引时就指定好需要的分片数量。
		更多的分片意味着每个较小的lucene索引上执行的操作会更快。如果将查询分散成对每个分片请求，然后合并结果，也是有代价的。
	
	副本：
		副本过多的缺点，各个分片的副本占用了额外的存储空间，主分片及副本之间的数据拷贝也存在时间开销。
	
	路由：
		路由确保了在索引时拥有相同路由值的文档会索引到相同的分片上，但一个给定的分片上可以有很多拥有不同路由值的文档。
		
	搜索：
		一种复杂的执行模型，因为不知道查询会中那些文档：这些文档可能在集群的任何分片上。
		查询阶段：
			在初始查询阶段时，查询会广播到索引中每个分片。每个分片在本地执行搜索并构建成一个匹配文档的优先队列。一个优先队列是一个有top-n的有序列表。
			查询分为3个阶段：
				1)客户端发送一个search请求到节点A，节点A会创建一个大小为from + size 的空优先队列
				2)节点A将查询请求转发到索引的每个分片中。每个分片在本地执行查询并添加结果到大小为from+size的本地有序优先队列中。
				3)每个分片返回各自优先队列中的所有文档的id和排序值给节点A，它合并这些值到自己的优先队列中产生一个全局排序后的结果列表。
		取回阶段：
			查询阶段标识哪些文档满足搜索请求，此阶段是要取回文档。
			取回阶段分为3个阶段：
				1)节点A辨别出哪些文档需要被取回并向相关的分片提交多个GET请求。
				2)每个分片加载并丰富文档，接着返回给节点A。
				3)一旦所有文档都被取回了，协调节点返回给客户端。
	
	游标：
		ES的搜索是分2个阶段进行的，即Query阶段和Fetch阶段。  Query阶段比较轻量级，通过查询倒排索引，获取满足查询结果的文档ID列表。  而Fetch阶段比较重，需要将每个shard的结果取回，
		在协调结点进行全局排序。  通过From+size这种方式分批获取数据的时候，随着from加大，需要全局排序并丢弃的结果数量随之上升，性能越来越差。
		而Scroll查询，先做轻量级的Query阶段以后，免去了繁重的全局排序过程。 它只是将查询结果集，也就是doc id列表保留在一个上下文里， 之后每次分批取回的时候，只需根据设置的size，
		在每个shard内部按照一定顺序（默认doc_id续)， 取回这个size数量的文档即可。 
		由此也可以看出scroll不适合支持那种实时的和用户交互的前端分页工作，其主要用途用于从ES集群分批拉取大量结果集的情况，一般都是offline的应用场景。  
		比如需要将非常大的结果集拉取出来，存放到其他系统处理，或者需要做大索引的reindex等等。
	
	分片内部原理：
		使文档可被搜索:
			es采用倒排序索引存储。保存每个词项出现过的文档总数，在对应的文档中一个具体词项出现的总次数，词项在文档中的顺序，每个文档的长度等等。
		不变性：
			倒排序索引被写入磁盘后，不可变，为实现倒排序索引的更新，通过更多的索引实现。通过增加新的补充索引来反映新近的修改，而不是直接重写整个倒排序索引。
			每个倒排序索引都会被轮流查询到从最早的开始，查询完后再对结果进行合并。
		动态更新索引：
			
			新文档首先被添加到内存索引缓存中，然后写入一个基于磁盘的段。
			
			逐段搜索:
				1)新文档被收集到内存索引缓存
				2)每秒缓存被提交
					2.1)一个新的段-一个追加的倒排序索引-被写入磁盘
					2.2)一个新的包含新段名字的提交点被写入磁盘
					2.3)磁盘进行 同步-所有在文件系统缓存中等待的写入都刷新到磁盘。
				3)新的段被开启，让它包含的文档可以被搜索
				4)内存缓存被清空，等待接收新的文档。
			
			1)一个文件被索引后，会被添加到内存缓冲区，并且追加到translog。
			2)分片每秒被刷新一次:
				2.1)这些内存缓冲区的文档被写入到一个新的段，且没有进行fysnc操作
				2.2)这个段被打开，使其可被搜索
				2.3)内存缓冲区被清空，translog文件保留。
			3)这个进程继续工作，更多的文档被添加到内存缓冲区和translog中。
			4)每隔一段时间-translog文件越来越大，索引被刷新;一个新的translog被创建，并且全量提交被执行
				4.1)所有在内存缓冲区的文档都被写入一个新的段。
				4.2)缓冲区被清空。
				4.3)一个提交点被写入硬盘。
				4.4)文件系统缓存通过 fsync 被刷新（flush）。
				4.5)老的 translog 被删除。
			
		为什么搜索是 近 实时的？

​		
​		为什么文档的 CRUD (创建-读取-更新-删除) 操作是 实时 的?
​		Elasticsearch 是怎样保证更新被持久化在断电时也不丢失数据?
​		为什么删除文档不会立刻释放空间？
​		refresh, flush, 和 optimize API 都做了什么, 你什么情况下应该是用他们？
​	
​	段合并：
​		es自动刷新流程会每秒会创建一个新的段，这样会导致短时间内的段数量暴增。而且段数目太多会带来麻烦，因为每个段都会消耗文件句柄、内存和cpu运行周期。
​		更重要是因为每个搜索请求都必须轮流检查每个段；所以段越多，搜索越慢。
​		es通过后台进行段合并来解决这问题。小的段合并大的段，然后这些大的段再被合并到更大的段。
​		段合并的时候会将那些旧的已删除文档从系统中清除。被删除的文档不会被拷贝到新的大段中。
​		
​		段合并步骤：
​			1、当索引的时候，刷新操作会创建新的段并将段打开以供搜索。
​			2、合并进程选择一小部分大小相似的段，并且在后台将他们合并更大的段中。这并不会中断索引和搜索。
​			3、合并结束，老的段被删除
​				3.1)新的段被刷新到磁盘，写入一个包含新段且排除旧的较小段的新提交点。
​				3.2)新的段被打开用来搜索。
​				3.3)老的段被删除。

# 写操作（Write）：针对文档的CRUD操作

索引创建步骤：
​	1) 将文档交给切词组件(Tokenizer)进行切词处理
​		将文档中的一个个的单词切割出来，同时消除一些无意义的词，如标点符号、英文中的the和is、中文中的"的"等。
​	2)将得到的词元(Token)交给语言处理组件
​		主要针对第一步生成的词元进行自然语言同化处理，比如针对cars词元，同时生成新的词car;Student词元中的大写字母小写化，这样能够实现一个b不区分student大小写搜索。
​	3)将得到的词(Term)交给索引组件
​		3.1)利用得到的词创建一个词典
​		3.2)对字典按字母顺序进行排序
​		3.3)合并相同的词并建立倒排链表
​	4)由索引组件针对词建立索引
​		针对最后生成的词典，进行索引的建立，至此我们发现根据搜索词，我们可以立即找到对应的文档

### 索引新文档（Create）

​	当用户向一个节点提交一个索引新文档请求，节点会新计算新文档应该加入那个分片(shard)中。每个节点都存储有每个分片存储那个节点信息，因此协调节点会将请求发送给对应的节点。这个请求会发送给主分片，等主分片完成索引，并将请求发送给其所有副本分片，保证每个分片都持有最新数据。

每次写入新文档时，都会先写入内存中，并将这一操作写入translog文件(transaction log)中，此时如果执行搜索操作，这个新文档还不能被索引到。

![1](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\1.png)

es会每隔1秒时间(时间可修改)进行一次刷新操作(refresh)，此时在这1秒时间内写入内存的新文档都会被写入一个文件系统缓存(file system cache)中，并构成一个分段(segment)。此时segment里的文档可以被搜索到，但是尚未写入硬盘，如果发生宕机，这些文档将会丢失。

![2](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\2.png)

### 

不断有新的文档写入，则这个过程不断重复执行。每隔一秒将产生一个新的segment，而translog文件越来越大。

![3](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\3.png)

每隔30分钟或translog文件变大，则执行一次fsync操作。此时所有在文件系统缓存中的segment将被写入磁盘，而translog将被删除(此后会生成新的translog)

![4](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\4.png)

在两次fsyn操作之间，存储在内存和文件系统缓存中的文档是不安全的，一旦出现宕机这些文档就会丢失。所以es引入translog来记录两次fsync之间所有操作，这样机器
​	恢复，es可以根据translog进行还原。
​	translog本身是文件，存在内存中，如果发生宕机就会丢失。es会在每隔5秒时间或一次写入请求完成将translog写入磁盘。可以认为一个对文档的操作一旦写入磁盘便是
​	安全的可复原的。因此只有在当前操作记录被写入磁盘，es才会将操作成功返回给客户端。

![5](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\5.png)

![6](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\6.png)

### 更新（Update）和删除（Delete）文档

es的索引是不能修改的，因此更新和删除操作并不是直接在原索引上执行的。

每一个磁盘上的segment都会维护一个del文件，用来记录被删除的文件。每当一个用户提出删除请求，文档并没有真正删除，索引也没有发生变化，而是在del文件中标记该文档被删除。因此，被删除的文档依然可以被检索，只是在返回检索结果时被过滤掉。每次在启动segment合并工作时，那些被标记删除的文档才会被真正删除。

更新文档会首先查找到原文档，得到该文档的版本号。然后将修改的文档写入内存，次过程与写入一个新文档相同。同时旧文档被标记删除，最终合并segemnt删除。

# 读操作（Read）：查询过程

查询的过程大体分为查询(query)、取回(fetch)两个阶段。这个节点的任务是广播查询请求到所有相关分片，并将他们的响应整合成全局排序后的结果集返回客户端。

### 查询阶段

![7](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\7.png)

第一步是广播请求到索引中每个节点的分片拷贝。查询请求可以被某个主分片或副分片处理，协调节点将在之后的请求中轮询所有的分片拷贝来分摊负载。
​	每个分片将会在本地构建一个优先级队列。如果客户端要求返回结果集排序中从第from名开始的数量为size的结果集，则每个节点都需要生成一个from+size大小的结果集，
​	因此优先级队列的大小也是from+size。分片仅会返回一个轻量级的结果给协调节点，包含结果集中的每一个文档的ID和进行排序所需要的信息。
​	协调节点会将所有分片的结果汇总，并进行全局排序，得到最终的查询排序结果。此时查询阶段结束。

### 取回阶段

查询过程得到的是一个排序结果，标记出哪些文档是符合搜索要求的，此时仍然需要获取这些文档返回客户端。

协调节点会确定实际需要返回的文档，并向含有该文档的分片发送get请求；分片获取文档返回给协调节点；协调节点将结果返回给客户端。

![8](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\8.png)



搜索步骤：
1)用户输入查询语句
​			 比如用户输入：lucene AND learned NOT hadoop	--------->   	用户想要查找包含lucene和learned，但是不包含hadoop的文档
2)对查询语句进行词法分析、语法分析、语言处理
​		2.1)此法分析主要用来识别单词和关键字
​				如针对上述用户的输入，经过此法分析，得到单词为lucene、learned、hodoop，

​				关键词为	 AND、NOT
​		2.2)语法分析主要根据查询语句生词一颗语法树
​				![download](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\1.png)
​		2.3)语法处理和索引建立过程中的语法处理一样
​				如将learned变成learn,经过处理得到以下结果

​	![download](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\2.png)		

3)搜索索引，得到符合语法树的文档

​	首先在反向索引链表里，找到包含lucene、learn和hadoop得到文档，其次，对包含lucene和learn的文档进行求合集操作，得到同时包含两者的文档链条，然后将得到合集后的链表与Hadoop的文档链表进行差集操作，去除包含hadoop的文档，最后此文档链表就是我们要找的文档	

4)根据得到的文档进行语句相关性，对结果进行排序与展示





1. 索引过程：

   1) 有一系列被索引文件

   2) 被索引文件经过语法分析和语言处理形成一系列词(Term) 。

   3) 经过索引创建形成词典和反向索引表。

   4) 通过索引存储将索引写入硬盘。

2. 搜索过程：

   a) 用户输入查询语句。

   b) 对查询语句经过语法分析和语言分析得到一系列词(Term) 。

   c) 通过语法分析得到一个查询树。

   d) 通过索引存储将索引读入到内存。

   e) 利用查询树搜索索引，从而得到每个词(Term) 的文档链表，对文档链表进行交，差，并得到结果文档。

   f) 将搜索到的结果文档对查询的相关性进行排序。

   g) 返回查询结果给用户。

### 引用





![9](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\9.png)	







文档评分
​	查询改写
​		前缀查询范围
​	二次评分
​	批量操作索引
​	过滤器
​	切面过滤器
​	数据处理、文本分析、范例的使用
​	索引合并、段合并策略

#### 倒排序索引


数据	

| docid | age  | gender |
| ----- | ---- | ------ |
| 1     | 18   | 女     |
| 2     | 20   | 女     |
| 3     | 18   | 男     |

每一行都是一个document，每个document都有一个docid，给这些document建立的倒排序索引如下：

年龄	

| 18   | [1,3] |
| ---- | ----- |
| 20   | [2]   |

性别		

| 女   | [1,2] |
| ---- | ----- |
| 男   | [3]   |

**trem**：称为18、20trem

**posting list**：[1,3]就是posting list,posting是一个int数组，存储了索引符合某个trem的文档id

**term dictionary**：排序后的trem

​	假设我们有很多个term，比如：

​			Carla,Sara,Elin,Ada,Patty,Kate,Selena

​	如果按照这样的顺序排列，找出某个特定的term一定很慢，因为term没有排序，需要全部过滤一遍才能找出特定的term。排序之后就变成了：

​			Ada,Carla,Elin,Kate,Patty,Sara,Selena

这样我们可以用二分查找的方式，比全遍历更快地找出目标的term。这个就是 term dictionary。

**term index**：为减少term dictionary磁盘的读取，将数据存储在内存中衍生出term index。如下：

​	A开头的term ……………. Xxx页

​	C开头的term ……………. Xxx页

​	E开头的term ……………. Xxx页

**通过Terms Index(.tip)能够快速地在Terms Dictionary(.tim)中找到你的想要的Term，以及它对应的Postings文件指针与Term在Segment作用域上的统计信息。**

实际的term index是一棵trie 树。

![1554362896(1)](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\1554362896(1).png)

例子是一个包含 "A", "to", "tea", "ted", "ten", "i", "in", 和 "inn" 的 trie 树。这棵树包含所有的term，它包含的是term的一些前缀。通过term index可以快速定位到term dictionary的某一个offset，然后从这个位置再往后查找。

![1554363227(1)](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\1554363227(1).png)

term Index(压缩) -> Term Dictionary(二分查找) -> Posting List(使用跳表SkipList)	

term index以树的形式缓存在内存中。从term index查找对应的term dictionary的bolck位置之后，再去磁盘查找term，大大减少磁盘的reandom access次数。

所以给定的查询过滤条件 age=18的过程就是先从term index找到18所在的term dictionary 的大概位置，然后再从term dictionary里精确找到18这个teram，然后得到posting list或者一个指向posting list位置的指针。

#### 联合索引查询：

![1554685500(1)](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\1554685500(1).png)

![10](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\10.png)

从概念上来说，对于一个很长的 posting list，比如：

[1,3,13,101,105,108,255,256,257]

我们可以把这个 list 分成三个 block：

[1,3,13 ]，[101,105,108]，[255,256,257]

然后可以构建出 skip list 的第二层：

[1,101,255]

1,101,255 分别指向自己对应的 block。这样就可以很快地跨 block 的移动指向位置了。

 比如一个词对应的文档id列表为[73, 300, 302, 332,343, 372] ，id列表首先要从小到大排好序；第一步增量编码就是从第二个数开始每个数存储与前一个id的差值，即300-73=227，302-300=2。。。，一直到最后一个数；第二步就是将这些差值放到不同的区块，下面示例为了方便展示使用了3个区块，即每3个数一组；第三步位压缩，计算每组3个数中最大的那个数需要占用bit位数，比如30、11、29中最大数30最小需要5个bit位存储，这样11、29也用5个bit位存储，这样才占用15个bit，不到2个字节，压缩效果很好



30的5个bit位来源

0000  0
1248  16

16+8+4+2=30





![11](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\11.png)

![12](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\12.png)

#### Roaring bitmaps

​	上图的步骤：

1. 我们将 32-bit 的范围 ([0, n)) 划分为 2^16 个桶，每一个桶有一个 Container 来存放一个数值的低16位；
2. 在存储和查询数值的时候，我们将一个数值 k 划分为高 16 位`(k / 2^16)`和低 16 位`(k % 2^16)`，取高 16 位找到对应的桶，然后在低 16 位存放在相应的 Container 中；
3. 容器的话， RBM 使用两种容器结构： Array Container 和 Bitmap Container。Array Container 存放稀疏的数据，Bitmap Container 存放稠密的数据。即，若一个 Container 里面的 Integer 数量小于 4096，就用 Short 类型的有序数组来存储值。若大于 4096，就用 Bitmap 来存储值。

![17](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\17.png)

# 0x03 举个栗子

现在我们要将 821697800 这个 32 bit 的整数插入 RBM 中，整个算法流程是这样的：

1. 821697800 对应的 16 进制数为 30FA1D08， 其中高 16 位为 30FA， 低16位为 1D08。
2. 我们先用二分查找从一级索引（即 Container Array）中找到数值为 30FA 的容器（如果该容器不存在，则新建一个），从图中我们可以看到，该容器是一个 Bitmap 容器。
3. 找到了相应的容器后，看一下低 16 位的数值 1D08，它相当于是 7432，因此在 Bitmap 中找到相应的位置，将其置为 1 即可。

然后换一个数值插入，比如说 191037，它的 16 进制的数值是 0002EA3D ，插入流程和前面的例子一样，不同的就在于， 高 16 位对应的容器是一个 Array Container，我们仍然用二分查找找到相应的位置再插入即可。



>
>
> 若一个 Container 里面的 Integer 数量小于 4096，就用 Short 类型的有序数组来存储值。若大于 4096，就用 Bitmap 来存储值。  

先解释一下为什么这里用的 4096 这个阈值？因为一个 Integer 的低 16 位是 2Byte，因此对应到 Arrary Container 中的话就是 2Byte * 4096 = 8KB；同样，对于 Bitmap Container 来讲，2^16 个 bit 也相当于是 8KB。



在lucene中会采用下列顺序进行合并：

1. 在termA开始遍历，得到第一个元素docId=1
2. Set currentDocId=1
3. 在termB中 search(currentDocId) = 1 (返回大于等于currentDocId的一个doc),

因为currentDocId ==1，继续
如果currentDocId 和返回的不相等，执行2，然后继续

1. 到termC后依然符合，返回结果
2. currentDocId = termC的nextItem
3. 然后继续步骤3 依次循环。直到某个倒排链到末尾。

整个合并步骤我可以发现，如果某个链很短，会大幅减少比对次数，并且由于SkipList结构的存在，在某个倒排中定位某个docid的速度会比较快不需要一个个遍历。可以很快的返回最终的结果。从倒排的定位，查询，合并整个流程组成了lucene的查询过程，和传统数据库的索引相比，lucene合并过程中的优化减少了读取数据的IO，倒排合并的灵活性也解决了传统索引较难支持多条件查询的问题。

**BKDTree**
在lucene中如果想做范围查找，根据上面的FST模型可以看出来，需要遍历FST找到包含这个range的一个点然后进入对应的倒排链，然后进行求并集操作。但是如果是数值类型，比如是浮点数，那么潜在的term可能会非常多，这样查询起来效率会很低。所以为了支持高效的数值类或者多维度查询，lucene引入类BKDTree。BKDTree是基于KDTree，对数据进行按照维度划分建立一棵二叉树确保树两边节点数目平衡。在一维的场景下，KDTree就会退化成一个二叉搜索树，在二叉搜索树中如果我们想查找一个区间，logN的复杂度就会访问到叶子结点得到对应的倒排链。如下图所示：



如果是多维，kdtree的建![1555032624(1)](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\1555032624(1).png)立流程会发生一些变化。
比如我们以二维为例，建立过程如下：

1. 确定切分维度，这里维度的选取顺序是数据在这个维度方法最大的维度优先。一个直接的理解就是，数据分散越开的维度，我们优先切分。
2. 切分点的选这个维度最中间的点。
3. 递归进行步骤1，2，我们可以设置一个阈值，点的数目少于多少后就不再切分，直到所有的点都切分好停止。

下图是一个建立例子：

![1555032664(1)](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\1555032664(1).png)

BKDTree是KDTree的变种，因为可以看出来，KDTree如果有新的节点加入，或者节点修改起来，消耗还是比较大。类似于LSM的merge思路，BKD也是多个KDTREE，然后持续merge最终合并成一个。不过我们可以看到如果你某个term类型使用了BKDTree的索引类型，那么在和普通倒排链merge的时候就没那么高效了所以这里要做一个平衡，一种思路是把另一类term也作为一个维度加入BKDTree索引中。





FST（Finite State Transducer）

​	1）空间占用小。通过对词典中单词前缀和后缀的重复利用，压缩了存储空间；

​	2）查询速度快。O(len(str))的查询时间复杂度。

​	3）**快速试错**，即是在FST上找不到可以直接跳出不需要遍历整个Dictionary。类似于BloomFilter的作用。

对

mon -> 2
tues -> 3
thurs -> 5

tyes  -> 99

这3个单词进行插入构建FST（注：必须已排序）。

1)插入mon -> 2

![13](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\13.png)

2)tues -> 3

![14](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\14.png)

3)thurs -> 5

​	tues和thurs有共同的前缀t

![15](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\15.png)

4)tyes-> 99

![16](E:\develop\git_workspase\zhengjy-demo\zhengjy-demo-deploy\src\main\resources\file\md_img\es\16.png)

ES
​	分配文档到不同的容器与分片中，文档可以存储在一个或多个节点
​	按集群节点来均衡分配这些分片，从而对索引和搜索过程进行负载均衡
​	复制每个分片以支持数据冗余，从而防止硬件故障导致数据丢失
​	将集群的任意一个请求路由到存有相关数据节点
​	集群扩容时无缝整合新节点，重新分配分片以便从离群节点恢复
​	

	我们可以将请求发送到集群中任何节点，每个节点都知道任意文档所在的位置，并且能够将我们的请求直接转发到存储我们所需文档的节点。
	无论我们将请求发送到那个节点，它都能负责从各个包含我们所需文档的节点收集数据，将最终结果返回客户端。
	
	每个节点都知道文档的位置，任意一个节点收到数据，都会找到对应的文档的节点，然后处理数据返回。
	
	全文检索
		全文检索主要针对非结构化数据，主要有两种方法：
			1)顺序扫描
				如我们想要在成千上万的文档中，查找包含某一字符串的所有文档，顺序扫描秀逐个扫描每个文档，并且每个文档都是从头看到尾，如果找到，就继续下一个，
				直至遍历所有的文档；这种方法适合应用到数据量较小的场景。
			2)全文检索
				结构化数据的查询方式，例如数据库的查找方式，我们知道数据库中构建索引极大程度的提高出现速度，这是因为结构化数据有一定程度固定的结构使得我们
				可以采用某些搜索算法加速查询速度，既然如此，为什么我们不可以尝试在非结构化数据中，将一部分结构化信息抽取处理，重新组织，然后针对这部分有
				结构的数据进行索引建立，从而达到加速查询的目的。
	
	正向索引：从文档中查找字符串，关系型数据库使用的是正向索引
	反向索引：从字符串查找文档 lucene使用的是反向索引



==============================================



分配文档到不同的容器与分片中，文档可以存储在一个或多个节点
​	按集群节点来均衡分配这些分片，从而对索引和搜索过程进行负载均衡
​	复制每个分片以支持数据冗余，从而防止硬件故障导致数据丢失
​	将集群的任意一个请求路由到存有相关数据节点
​	集群扩容时无缝整合新节点，重新分配分片以便从离群节点恢复
​	

	我们可以将请求发送到集群中任何节点，每个节点都知道任意文档所在的位置，并且能够将我们的请求直接转发到存储我们所需文档的节点。
	无论我们将请求发送到那个节点，它都能负责从各个包含我们所需文档的节点收集数据，将最终结果返回客户端。
	
	每个节点都知道文档的位置，任意一个节点收到数据，都会找到对应的文档的节点，然后处理数据返回。
	
	全文检索
		全文检索主要针对非结构化数据，主要有两种方法：
			1)顺序扫描
				如我们想要在成千上万的文档中，查找包含某一字符串的所有文档，顺序扫描秀逐个扫描每个文档，并且每个文档都是从头看到尾，如果找到，就继续下一个，
				直至遍历所有的文档；这种方法适合应用到数据量较小的场景。
			2)全文检索
				结构化数据的查询方式，例如数据库的查找方式，我们知道数据库中构建索引极大程度的提高出现速度，这是因为结构化数据有一定程度固定的结构使得我们
				可以采用某些搜索算法加速查询速度，既然如此，为什么我们不可以尝试在非结构化数据中，将一部分结构化信息抽取处理，重新组织，然后针对这部分有
				结构的数据进行索引建立，从而达到加速查询的目的。
	
	正向索引：从文档中查找字符串，关系型数据库使用的是正向索引
	反向索引：从字符串查找文档 lucene使用的是反向索引


​	
	索引创建步骤：
		1) 将文档交给切词组件(Tokenizer)进行切词处理
			将文档中的一个个的单词切割出来，同时消除一些无意义的词，如标点符号、英文中的the和is、中文中的"的"等。
		2)将得到的词元(Token)交给语言处理组件
			主要针对第一步生成的词元进行自然语言同化处理，比如针对cars词元，同时生成新的词car;Student词元中的大写字母小写化，这样能够实现一个b不区分student大小写搜索。
		3)将得到的词(Term)交给索引组件
			3.1)利用得到的词创建一个词典
			3.2)对字典按字母顺序进行排序
			3.3)合并相同的词并建立倒排链表
		4)由索引组件针对词建立索引
			针对最后生成的词典，进行索引的建立，至此我们发现根据搜索词，我们可以立即找到对应的文档
	
	搜索步骤：
		1)用户输入查询语句
			 比如用户输入：lucene AND learned NOT hadoop	--------->   	用户想要查找包含lucene和learned，但是不包含hadoop的文档
		2)对查询语句进行词法分析、语法分析、语言处理
			2.1)此法分析主要用来识别单词和关键字
				如针对上述用户的输入，经过此法分析，得到单词为lucene、learned、hodoop，关键词为AND、NOT
			2.2)语法分析主要根据查询语句生词一颗语法树
				受限进行语法判断，如果不满足语法要求，则直接报错
			2.3)语法处理和索引建立过程中的语法处理一样
				如将learned变成learn,经过处理得到以下结果
		3)搜索索引，得到符合语法树的文档
			首先在反向索引链表里，找到包含lucene、learn和hadoop得到文档，其次，对包含lucene和learn的文档进行求合集操作，得到
			同时包含两者的文档链条，然后将得到合集后的链表与Hadoop的文档链表进行差集操作，去除包含hadoop的文档，最后此文档
			链表就是我们要找的文档
		4)根据得到的文档进行语句相关性，对结果进行排序与展示
			
	分片：
		一个索引的数据保存在多个分片中，相当于水平分表。一个分片是一个lucene的实例，它本身就是一个完整的搜索引擎。
		分片是数据的容器，文档保存在分片内，分片被分配到集群内的各个节点。当集群规模扩大或缩小，es自动的在各节点中迁移分片，使得数据仍然均匀分布在集群中。


​		
	在两次fsyn操作之间，存储在内存和文件系统缓存中的文档是不安全的，一旦出现宕机这些文档就会丢失。所以es引入translog来记录两次fsync之间所有操作，这样机器
	恢复，es可以根据translog进行还原。
	translog本身是文件，存在内存中，如果发生宕机就会丢失。es会在每隔5秒时间或一次写入请求完成将translog写入磁盘。可以认为一个对文档的操作一旦写入磁盘便是
	安全的可复原的。因此只有在当前操作记录被写入磁盘，es才会将操作成功返回给客户端。


​	
	第一步是广播请求到索引中每个节点的分片拷贝。查询请求可以被某个主分片或副分片处理，协调节点将在之后的请求中轮询所有的分片拷贝来分摊负载。
	每个分片将会在本地构建一个优先级队列。如果客户端要求返回结果集排序中从第from名开始的数量为size的结果集，则每个节点都需要生成一个from+size大小的结果集，
	因此优先级队列的大小也是from+size。分片仅会返回一个轻量级的结果给协调节点，包含结果集中的每一个文档的ID和进行排序所需要的信息。
	协调节点会将所有分片的结果汇总，并进行全局排序，得到最终的查询排序结果。此时查询阶段结束。


​	
​	
	es使用倒排序索引存储索引
		存储term使用的是FST算法存储来压缩存储空间
		使用跳跃表索引来搜索文档id
		使用Frame Of Reference压缩文档、 bitset 合并压缩文档


​		
	Lucene把用于存储Term的索引文件叫Terms Index
	通过Terms Index(.tip)能够快速地在Terms Dictionary(.tim)中找到你的想要的Term，以及它对应的Postings文件指针与Term在Segment作用域上的统计信息。
	
	由于term 分词后数据量非常大无法放入内存，使用term index FTS算法来进行压缩term ，可以放入内存。
	
	FST即不能知道某个Term在Dictionary(.tim)文件上具体的位置，也不能仅通过FST就能确切的知道Term是否真实存在。它只能告诉你，查询的Term可能在这些Blocks上，
	到底存不存在FST并不能给出确切的答案，因为FST是通过Dictionary的每个Block的前缀构成，所以通过FST只可以直接找到这个Block在.tim文件上具体的File Pointer，并无法直接找到Terms。
	
	 Terms Index是Dictionary的索引，它采用 了FST结构。


#### <https://zhuanlan.zhihu.com/p/33671444>	

​	
​	
​	
​	
​	
​	
​	