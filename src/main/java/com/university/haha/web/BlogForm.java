package com.university.haha.web;

/**
 * 用于页面显示的博客信息
 */
public class BlogForm {
    private Integer id;//用于显示页面的博客Id
    private String tittle;//用于显示页面的博客标题
    private String summary;//用于显示页面的博客摘要
    private String createDate;//用于显示页面的博客创建日期
    private String categoryName;//用于显示页面的博客类别名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BlogForm() {
    }

    public BlogForm(Integer id, String tittle, String summary, String createDate, String categoryName) {
        this.id = id;
        this.tittle = tittle;
        this.summary = summary;
        this.createDate = createDate;
        this.categoryName = categoryName;
    }

    public BlogForm(Integer id, String tittle, String createDate, String categoryName) {
        this.id = id;
        this.tittle = tittle;
        this.createDate = createDate;
        this.categoryName = categoryName;
    }
}
