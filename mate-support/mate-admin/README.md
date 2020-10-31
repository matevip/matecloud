# MATE-ADMIN钉钉告警配置方案

## SDK下载

#### Java SDK 下载
[Java SDK下载]https://open-doc.dingtalk.com/microapp/faquestions/vzbp02

#### 本地jar包
项目目录：doc/lib/taobao-sdk-20200415.jar

## 配置项

#### application-@spring.active@.yml

```yaml
spring:
  boot:
    admin:
      notify:
        dingding:
          token: ${Your DingDing Robot Token}
```