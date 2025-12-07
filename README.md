# 🛍️ Secondhand Transaction System (二手物品交易系统)

这是一个基于 **Java原生 Servlet + JSP + JDBC** 技术栈开发的简易二手物品交易平台。项目采用标准的 **MVC 分层架构**，实现了用户注册、物品发布、物品列表展示、关键词搜索以及物品删除等核心功能。

---

## 🛠️ 技术栈 (Tech Stack)

| 类别 | 技术/工具 | 备注 |
| :--- | :--- | :--- |
| **后端** | Java (JDK 21), Jakarta Servlet 6.0 | 核心逻辑 |
| **前端** | JSP, JSTL, HTML, CSS | 页面展示 |
| **数据库** | MySQL 8.0 | 数据存储 |
| **构建工具** | Maven | 依赖管理与打包 |
| **服务器** | Apache Tomcat 10.1+ | **必须支持 Jakarta EE 规范** |
| **工具库** | Lombok, MySQL Connector | 简化实体类与数据库连接 |

---

## 🏗️ 系统架构设计 (Architecture)

本项目采用经典的 **MVC (Model-View-Controller)** 设计模式：

### 1. Controller (控制层)
* **`StuffServlet`**: 统一处理物品相关的请求（查询列表、发布、删除、搜索）。
* **`RegisterServlet`**: 处理用户注册请求。

### 2. Service (业务逻辑层)
* **`StuffService`**: 封装物品管理的业务逻辑。
* **`UserService`**: 处理用户注册逻辑，包含密码 MD5 加密。

### 3. DAO (数据访问层)
* **`DBtool`**: 管理数据库连接（JDBC Connection）。
* **`StuffDao`**: 执行物品表的 CRUD 操作。
* **`UserDao`**: 执行用户表的插入操作。

### 4. Entity (实体层)
* **`User`**, **`Stuff`**: 对应数据库表的 POJO 类。

---

## 🧱 项目目录结构

```text
src/main/java
├── Controller  // Servlet 控制器
├── Service     // 业务逻辑
├── DAO         // 数据库操作
├── Entity      // 实体类
└── resources   // 配置文件

webapp
├── index.jsp       // 首页
├── list.jsp        // 物品列表页
├── register.jsp    // 注册页
└── WEB-INF         // web.xml 等配置
```

---

## 💾 数据库设计 (Database Schema)

请在 MySQL 中创建一个名为 `secondhand_db` 的数据库，并执行以下 SQL 语句。

### 1. 数据库创建
```sql
CREATE DATABASE IF NOT EXISTS secondhand_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE secondhand_db;
```

### 2. 建表语句

**用户表 (users)**
```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '加密后的密码'
);
```

**物品表 (stuffs)**
```sql
CREATE TABLE stuffs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL COMMENT '物品名称',
    description TEXT COMMENT '物品描述',
    price DECIMAL(10, 2) COMMENT '价格'
);
```

### 3. 插入测试数据
```sql
INSERT INTO stuffs (title, description, price) VALUES 
('旧电脑', '9成新，急出', 2500.00),
('机械键盘', '青轴，手感很好', 150.50),
('Java编程书', '经典书籍，稍微有点旧', 45.00);
```

---

## 🚀 快速开始 (Quick Start)

### 配置要求
* **JDK**: 17 或 21
* **Maven**: 3.6+
* **MySQL**: 8.0+
* **Tomcat**: 10.0 或更高版本
    > ⚠️ **注意**：由于本代码使用了 `jakarta.servlet`，不支持 Tomcat 9 及以下版本。

---

## 🪜 部署步骤

### 1. 克隆项目
```bash
git clone [https://github.com/YourUsername/SecondhandTransaction.git](https://github.com/YourUsername/SecondhandTransaction.git)
```

### 2. 配置数据库
打开 `src/main/java/DAO/DBtool.java`，修改以下配置以匹配你的本地环境：

```java
private static final String URL = "jdbc:mysql://localhost:3306/secondhand_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
private static final String USER = "your_account";   // 你的数据库账号
private static final String PWD = "your_password";   // 你的数据库密码
```

### 3. 打包项目
在项目根目录下运行 Maven 命令：
```bash
mvn clean package
```
成功后会在 `target` 目录下生成 `SecondhandTransaction-1.0-SNAPSHOT.war`。

### 4. 部署到 Tomcat
1.  将 `.war` 文件复制到 Tomcat 的 `webapps` 目录下。
2.  启动 Tomcat (`bin/startup.sh` 或 `bin/startup.bat`)。

### 5. 访问项目
打开浏览器访问：
[http://localhost:8080/SecondhandTransaction/](http://localhost:8080/SecondhandTransaction/)

---

## 🧪 测试说明 (Testing)

### 1. 访问地址
* **首页**: `http://localhost:8080/SecondhandTransaction/`
* **物品列表/搜索**: `http://localhost:8080/SecondhandTransaction/Stuff`
* **注册页面**: `http://localhost:8080/SecondhandTransaction/register.jsp`

### 2. 功能测试
- [ ] **测试场景 1 (发布)**: 在列表页下方填写名称、描述和价格，点击“发布”，数据应即时出现在列表中，同时可查看数据库中应有对应的更新。
- [ ] **测试场景 2 (搜索)**: 在搜索框输入“电脑”，应只显示包含“电脑”的商品。
- [ ] **测试场景 3 (注册)**: 访问注册页，输入用户名 `testuser` 和密码 `123456`，查看数据库 `users` 表，用户名为 `testuser`，密码应为 MD5 加密后的字符串。

---

## ⚠️ 常见问题 (Troubleshooting)

**Q1: HTTP 500 错误 (UnsupportedClassVersionError)**
> 请确保服务器运行的 JRE 版本 >= 编译项目的 JDK 版本 (推荐统一使用 JDK 21)。

**Q2: 部署在 Linux 上列表为空**
> Linux 下 MySQL 表名区分大小写。请确保代码中 SQL 语句使用的是 `stuffs` (小写) 而不是 `Stuffs`。

**Q3: 中文乱码**
> 1. 确保 `DBtool` 连接字符串中包含 `characterEncoding=utf-8`。
> 2. 确保 Tomcat 的 `server.xml` 配置了 UTF-8 支持。

**Q4: 其他**
> 欢迎 pull requests，看到后会回复。

<br>

**Enjoy coding! ☕**
