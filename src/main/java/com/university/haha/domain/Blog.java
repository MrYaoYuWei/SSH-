package com.university.haha.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table
/**
 * 发布博客
 */
public class Blog implements Serializable {
    private int id;//发布博客Id
    private String tittle;//发布博客标题
    private String summary;//发布博客摘要
    private String content;//发布博客内容
    private Date createDate;//发布博客创建时间
    private Category category;//发布博客类别（多对一）

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tittle")
    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    @Basic
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Temporal(value = TemporalType.DATE)
    @Basic
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Blog() {
    }

    public Blog(int id, String tittle, String summary, String content, Date createDate, Category category) {
        this.id = id;
        this.tittle = tittle;
        this.summary = summary;
        this.content = content;
        this.createDate = createDate;
        this.category = category;
    }

    public Blog(int id, String tittle, Date createDate,Category category) {
        this.id = id;
        this.tittle = tittle;
        this.createDate = createDate;
        this.category=category;
    }

    public Blog(String tittle, String summary, String content, Date createDate) {
        this.tittle = tittle;
        this.summary = summary;
        this.content = content;
        this.createDate = createDate;
    }

    public Blog(int id, String tittle, String summary, Date createDate, Category category) {
        this.id = id;
        this.tittle = tittle;
        this.summary = summary;
        this.createDate = createDate;
        this.category = category;
    }

    public Blog(String tittle, String content) {
        this.tittle = tittle;
        this.content = content;
    }

    public Blog(int id, String tittle, String summary, String content, Date createDate) {
        this.id = id;
        this.tittle = tittle;
        this.summary = summary;
        this.content = content;
        this.createDate = createDate;
    }
}
