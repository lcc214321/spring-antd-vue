# 项目说明

- 采用SpringBoot 2.1.4、MyBatis、Shiro框架，开发的一套通用基础开发平台，门槛低，开发高效。
- 暂时只支持mysql, 需要可扩展对其他类型数据库的支持。

# 使用的maven包介绍

maven包都已升级到最新版本(2019-04-20), 下面是各个maven包的简介:


- spring-boot-starter-web: 2.1.4, 这个不用介绍了。使用Spring Boot的人都知道。
- junit: 测试包
- spring-boot-starter-test: spring boot的测试启动包
- spring-boot-starter-aop: string aop启动包
- spring-context-support: Spring context的扩展支持，用于MVC方面。
- spring-boot-starter-data-redis: (todo 检查最新版本是否有问题)
- spring-boot-configuration-processor: 用于处理.xml, .properties中的配置文件，再在你的配置类开头加上@PropertySource("classpath:your.properties")，其余用法与加载yml的配置一样。
- jedis: redis客户端
- mysql-connector-java: mysql驱动器
- druid: alibaba的数据库连接池
- mybatisplus: mybatis plus
- fastjson:
- commons-lang3:
- commons-fileupload
- commons-io:
- commons-codec: General encoding/decoding algorithms (for example phonetic, base64, URL).
- joda-time:
- springfox-swagger2:

# 项目架构

- eaton-common: 公共jar包, 其他模块都可以使用它。 
- eaton-biz: 业务jar包, 各个api端都会使用它。
- eaton-platform: 平台后管api
- eaton-api: 应用api。
- eaton-flux: Spring reactive Flux的应用模块.


# todo list


# 参考链接

- [apache commons](http://commons.apache.org/)
