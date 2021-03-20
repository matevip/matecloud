> 本环境以本地window10环境为准



## NodeJS安装
### 1、下载NodeJS
> 博主版本: node-v12.16.3-win-x64 解压版本



- 创建**node_cache **文件夹
- 创建**node_modules **文件夹

![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616172279147-6af3f78b-4394-4d2d-8aea-fe2ef717871b.png#align=left&display=inline&height=227&margin=%5Bobject%20Object%5D&name=image.png&originHeight=454&originWidth=708&size=51479&status=done&style=none&width=354)
### 2、配置NodeJS环境
> NODE_HOME
> D:\java\nodejs\node-v12.16.3-win-x64



![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616172410514-fd14cd96-4a41-4b2c-96fe-644cc50f33ae.png#align=left&display=inline&height=470&margin=%5Bobject%20Object%5D&name=image.png&originHeight=666&originWidth=632&size=47603&status=done&style=none&width=446)
![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616172706634-cad6e8c5-64d6-4f1f-9149-ebb61d267638.png#align=left&display=inline&height=570&margin=%5Bobject%20Object%5D&name=image.png&originHeight=666&originWidth=632&size=59602&status=done&style=none&width=541)


### 3、设置NodeJS缓存与更换阿里中央仓库



- global  npm全局安装位置
- cache  缓存路径
```shell
npm config set cache "D:\java\nodejs\node-v12.16.3-win-x64\node_cache"
npm config set prefix "D:\java\nodejs\node-v12.16.3-win-x64\node_global"
```

- 更换仓库源
```shell
npm config set registry http://registry.npm.taobao.org/
# 验证
npm config get registry
npm info underscore
```

- 测试是否安装成功
```shell
node -v
npm -v
```
![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616172872992-64db192b-091a-4401-b429-fbfee0c04130.png#align=left&display=inline&height=282&margin=%5Bobject%20Object%5D&name=image.png&originHeight=282&originWidth=456&size=10769&status=done&style=none&width=456)




## 项目运行


### 1、项目下载
```shell
下载地址：
https://gitee.com/matevip/artemis
```


### 2、项目编译
```shell
# 安装依赖
npm install
# 或者
yarn install
```


![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616173127514-6f633e8d-ec41-40ac-a336-00b238daa44c.png#align=left&display=inline&height=377&margin=%5Bobject%20Object%5D&name=image.png&originHeight=519&originWidth=993&size=27445&status=done&style=none&width=722)
![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616173194350-0ba53bc7-7fbb-43cb-beea-a39eaaf38eea.png#align=left&display=inline&height=378&margin=%5Bobject%20Object%5D&name=image.png&originHeight=519&originWidth=993&size=73150&status=done&style=none&width=724)
### 3、启动服务


```shell
# 启动服务 localhost:9528
npm run serve
## <2.2.8
npm run dev
# 或者
yarn serve
## <2.2.8
yarn dev
```
![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616173289426-ac8800f2-d5ca-4d07-9524-8fa842b7012e.png#align=left&display=inline&height=390&margin=%5Bobject%20Object%5D&name=image.png&originHeight=519&originWidth=993&size=72436&status=done&style=none&width=746)
![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616173395039-75b5c5ca-1598-46a8-a431-8cd09888ee53.png#align=left&display=inline&height=349&margin=%5Bobject%20Object%5D&name=image.png&originHeight=698&originWidth=1420&size=391483&status=done&style=none&width=710)




### 4、登录
| 账号 | 密码 |
| --- | --- |
| admin | matecloud |





![image.png](https://cdn.nlark.com/yuque/0/2021/png/2654448/1616173545777-bc9caf97-60e6-4f4c-ab8a-920930b42ece.png#align=left&display=inline&height=825&margin=%5Bobject%20Object%5D&name=image.png&originHeight=825&originWidth=1378&size=138123&status=done&style=none&width=1378)
