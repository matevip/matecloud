## 模块介绍
### 引用xxl-job
地址：https://gitee.com/xuxueli0323/xxl-job

### 部署说明
自行部署xxl-job-admin

### 配置参数修改
````
mate:
  job:
    ## xxl-job-admin地址
    admin:
      addresses: http://localhost:30011/xxl-job-admin
    accessToken: ''
    executor:
      appname: ${spring.application.name}
      address: 127.0.0.1:30007
      ip: 127.0.0.1
      port: 30007
      logPath: /data/applogs/mate-job/jobhandler
      logRetentionDays: 30
````