系统开发启动方式：
以ssms工作目录为根目录，在ide中创建一个maven配置，命令填写“tomcat7:run”即可。

# 一、数据库规约

数据库命名规则采用电厂规范。**严禁使用拼音首字母缩写！如需使用拼音，则宁可使用全拼！**

## 1.1 表名

|缩略词            |作用         |
|------------------|-------------|
|SYS_              |系统表       |
|GEN_              |通用功能表   |

## 1.2 字段名

|缩略词            |作用
|------------------|-----------------
|SID               |唯一编号
|C_                |表示字符串
|R_                |引用表唯一编号，SID
|S_                |引用表，字符串。冗余设计。
|N_                |数值型
|T_                |日期型
|Z_                |引用编码，非指向SID，选项值，KEY
|B_                |bool值



-----------------------------------------------------------


# 二、编码规范

## 2.1 编码规范

内容待定。

## 2.2 注释规范
每个文件应带有固定的文件头，格式如下：
```
/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Config.java
 * 创建时间：2015-03-09
 * 创建用户：张铮彬
 */
```

对于主要的代码块，应有详细的步骤注释。当逻辑调整时应修改相应的注释内容。如：
```
// 1. 执行打开数据库操作
// 2. 执行SQL命令
// 3. 关闭连接
```

对于修改他人代码，应注释修改原因。并在注释背后加上签名与日期。签名名字可以为邮箱、姓名、自己喜欢使用的网络名等，但一旦确定便不可更改。建议的签名方式如下：
```
// 修改了...          by 张铮彬(cnzgray@qq.com)#2015-3-30
```

## 2.3 文本编码
所有文件编码统一采用UTF-8，非特殊情况，禁止使用GBK编码。

## 2.4 缩进问题
一律采用空格缩进，禁止使用tab缩进。

## 2.5 java代码风格
1. 应该保持同行的代码
```
// if语句非多行情况，其后代码应保持同行
if (2 < 3) return;
else if (2 > 3) return;
else return;
```

# 2.6 其他规约
1. 任何异常均派生自`com.lanstar.common.exception.WebException`类型。

# 三、缩略词表

在java代码编写过程中，方法名、字段名、变量、参数尽可能写全名。如需使用缩略词则应补充下表。

|缩略词       |描述
|-------------|----------------------
|SYS          |system，系统


# 四、页面调度规则

## 4.1 资源类
资源文件以/resource/开头的不参与URL解析。

## 4.2 业务类
### 名词表：
|名称                 |说明
|---------------------|-----------------------
|module               |模块
|controller           |控制器
|action               |控制器中的行为
|render               |呈现页面的方法
|view                 |视图

### 1) URL规则
业务类型根据 “模块/控制器/方法.输出类型?参数”  规则来构造URL，**字母必须为小写**。例如：
```
http://localhost/e/a02/index.html
http://localhost/xxx/a02/tree.json?sid=1
```
其中实际有效部分为`/e/a02/index.html`，解析结果为：
```
e=>大模块[module]
a02=>a02Controller[controller]
index=>indexAction[action]
.html=>HTMLRender[render]
```
*URL规则必须严格按照以上规则进行构造，不符合规则则一律不予解析。*


### 2) View目录结构
必须按如下结构放置目录:`views/[module]/[controller]/[action].ftl`，如:
```
views/e/a02/index.ftl
```
1. 所有模板必须放在views目录下
2. 按"模块/控制器/模板文件"结构放置
3. 全部小写，不可大写！

### 3) Controller规则
Controller规则如下:
1. public
2. 类名为[name]Controller，其中name必须小写，后面必须跟上Controller。
3. 继承自Controller类型
4. 必须放在`com.lanstar.controller`包下。*其下是否可以放子包待定*。
示例如下：
```
public class a02Controller extends Controller {
    // 添加各种action
}
```

### 4) Action规则
Action规则如下:
1. public
2. 返回值为`ViewAndModel`类型或其子类型
3. 参数有且只有一个，类型为`HandleContext`
代码示例如下:
```java
package com.lanstar.controller;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.controller.Controller;
import com.lanstar.core.handle.HandlerContext;
public class a02Controller extends Controller {
    public ViewAndModel index(HandlerContext context) {
        // 业务处理
        // 返回结果
        return context.returnWith(null);
    }
}
```
