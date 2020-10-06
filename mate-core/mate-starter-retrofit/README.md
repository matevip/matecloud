## mate-starter-retrofit

封装了链家出品的基于springboot上应用的retrofit-spring-boot-starter

## 框架开发背景
spring-boot项目中存在大量的http请求调用，然而当时调用都是通过HttpUtils或者RestTemplate实现的，说实话很麻烦，并且不利于统一管理。 因此当时就想通过Java接口的形式来统一搞定这件事。 由于不想引入spring-cloud相关依赖，当时就没有直接用feign，另外更重要的一点就是也想做的更加轻量，因此最终才写了这个框架

## 官方教程

https://github.com/LianjiaTech/retrofit-spring-boot-starter/blob/master/README_CN.md