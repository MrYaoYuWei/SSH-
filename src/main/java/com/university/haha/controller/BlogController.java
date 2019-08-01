package com.university.haha.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.university.haha.domain.Blog;
import com.university.haha.domain.Category;
import com.university.haha.service.BlogService;
import com.university.haha.util.Util;
import com.university.haha.util.CommonUtil;
import com.university.haha.util.DateUtil;
import com.university.haha.util.JsonUtil;
import com.university.haha.web.BlogForm;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;


@Controller
@RequestMapping(value = "/blog")
public class BlogController {
    @Resource(name = BlogService.SERVICE)
    private BlogService blogService;
    //json格式转换工具
    @Resource(name = CommonUtil.SERVICE)
    private CommonUtil commonUtil;
    //时期转换工具
    private DateUtil dateUtil = new DateUtil();

    /**
     * 撰写博客功能（显示页面）
     */
    @RequestMapping(value = "/writeBlog", method = RequestMethod.GET)
    public String writeBlog(Integer id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Content-Type", "text/html;charset=UTF-8");
        //如果Id不是空修改博客功能
        if (id != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            Blog blog = blogService.getBlog("select new Blog(id,tittle,summary,content,createDate,category) from Blog b where b.id=:id", map);
            httpServletRequest.setAttribute("blog", blog);
            httpServletRequest.setAttribute("categories",blog.getCategory());
            return "bg/writeblog";
        } else {
            //如果Id是空撰写博客功能
            List<Category> categories = blogService.getCategory("from Category order by id desc");
            httpServletRequest.setAttribute("categories", categories);
            return "bg/writeblog";
        }
    }


    @RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
    public @ResponseBody
    String saveBlog(@RequestParam(value = "id", required = false) Integer id,@RequestParam("tittle") String tittle, @RequestParam("summary") String summary, @RequestParam("category") String categoryName, @RequestParam("content") String content) throws JsonProcessingException {
        /**
         保存博客功能
         */
        Integer status = null;
        if (id == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("categoryName", categoryName);
            Category category = blogService.getCategory("from Category where categoryName=:categoryName", map);
            Blog blog = new Blog(tittle, summary, content, new Date());
            blog.setCategory(category);
            status = blogService.saveBlog(blog);
        }
        else if (id != null){
            /**
             更新博客功能
             */
            Category category = new Category(categoryName);
            Blog blog = new Blog(tittle, summary, content, new Date());
            blog.setCategory(category);
            status = blogService.updateBlog(Blog.class,id,blog);
        }
        //Util格式status,msg
        //普通json数据格式工具
        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.setStatus(status);
        jsonUtil.setMsg("ok");
        return commonUtil.getJson(jsonUtil);
    }

    /**
     * 搜索博客功能(显示页面)
     */
    @RequestMapping(value = "/findBlog", method = RequestMethod.GET)
    public String findBlog(String mes,HttpServletRequest httpServletRequest) {
        if (mes != null) {
            httpServletRequest.setAttribute("mes",mes);
            return "bg/findblog";
        } else {
            return "bg/findblog";
        }
    }

    /**
     * 搜索博客功能
     * page：当前页数
     * limit：每页条数
     */
    @RequestMapping(value = "/getBlog", method = RequestMethod.POST)
    public @ResponseBody
    String getBlog(@RequestParam(value = "mes",required = false)String mes,@RequestParam(required = true, defaultValue = "1") Integer page, @RequestParam(required = false) Integer limit) throws JsonProcessingException {
        List<Blog> blogs=new ArrayList<>();
        //数据表格规范json数据工具
        Util util = new Util();
        try {
            mes= new String(mes.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (mes!=null)
        {
            blogs = blogService.getBlog("select new Blog(id,tittle,createDate,category)from Blog blog where blog.tittle like '%" + mes + "%' order by blog.id desc", page, limit);
            util.setCount(blogService.getCount("select count(*) from Blog blog where blog.tittle like '%" + mes + "%'"));
        }
        else {
             blogs = blogService.getBlog("select new Blog(id,tittle,createDate,category)from Blog blog order by blog.id desc", page, limit);
            util.setCount(blogService.getCount("select count(*) from Blog"));
        }
        //如果不是空则将数据放入博客数据表格json格式
        if (blogs != null) {
            util.setCode(0);
            util.setMsg("ok");
            for (int i = 0; i < blogs.size(); i++) {
                BlogForm blogForm = new BlogForm(blogs.get(i).getId(), blogs.get(i).getTittle(), dateUtil.DateToStr(blogs.get(i).getCreateDate()), blogs.get(i).getCategory().getCategoryName());
                util.getData().add(blogForm);
            }
            return commonUtil.getJson(util);
        } else {
            util.setCode(1);
            util.setMsg("bad");
            return commonUtil.getJson(util);
        }
    }

    /**
     * 删除博客功能
     */
    @RequestMapping(value = "/deleteBlog", method = RequestMethod.POST)
    public @ResponseBody
    String deleteBlog(@RequestParam("id") Integer id) throws JsonProcessingException {
        int status = blogService.deleteBlog(Blog.class, id);
        //普通json数据格式工具
        JsonUtil jsonUtil = new JsonUtil();
        if (status == 0) {
            jsonUtil.setStatus(status);
            jsonUtil.setMsg("success delete");
        } else {
            jsonUtil.setStatus(1);
            jsonUtil.setMsg("fail delete");
        }
        return commonUtil.getJson(jsonUtil);
    }


    /**
     * 本地文件上传功能
     */
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity uploadfile(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonObject = objectMapper.createObjectNode();
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/json");
            String rootPath = request.getSession().getServletContext().getRealPath("/static/images/");
            /**
             * 文件路径不存在则需要创建文件路径
             */
            File filePath = new File(rootPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            // 最终文件名
            File realFile = new File(rootPath + File.separator + attach.getOriginalFilename());
            FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);

            // response返回的json格式是editor.md所限制的
            jsonObject.put("success", 1);
            jsonObject.put("message", "上传成功");
            jsonObject.put("url", "/haha/static/images/" + attach.getOriginalFilename());
        } catch (Exception e) {
            jsonObject.put("success", 0);
        }
        return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
    }

}
