# Mlib-App

![](https://pan.lmio.xyz/pic/1819afd45528a6d5e6a34f7be747ebd6.jpg)

### 使用的技术栈
#### app
- Kotlin 是一种跨平台、静态类型、面向对象的编程语言，它是一种现代化的语言，可以帮助开发者编写更简洁、更可读、更易于维护的代码。Kotlin 是一种基于 JVM 的语言，与 Java 兼容，并且可以与 Java 代码无缝集成。Kotlin 也可以编译为 JavaScript 或本地代码，因此可以用于多种应用程序开发，包括 Android 应用程序、Web 应用程序和桌面应用程序。

- Gson 是 Google 提供的一个用于处理 JSON 数据的 Java 库。它可以将 Java 对象序列化为 JSON 字符串，也可以将 JSON 字符串反序列化为 Java 对象。Gson 支持复杂的对象图、泛型、自定义序列化和反序列化规则等高级特性，使开发人员可以轻松地将 JSON 数据转换为 Java 对象，或将 Java 对象转换为 JSON 数据。

- Retrofit 是一个用于 Android 和 Java 应用程序的网络请求库。它基于 OkHttp 库构建，可以轻松地进行 HTTP 请求和响应处理。Retrofit 支持多种请求方式，包括 GET、POST、PUT、DELETE 等，还支持自定义请求头、请求参数和请求体。Retrofit 还支持异步请求和 RxJava 响应式编程，使开发人员可以更轻松地处理网络请求和响应。

- Room 是 Android 中的一个持久性库，可以帮助开发人员轻松地访问 SQLite 数据库。它提供了一个类型安全的 API，可以将 SQLite 查询映射到 Java 对象，从而简化了数据库访问。Room 还提供了编译时检查和代码生成功能，可以帮助开发人员避免常见的 SQLite 错误和运行时异常。

- Material 2 是 Google 提供的一组用于 Android 应用程序的 UI 组件和设计指南。它提供了一系列标准化的 UI 控件和布局，可以帮助开发人员创建具有一致性和美观性的 Android 应用程序。Material 2 还提供了许多设计指南和最佳实践，可以帮助开发人员创建符合用户体验标准的应用程序。

#### 后端

- Spring Boot 是 Spring 框架的一个子项目，它提供了一种快速创建基于 Spring 的应用程序的方法。Spring Boot 提供了许多开箱即用的功能，包括自动配置、嵌入式 Web 服务器、安全性、监控和管理等。Spring Boot 还支持许多开发人员常用的技术栈，包括 Spring MVC、JPA、MyBatis、Thymeleaf 等。

#### MyBatis

- MyBatis 是一个用于 Java 应用程序的持久性框架，它可以帮助开发人员轻松地访问数据库。MyBatis 提供了一个简单的 SQL 映射器，可以将 SQL 查询映射到 Java 对象，从而简化了数据库访问。MyBatis 还提供了许多高级特性，包括动态 SQL、批量操作、缓存等。


- Spring Security 是 Spring 框架的一个子项目，它提供了一种安全性框架，可以帮助开发人员保护应用程序免受各种攻击。Spring Security 提供了许多安全性功能，包括身份验证、授权、加密、会话管理等。它还支持许多常见的身份验证和授权机制，包括基于表单、基于 HTTP 基本认证、OAuth 等。


- MySQL 是一个流行的开源关系型数据库管理系统，它支持多种操作系统和编程语言。MySQL 提供了许多高级特性，包括事务处理、存储过程、触发器、视图等，使开发人员可以轻松地进行数据管理和查询。


- Redis 是一个高性能的键值存储数据库，它可以将数据存储在内存中，以提高访问速度。Redis 支持多种数据结构，包括字符串、哈希表、列表、集合等，还提供了许多高级特性，包括事务处理、发布/订阅、Lua 脚本等。Redis 通常用于缓存、会话管理、消息队列等场景。

### 目录结构
```
├─app
│  │  build.gradle.kts
│  │  proguard-rules.pro
│  │
│  └─src
│      ├─androidTest
│      │  └─java
│      │      └─com
│      │          └─lmio
│      │              └─mlib
│      │                      ExampleInstrumentedTest.kt
│      ├─main
│      │  │  AndroidManifest.xml
│      │  ├─java
│      │  │  └─com
│      │  │      └─lmio
│      │  │          └─mlib
│      │  │              │  MainActivity.kt
│      │  │              ├─Dao
│      │  │              ├─entity
│      │  │              ├─services
│      │  │              ├─stores
│      │  │              ├─ui
│      │  │              │  ├─auth
│      │  │              │  ├─home
│      │  │              │  ├─profile
│      │  │              │  └─settings
│      │  │              │
│      │  │              └─utils
│      │  └─res
│      │      ├─drawable
│      │      ├─layout
│      │      ├─menu
│      │      ├─navigation
│      │      ├─raw
│      │      ├─values
│      │      └─xml
│      │              backup_rules.xml
│      │              data_extraction_rules.xml
│      │              root_preferences.xml
```

### 如何开发

- 拉取源代码
```
git clone https://github.com/Clear-Love/Mlib-App.git # app
git clone https://github.com/Clear-Love/mlib.git # 后端
```