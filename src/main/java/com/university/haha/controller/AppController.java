package com.university.haha.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.university.haha.domain.Blog;
import com.university.haha.service.BlogService;
import com.university.haha.service.UserService;
import com.university.haha.util.CommonUtil;
import com.university.haha.util.DateUtil;
import com.university.haha.util.JsonUtil;
import com.university.haha.util.Util;
import com.university.haha.web.BlogForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "app")
public class AppController {
    @Resource(name = BlogService.SERVICE)
    private BlogService blogService;
    @Resource(name = UserService.SERVICE)
    private UserService userService;

    //转换json数据工具
    @Resource(name = CommonUtil.SERVICE)
    private CommonUtil commonUtil;

    //时期转换工具
    private DateUtil dateUtil = new DateUtil();

    //普通json数据格式
    private JsonUtil jsonUtil = new JsonUtil();


    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;


    /**
     * 门户系统导航条
     *
     * @param tag                 当前标签
     * @param httpServletRequest  响应前端请求将数据放入请求
     * @param httpServletResponse 处理浏览器中文乱码问题
     * @return
     */
    @RequestMapping(value = "iframe", method = RequestMethod.GET)
    public String content(String tag, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        try {
            tag = new String(tag.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        switch (tag) {
            case "top":
                getBlog("select new Blog(id,tittle,summary,createDate,category) from Blog blog order by blog.id desc", null, tag);
                httpServletRequest.setAttribute("user",userService.getUser());
                return "fg/top";
            case "mood":
                getBlog("select new Blog(id,tittle,summary,createDate,category) from Blog blog where blog.category.id=0  order by blog.id desc", "select count(*) from Blog blog where blog.category.id=0", tag);
                return "fg/common";
            case "insight":
                getBlog("select new Blog(id,tittle,summary,createDate,category) from Blog blog where blog.category.id=1  order by blog.id desc", "select count(*) from Blog blog where blog.category.id=1", tag);
                return "fg/common";
            case "memory":
                getBlog("select new Blog(id,tittle,summary,createDate,category) from Blog blog where blog.category.id=2 order by blog.id desc", "select count(*) from Blog blog where blog.category.id=2", tag);
                return "fg/common";
            case "book":
                getBlog("select new Blog(id,tittle,summary,createDate,category) from Blog blog where blog.category.id=3  order by blog.id desc", "select count(*) from Blog blog where blog.category.id=3", tag);
                return "fg/common";
            case "technology":
                getBlog("select new Blog(id,tittle,summary,createDate,category) from Blog blog where blog.category.id=4  order by blog.id desc", "select count(*) from Blog blog where blog.category.id=4", tag);
                return "fg/common";
            default:
                getBlog("select new Blog(id,tittle,summary,createDate,category) from Blog blog  where blog.tittle like '%" + tag + "%' order by blog.id desc", "select count(*) from Blog blog where blog.tittle like '%" + tag + "%'", tag);
                return "fg/common";
        }
    }

    /**
     * 门户系统导航条封装
     *
     * @param arg1 hql语句
     * @param arg2 查询数量数
     * @param arg3 当前标签
     */
    public void getBlog(String arg1, String arg2, String arg3) {
        httpServletResponse.setHeader("Content-Type", "text/html;charset=UTF-8");
        List<Blog> blogs = blogService.getBlog(arg1, 10);
        if (arg2 != null) {
            Long count = blogService.getCount(arg2);
            httpServletRequest.setAttribute("count", count);
        }
        httpServletRequest.setAttribute("tag", arg3);
        httpServletRequest.setAttribute("blogs", blogs);
    }

    /**
     * 根据tag响应前端分页
     *
     * @param page  当前页数
     * @param limit 当前数据数
     * @param tag   当前响应标签
     * @return
     */
    @RequestMapping(value = "paging", method = RequestMethod.POST)
    public @ResponseBody
    String page(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, @RequestParam("limit") Integer limit, @RequestParam("tag") String tag) {
        switch (tag) {
            case "mood":
                return paging("select new Blog(id,tittle,summary,createDate,category) from Blog blog where blog.category.id=0 order by blog.id desc", page, limit);
            case "insight":
                return paging("select new Blog(id,tittle,summary,createDate,category) from Blog blog where blog.category.id=1 order by blog.id desc", page, limit);
            case "memory":
                return paging("select new Blog(id,tittle,summary,createDate,category) from Blog blog where blog.category.id=2 order by blog.id desc", page, limit);
            case "book":
                return paging("select new Blog(id,tittle,summary,createDate,category) from Blog blog where blog.category.id=3 order by blog.id desc", page, limit);
            case "technology":
                return paging("select new Blog(id,tittle,summary,createDate,category) from Blog blog where blog.category.id=4 order by blog.id desc", page, limit);
            default:
                return paging("select new Blog(id,tittle,summary,createDate,category) from Blog blog where blog.tittle like '%" + tag + "%' order by blog.id desc", page, limit);
        }
    }

    /**
     * 封装分页技术
     *
     * @param arg1 hql语句
     * @param arg2 page 当前页数
     * @param arg3 limit 当前数据数
     * @return
     */
    public String paging(String arg1, Integer arg2, Integer arg3) {
        String json = null;
        httpServletResponse.setHeader("Content-Type", "text/html;charset=UTF-8");
        List<Blog> blogs = blogService.getBlog(arg1, arg2, arg3);
        Util util = new Util();
        if (blogs != null) {
            util.setCode(0);
            util.setMsg("ok");
            for (int i = 0; i < blogs.size(); i++) {
                BlogForm blogForm = new BlogForm(blogs.get(i).getId(), blogs.get(i).getTittle(), blogs.get(i).getSummary(), dateUtil.DateToStr(blogs.get(i).getCreateDate()), blogs.get(i).getCategory().getCategoryName());
                util.getData().add(blogForm);
            }
            try {
                json = commonUtil.getJson(util);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            util.setCode(1);
            util.setMsg("bad");
            try {
                json = commonUtil.getJson(util);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return json;
    }


    /**
     * 查询博客内容及时响应给前端
     *
     * @param id 当前博客id号
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public String content(Integer id,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        httpServletResponse.setHeader("Content-Type", "text/html;charset=UTF-8");
        Blog blog = blogService.getBlog("select new Blog(tittle,content) from Blog blog where blog.id=:id", map);
        httpServletRequest.setAttribute("blog", blog);
        return "page";
    }

    /**
     * 返回控制台
     * @return
     */
    @RequestMapping(value = "admin",method = RequestMethod.GET)
    public String admin()
    {
        return "bg/main";
    }


}
