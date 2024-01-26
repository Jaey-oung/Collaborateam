package com.collaborateam.www.domain;

import org.springframework.web.util.UriComponentsBuilder;

public abstract class PageCondition {
    protected Integer page = 1;
    protected Integer pageSize;

    public PageCondition() {}
    public PageCondition(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public String getQueryString(Integer page) {
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .build().toString();
    }

    public String getQueryString() {
        return getQueryString(page);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (page-1) * pageSize;
    }

    @Override
    public String toString() {
        return "PageCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offSet=" + getOffset() +
                '}';
    }
}