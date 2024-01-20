package com.collaborateam.www.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class BoardListCondition extends PageCondition {
    private String field = "A";
    private String specialization = "A";

    public BoardListCondition() {
        super();
        this.pageSize = 10;
    }

    public BoardListCondition(Integer page, Integer pageSize, String option, String keyword, String field, String specialization) {
        super(page, pageSize, option, keyword);
        this.field = field;
        this.specialization = specialization;
    }

    public String getQueryString(Integer page, String option, String keyword, String field, String specialization) {
        return UriComponentsBuilder.fromUriString(super.getQueryString(page, option, keyword))
                .queryParam("field", field)
                .queryParam("specialization", specialization)
                .build().toString();
    }

    public String getQueryString() {
        return getQueryString(page, option, keyword, field, specialization);
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