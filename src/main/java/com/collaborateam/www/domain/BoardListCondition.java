package com.collaborateam.www.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class BoardListCondition extends PageCondition {
    private String field = "A";
    private String specialization = "A";
    private String option = "A";
    private String keyword = "";

    public BoardListCondition() {
        super();
        this.pageSize = 10;
    }

    public BoardListCondition(Integer page, Integer pageSize, String field, String specialization, String option, String keyword) {
        super(page, pageSize);
        this.field = field;
        this.specialization = specialization;
        this.option = option;
        this.keyword = keyword;
    }

    public String getQueryString(Integer page, String field, String specialization, String option, String keyword) {
        return UriComponentsBuilder.fromUriString(super.getQueryString(page))
                .queryParam("field", field)
                .queryParam("specialization", specialization)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .build().toString();
    }

    public String getQueryString() {
        return getQueryString(page, field, specialization, option, keyword);
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
        return "BoardListCondition{" +
                "field='" + field + '\'' +
                ", specialization='" + specialization + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", option='" + option + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}