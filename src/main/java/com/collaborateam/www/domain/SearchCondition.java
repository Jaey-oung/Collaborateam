package com.collaborateam.www.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String field = "A";
    private String specialization = "A";
    private String option = "A";
    private String keyword = "";

    public SearchCondition() {}
    public SearchCondition(Integer page, Integer pageSize, String field, String specialization, String option, String keyword) {
        this.page = page;
        this.pageSize = pageSize;
        this.field = field;
        this.specialization = specialization;
        this.option = option;
        this.keyword = keyword;
    }

    public String getQueryString(Integer page, String field, String specialization) {    // ?page=?&pageSize=?&option=?&keyword=?
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("field", field)
                .queryParam("specialization", specialization)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .build().toString();
    }

    public String getQueryString() {    // ?page=?&pageSize=?&option=?&keyword=?
        return getQueryString(page, field, specialization);
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offset=" + getOffset() +
                ", field='" + field + '\'' +
                ", specialization='" + specialization + '\'' +
                ", option='" + option + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}