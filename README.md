# miya

## 系统架构

- 后期补上架构图

### 模块介绍

- 采用alibaba nacos 注册中心与配置中心
- miya-gataway 网关,同一处理请求,连接各个服务
- miya-auth 授权认证中心,管理令牌
- miya-common 通用模块
- miya-system 系统管理(权限)

### 端口约定

| 服务模块     | 端口 |
| ------------ | ---- |
| nacos-server | 8001 |
| miya-auth    | 8101 |
| miya-system  | 8201 |
| miya-gataway | 8301 |



### spring cloud 版本依赖推荐

| Spring Cloud Version        | Spring Cloud Alibaba Version | Spring Boot Version |
| --------------------------- | ---------------------------- | ------------------- |
| Spring Cloud Hoxton.SR3     | 2.2.1.RELEASE                | 2.2.5.RELEASE       |
| Spring Cloud Hoxton.RELEASE | 2.2.0.RELEASE                | 2.2.X.RELEASE       |
| Spring Cloud Greenwich      | 2.1.2.RELEASE                | 2.1.X.RELEASE       |
| Spring Cloud Finchley       | 2.0.2.RELEASE                | 2.0.X.RELEASE       |
| Spring Cloud Edgware        | 1.5.1.RELEASE                | 1.5.X.RELEASE       |

---
