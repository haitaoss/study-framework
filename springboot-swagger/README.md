# 原文

https://mianshenglee.github.io/2019/11/13/springboot-swagger1.html

## 概述

- swagger 它是一个规范 （OpenAPI Specification，OAS）
- 使用swagger进行接口文档编写步骤是：（1）编写符合OAS的接口文件，（2）使用swagger-ui进行显示。
- 牛人写了swagger 集成到 mvc的框架，我们直接使用即可（SpringFox） http://springfox.github.io/springfox/
- springfox是对swagger规范的在springmvc和springboot开发中的自动化实现

## 实现了那些功能

- 如何使用springboot与swagger结合创建接口文档
- 对swagger进行参数化配置及包过滤
- swagger注解的详细使用
- 接口认证方法
- 接口过滤：通过包过滤、类注解过滤、方法注解过滤、分组过滤等方式，实现按需发布指定接口的功能。
- 使用easy-mock+swagger实现mock数据。
- 使用maven插件实现接口文档的离线导出

## 依赖说明

```xml
<!-- 自动化生成符合swagger的接口描述文件,查看JSON格式的接口描述文档 http://ip:port/v2/api-docs -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>${swagger.version}</version>
</dependency>

<!-- 可视化显示接口文档,可视化接口描述页面 http://ip:port/swagger-ui.html -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>${swagger.version}</version>
</dependency>
```

## 使用SpringFox 常用的注解

- 数据模型注解：
    - @ApiModel 实体描述
    - @ApiModelProperty 实体属性描述
- 接口注解：
    - @ApiOperation 接口描述
    - @ApiIgnore 忽略此接口
- 控制器注解：
    - @Api 控制器描述
- 输入参数注解：
    - @ApiImplicitParams 接口多个参数
    - @ApiImplicitParam 单个参数
        - 注解属性paramType 可选值 path，query，header，form，body
            - path：以地址的形式提交数据，根据 id 查询用户的接口就是这种形式传参
            - query：Query string 的方式传参，对应无注解和@RequestParam注解参数
            - header：请求头（header）中的参数
            - form：以 Form 表单的形式提交，如上传文件，属于此类
            - body：以JSON方式传参
    - @ApiParam 单个参数描述
- 响应数据注解：
    - @ResponseHeader 响应值header
    - @ApiResponses 响应值集
    - @ApiResponse 单个响应值

## 【企业实践】接口认证

对于对外发布的接口，一般都需要进行权限校验，需要登录的用户才可以调用，否则报权限不足。对于api的安全认证，一般有以下几种模式(
可参考swagger文档: https://swagger.io/docs/specification/authentication/ )：

- Basic 认证：使用Authorization请求头，用户名和密码使用Base64编码，如：Authorization: Basic ZGVtbzpwQDU1dzByZA==
- Bearer 认证：使用Authorization请求头，由服务端产生加密字符token，客户端发送请求时把此token的请求头发到服务端作为凭证，token格式是Authorization: Bearer <token>
- API Keys认证：使用请求头，参数或cookie，把只能客户端和服务端知道的密钥进行传输。
- OAuth2： 一个开放标准，允许用户授权第三方移动应用访问他们存储在另外的服务提供者上的信息，而不需要将用户名和密码提供给第三方移动应用或分享他们数据的所有内容 。

对于前后端分离的的springboot项目，现在很多采用jwt的认证方式（属于Bearer认证），需要先获取token，然后在调用接口时，添加类似Authorization: Bearer <token>
的请求头来对接口进行认证。针对这种方式，在Swagger中有以下三种方法来实现：

- 在接口参数描述中添加@ApiImplicitParam，其中参数类型是ParamType=header
  ```java
  // 这种方式的缺点是，针对每一个接口，都需要添加这个参数描述，而描述都是一样的，重复工作
  @ApiImplicitParam(name = "Authorization", value = "token，格式: Bearer &lttoken&gt", required = false, dataType = "String",paramType = "header")
    ```
- 添加全局接口参数
  ```java
  // 这种方式缺点也明显，由于设置了全局参数，则所有接口都需要此参数，若有某些接口不需要，则需要进行特殊处理
  //全局header参数
  ParameterBuilder tokenPar = new ParameterBuilder();
  List<Parameter> pars = new ArrayList<Parameter>();
  tokenPar.name("Authorization").description("token令牌")
      .modelRef(new ModelRef("string"))
      .parameterType("header")
      .required(true).build();
  pars.add(tokenPar.build());
  docket.globalOperationParameters(pars);
  ```
- 添加安全认证上下文
  ```java
  docket.securitySchemes(securitySchemes())
          .securityContexts(securityContexts());
  ...//省略
  private List<ApiKey> securitySchemes() {
          return Lists.newArrayList(
                  new ApiKey("Authorization", "Authorization", "header"));
      }
  private List<SecurityContext> securityContexts() {
      return Lists.newArrayList(
          SecurityContext.builder()
          .securityReferences(defaultAuth())
          //正则式过滤,此处是所有非login开头的接口都需要认证
          .forPaths(PathSelectors.regex("^(?!login).*$"))
          .build()
      );
  }
  List<SecurityReference> defaultAuth() {
      AuthorizationScope authorizationScope = new AuthorizationScope("global", "认证权限");
      return Lists.newArrayList(
          new SecurityReference("Authorization", new AuthorizationScope[]{authorizationScope}));
  }
  ```

## 接口过滤

- 通过包过滤、类注解过滤、方法注解过滤、分组过滤等方式，实现按需发布指定接口的功能。
- 在springboot的swagger配置类(Swagger2Config.java)中，Docket提供了apis()和paths()两个方法用于接口过滤。

## 使用easy-mock+swagger实现mock数据。

- 根据easy-mock的官网介绍， Easy Mock 是一个可视化，并且能快速生成模拟数据的持久化服务。 可以使用它的在线服务，也可以私有部署。它是开源项目，
- 它可以连接swagger，自动生成mock数据
- github地址： https://github.com/easy-mock/easy-mock

## 使用maven插件实现接口文档的离线导出

一般在开发过程中使用swagger文档，直接使用浏览器访问swagger-ui.html页面即可满足要求，若有需求是需要导出离线接口文档，总体可以按以下思路进行：

- 使用swagger的v2/api-docs的url地址导出json文档（不导出也可以直接使用此url作为输入文档的地址）
- 使用swagger2markup工具将json文档转为asciidoc格式文档。
- 使用asciidoctor工具将asciidoc文件转html或pdf文件

1. 定义目录文件

```shell
#api-docs.json 可以访问 http://ip:port/v2/api-docs 然后 ctrl+S 保存，
├─docs
  └─swagger
    └─api-docs.json 
```

2. 导出asciidoc文档 使用swagger2markup导出asciidoc文档有两种方式：

- 引入swagger2markup的jar包，通过编码方式生成对应格式的文档输出。（参考 cn.haitaoss.advswagger.AdvanceSwaggerDemoApplicationTests）
- 使用swagger2markup的maven插件，以mvn命令生成对应格式文档输出。（`mvn swagger2markup:convertSwagger2markup`）

3. 下载生成pdf需要的字体和主体（中文）
    - 下载KaiGenGothicCN开头和RobotoMono开头的ttf字体：https://github.com/chloerei/asciidoctor-pdf-cjk-kai_gen_gothic/releases
    -
    下载主体文件：https://github.com/chloerei/asciidoctor-pdf-cjk-kai_gen_gothic/blob/master/data/themes/KaiGenGothicCN-theme.yml
4. 使用asciidoctor插件对其转换为html和pdf文档输出
   `mvn generate-resources`