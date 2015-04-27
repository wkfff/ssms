系统开发启动方式：
以ssms工作目录为根目录，在ide中创建一个maven配置，命令填写“tomcat7:run”即可。

|名词         |对应英文       |说明
|-------------|---------------|-----------------------
|租户         |tenant         |企业、评审、政府统称为租户

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
|P_                |引用参数，非指向SID，选项值，KEY
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

其他特殊注释:
TODO: + 说明：
如果代码中有该标识，说明在标识处有功能代码待编写，待实现的功能在说明中会简略说明。

FIXME: + 说明：
如果代码中有该标识，说明标识处代码需要修正，甚至代码是错误的，不能工作，需要修复，如何修正会在说明中简略说明。

XXX: + 说明：
如果代码中有该标识，说明标识处代码虽然实现了功能，但是实现的方法有待商榷，希望将来能改进，要改进的地方会在说明中简略说明。

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
|STDTMP       |达标体系模板


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
3. 必须放在`com.lanstar.controller`包下。
4. 控制器必须放在在请求的[module]下。
Url为`/e/a02/index.html`的控制器定义示例如下：
```java
package com.lanstar.controller.e;
public class a02Controller {
    // 添加各种action
    public void index(HandleContext context){

    }
}
```

### 4) Action规则
Action规则如下:
1. public
2. 返回值为`ViewAndModel`类型或其子类型，或者为void。
3. 参数有且只有一个，类型为`HandleContext`
代码示例如下:
```java
package com.lanstar.controller;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.controller.Controller;
import com.lanstar.core.handle.HandlerContext;
public class a02Controller {
    public ViewAndModel index(HandlerContext context) {
        // 业务处理
        // 返回结果
        return context.returnWith();
    }

    public void index(HandlerContext context) {
        // 业务处理
        context.setValue(...);
    }
}
```

**以下情形必须使用ViewAndModel返回值方式:**
1. 使用的模板与方法名不一致时。如
```java
public ViewAndModel index2(HandlerContext context) {
    // 业务处理
    // 返回结果
    return context.returnWith("index");
}
```
2. 值是一个非map对象时。如：
```java
public ViewAndModel index2(HandlerContext context) {
    User user = new User();
    return context.returnWith().set(user);
}
```

### 5) Render规约
目前只支持两种规约输出格式：
1. html，采用Freemarker视图模板文件输出内容。
2. json，输出为json格式，用于标识获取数据。
3. do，输出为json格式，与json进行区别，用于标识执行操作。

值搜索顺序：
Model变量->本地变量 --> request --> url参数 --> session --> servletContext


## 五、租户数据库配置方式
1. 租户数据库配置信息放置于: `webapp/WEB-INF/db`
2. 使用properties文件进行配置（支持xml？）
3. 每个文件表示一个数据库配置

## 六、系统参数使用规则
### 6.1 单值参数
未实现

### 6.2 多值参数
1. 数据命名规则：
    - C_CODE: 参数名字+'_'+KEY。如：`USER_SEX_1`
    - C_VALUE: 名称。如：`男`
2. 模板上使用
```
<#list _USER_SEX_ as map>
    ${map.key}:${map.value}
</#list>
```