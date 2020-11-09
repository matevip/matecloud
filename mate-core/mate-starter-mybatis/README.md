## 组件说明

本组件是扩展打印SQL日志组件，引用了mica的代码，仅供大家参考。

源码参考：
https://gitee.com/596392912/mica

## 打印日志方案
### 简单实现
介绍文章：https://blog.csdn.net/bufegar0/article/details/108428445
```yaml
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```
此方法可以打印sql，但显示不美观
### 完美实现
好好研究下代码

