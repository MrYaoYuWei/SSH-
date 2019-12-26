# SSHBlog个人博客

#### 个人博客(haha)是使用SSH(SpringMVC，Spring，Hibernate)搭建的简单博客

#### 导入myblog.sql文件到myblog数据库运行即可

#### 运行环境IDEA,Tomcat7,gradle4.8,MySQL5.5


## 主要功能
  - 登录
  - 搜索博客
  - 写博客(支持Markdown)
  - 修改博客
  - 删除博客
  - 博客分类
  - 查看博客

## 待实现/优化功能
  - 增加点赞功能
  - 增加浏览数功能
  - 增加评论功能
  - 美化后台登录页面
  - 提高Hibernate查询能力和页面缓存机制，实体和表的关联理解不够透彻需要加强
  - Nginx和SpringSecurity集成实现安全登录功能
  - 采用Redis进行分库和分表提高查询能力
  - 采用Nginx负载均衡对接口进行集群缓解查询接口的压力

## 技术支持
  - SpringMVC进行接口的拦截
  - Spring作为容器
  - Hibernate对数据进行持久化
  - SpringSecurity进行安全登录
  - Thymeleaf动态模板
  - Druid 数据源用于加载数据库信息
  - layui实现主页面和后台页面
  - editormd实现Markdown文本编辑
  - jQuery传输前台和后台数据
  - Docker部署Tomcat，Nginx，MySql运行环境
  - Tomcat作为应用服务器后台逻辑项目和动态页面
  - Nginx作为Web服务器存放图片和静态页面实现负载均衡和反向代理到后台服务器页面

## 前台实现
 - http://localhost:8080/haha

 ![](http://junmoxiao.org.cn/前台.png)



## 后台管理实现
 - http://localhost:8080/haha/bg/main.html
 - 用户名和密码查询User表即可

 ![](http://junmoxiao.org.cn/后台.png)



## 后台写作实现

 ![](http://junmoxiao.org.cn/other.png)









