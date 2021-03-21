> 本环境以本地window10环境为准



## 1、Redis安装
> 下载文章后面提供的绿色安装包解压即可
> 蓝奏下载名:![](https://cdn.nlark.com/yuque/0/2021/gif/2654448/1616177381743-f9abd126-2c42-4d65-89c2-bf97253cf4fa.gif#align=left&display=inline&height=16&margin=%5Bobject%20Object%5D&originHeight=16&originWidth=16&size=0&status=done&style=none&width=16) Redis-x64-3.2.7z

![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616175911839-5e9ab678-2755-4039-9d16-a89ac0d83302.png#align=left&display=inline&height=489&margin=%5Bobject%20Object%5D&name=image.png&originHeight=489&originWidth=624&size=60306&status=done&style=none&width=624)
## 2、Nacos安装
> 下载文章后面提供的绿色安装包解压即可
> 蓝奏下载名:![](https://cdn.nlark.com/yuque/0/2021/gif/2654448/1616177359146-4a043ae4-46ea-49e8-bdef-3508cea6848c.gif#align=left&display=inline&height=16&margin=%5Bobject%20Object%5D&originHeight=16&originWidth=16&size=0&status=done&style=none&width=16) nacos-server-1.4.1.7z

![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616175995018-55a02391-e870-4216-ad32-ad56d84a3790.png#align=left&display=inline&height=370&margin=%5Bobject%20Object%5D&name=image.png&originHeight=370&originWidth=777&size=40545&status=done&style=none&width=777)


### 1、新建nacos数据库
nacos-mysql.sql 在nacos的配置文件夹下面nacos-mysql.sql 在nacos的配置文件夹下面nacos-mysql.sql 在nacos的配置文件夹下面
```shell
创建数据库：matecloud-nacos

--1、 创建nacos库 SQL语句
create database `matecloud-nacos` default character set utf8 collate utf8_bin;

--2、执行nacos的数据库
sql语句在nacos-server-1.4.1\nacos\conf\nacos-mysql.sql


```

![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616176347347-39ffa606-e388-4a45-b160-1e4daa384129.png#align=left&display=inline&height=415&margin=%5Bobject%20Object%5D&name=image.png&originHeight=415&originWidth=771&size=25454&status=done&style=none&width=771)


![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616176824706-aa324977-abb5-4c77-bed6-97d125b621ab.png#align=left&display=inline&height=241&margin=%5Bobject%20Object%5D&name=image.png&originHeight=241&originWidth=453&size=17200&status=done&style=none&width=453)

### 2、nacos连接mysql数据库
> 修改配置nacos数据库配置文件 conf/application.properties
> 23——25行 改为自己的nacos数据库与账户密码

![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616176188019-413750f1-13a7-449b-81de-1f45ee49d387.png#align=left&display=inline&height=598&margin=%5Bobject%20Object%5D&name=image.png&originHeight=598&originWidth=1065&size=89477&status=done&style=none&width=1065)

### 3、nacos运行
![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616176929439-54eec588-b285-4c08-b221-206364acad6b.png#align=left&display=inline&height=371&margin=%5Bobject%20Object%5D&name=image.png&originHeight=371&originWidth=745&size=34054&status=done&style=none&width=745)
### 4、登录nacos导入配置文件
> 蓝奏文件名:![](https://cdn.nlark.com/yuque/0/2021/gif/2654448/1616177337891-4b7199f6-d16c-4ac6-89cf-9c51330c9da9.gif#align=left&display=inline&height=20&margin=%5Bobject%20Object%5D&originHeight=20&originWidth=20&size=0&status=done&style=none&width=20) matecloud-nacos直接导入.zip



![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616177262154-2fb57fe3-7301-4fae-b88f-80dca75930be.png#align=left&display=inline&height=625&margin=%5Bobject%20Object%5D&name=image.png&originHeight=625&originWidth=1387&size=103371&status=done&style=none&width=1387)

![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616177277370-7c12ed28-20c7-4ef2-86d2-ee4a01ad4756.png#align=left&display=inline&height=550&margin=%5Bobject%20Object%5D&name=image.png&originHeight=550&originWidth=1194&size=64298&status=done&style=none&width=1194)


### 4、修改mate-local.yaml 配置文件数据库账户密码
> 如果账户密码为root可以跳过4步骤

## ![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616178101571-82f58a34-1df7-465a-b95b-fd8dcba8747a.png#align=left&display=inline&height=578&margin=%5Bobject%20Object%5D&name=image.png&originHeight=578&originWidth=1116&size=60693&status=done&style=none&width=1116)
## 

## 3、项目运行


### 1、idea导入项目
![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616177505704-58679758-6abf-4f40-b059-3c54cb31f94d.png#align=left&display=inline&height=952&margin=%5Bobject%20Object%5D&name=image.png&originHeight=952&originWidth=1540&size=145461&status=done&style=none&width=1540)

### 2、导入项目数据库
> matecloud\doc\sql\matex_schema.sql

```sql
--1、创建matex核心数据库 SQL
create database `matex` default character set utf8mb4 collate utf8mb4_general_ci;
--2、导入matex_schema.sql
```
![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616177927303-ed121743-8aaf-40b5-aa96-b69849416c5e.png#align=left&display=inline&height=276&margin=%5Bobject%20Object%5D&name=image.png&originHeight=276&originWidth=310&size=14390&status=done&style=none&width=310)

![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616177889449-7333654b-8bac-4cd1-a979-63f584ac156f.png#align=left&display=inline&height=414&margin=%5Bobject%20Object%5D&name=image.png&originHeight=414&originWidth=509&size=45286&status=done&style=none&width=509)


### 3、运行项目
> 运行项目顺序

![](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616088952299-c295543a-20ad-4e6e-9a46-c4ed6eeda014.png#align=left&display=inline&height=125&margin=%5Bobject%20Object%5D&originHeight=125&originWidth=359&status=done&style=none&width=359)




### 4、启动注意事项
> 选择启动运行环境 

#### 第一种方案

![](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616089279060-df8fd39b-56d9-4a24-af04-fa05b0b6254f.png?x-oss-process=image%2Fresize%2Cw_543#align=left&display=inline&height=357&margin=%5Bobject%20Object%5D&originHeight=357&originWidth=543&status=done&style=none&width=543)


#### 第二种方案
![](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616089433712-286f8217-2db5-4281-b95d-ead3bf61ea77.png?x-oss-process=image%2Fresize%2Cw_633#align=left&display=inline&height=307&margin=%5Bobject%20Object%5D&originHeight=307&originWidth=633&status=done&style=none&width=633)




## 4、文件下载地址
[https://wws.lanzous.com/b025zdd5e](https://wws.lanzous.com/b025zdd5e)
密码:7tv3
