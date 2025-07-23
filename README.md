# spring-boot 练手项目

---

## 基础

### 项目架构与模块交互

本项目采用典型的分层架构，主要模块包括：

- **Controller 层（控制器）**  
  负责接收前端 HTTP 请求，调用 Service 层处理业务逻辑，并将结果返回给前端。  
  例如：`UserController` 提供用户的增删改查 RESTful API。

- **Service 层（服务）**  
  封装具体的业务逻辑，负责数据校验、业务处理等，并调用 Mapper 层与数据库交互。  
  例如：`UserService` 实现用户的增删改查业务。

- **Mapper 层（数据访问）**  
  定义数据库操作接口，使用 MyBatis 注解或 XML 文件实现 SQL 语句。  
  例如：`UserMapper` 负责用户表的增删改查 SQL。

- **Entity 层（实体）**  
  定义与数据库表结构对应的数据实体类。  
  例如：`User` 实体类对应 user 表。

- **配置文件（application.yml）**  
  统一管理数据库、MyBatis 及其他全局配置。

**模块交互流程：**  
前端请求 → Controller → Service → Mapper → 数据库  
数据库返回数据 → Mapper → Service → Controller → 前端

---

### 用到的注解及其作用

- `@RestController`：标记控制器类，自动将返回值序列化为 JSON。
- `@RequestMapping`、`@GetMapping`、`@PostMapping`：映射 HTTP 路径和请求方法。
- `@Autowired`：自动注入依赖对象。
- `@Service`：标记服务类，纳入 Spring 容器管理。
- `@Mapper`：标记 MyBatis Mapper 接口，自动生成实现类。
- `@Select`、`@Insert`、`@Update`、`@Delete`：MyBatis 注解，直接在接口方法上编写 SQL 语句。
- `@Options`：MyBatis 注解，配置主键自增等选项。
- `@Param`：MyBatis 注解，绑定 SQL 参数名。
- `@Data`：Lombok 注解，自动生成 getter/setter、toString、equals、hashCode 等方法，简化实体类代码。
- `@PathVariable`：绑定 URL 路径中的参数到方法参数。
- `@RequestBody`：将请求体中的 JSON 数据绑定到方法参数。

---

### application.yml 配置说明

该文件用于集中管理项目的数据库和 MyBatis 配置，便于维护和环境切换。

- `spring.datasource.url`：数据库连接地址，包含参数 `allowPublicKeyRetrieval=true` 用于解决 MySQL 8+ 公钥检索问题。
- `spring.datasource.username`/`password`/`driver-class-name`：数据库用户名、密码、驱动类名。
- `mybatis.mapper-locations`：指定 MyBatis Mapper XML 文件的路径，支持批量加载。
- `mybatis.configuration.map-underscore-to-camel-case`：开启数据库字段下划线与 Java 驼峰命名的自动映射。

---

### 各模块功能说明

- **Entity（实体类）**  
  例如 `User`：定义用户的属性（userId、name、email、password、phone、role、status、createdAt），与数据库 user 表字段一一对应。

- **Mapper（数据访问接口）**  
  例如 `UserMapper`：定义用户的增删改查方法，使用 MyBatis 注解直接编写 SQL，实现与数据库的交互。

- **Service（业务逻辑层）**  
  例如 `UserService`：封装用户相关的业务逻辑，对外提供用户的增删改查服务，内部调用 Mapper 层。

- **Controller（控制器层）**  
  例如 `UserController`：对外提供 RESTful API，接收前端请求，调用 Service 层处理业务，并返回结果。

- **配置文件（application.yml）**  
  统一管理数据库连接、MyBatis 配置等，便于环境切换和集中维护。

---

如需进一步了解每个模块的代码实现和具体用法，请参考对应的 Java 源文件和注释。

