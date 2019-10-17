# SSHBlog
基于SSH的个人博客
- 个人博客(haha)是使用SSH(SpringMVC，Spring，Hibernate)搭建的简单博客
- 主要功能
  1.登录
  2.搜索博客
  3.写博客(支持Markdown)
  4.修改博客
  5.删除博客
  6.博客分类
  7.查看博客
- 待实现/优化功能
  1.增加点赞功能
  2.增加浏览数功能
  3.增加评论功能
  4.美化后台登录页面
  5.提高Hibernate查询能力和页面缓存机制，实体和表的关联理解不够透彻需要加强
  6.Nginx和SpringSecurity集成实现安全登录功能
  7.采用Redis进行分库和分表提高查询能力
  8.采用Nginx负载均衡对接口进行集群缓解查询接口的压力
- 技术支持
  后台技术
  1.SpringMVC进行接口的拦截
  2.Spring作为容器
  3.Hibernate对数据进行持久化
  4.SpringSecurity进行安全登录
  5.Thymeleaf动态模板
  6.Druid 数据源用于加载数据库信息
  前台技术
  1、layui实现主页面和后台页面
  2、editormd实现Markdown文本编辑
  3、jQuery传输前台和后台数据
  云服务器技术
  1.Docker部署Tomcat，Nginx，MySql运行环境
  2.Tomcat作为应用服务器后台逻辑项目和动态页面
  3.Nginx作为Web服务器存放图片和静态页面实现负载均衡和反向代理到后台服务器页面
  数据库技术
  1.管理员表 用于登录后台进行后台的操作的数据表
  2.博客表 用于存放博客信息 n..1
  3.博客类别表 用于存放博客类别信息
- 前台实现效果
![](E:\\前台.png)
- 后台管理实现效果
![](E:\\后台.png)
