package com.collaborateam.www.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class BoardListCondition extends PageCondition {
    private String field = "A";
    private String spec = "A";
    private String option = "A";
    private String keyword = "";
    private String zone = "A";

    public BoardListCondition() {
        super();
        this.pageSize = 10;
    }

    public BoardListCondition(Integer page, Integer pageSize, String field, String spec, String option, String keyword, String zone) {
        super(page, pageSize);
        this.field = field;
        this.spec = spec;
        this.option = option;
        this.keyword = keyword;
        this.zone = zone;
    }

    public String getQueryString(Integer page, String field, String spec, String option, String keyword, String zone) {
        return UriComponentsBuilder.fromUriString(super.getQueryString(page))
                .queryParam("field", field)
                .queryParam("spec", spec)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .queryParam("zone", zone)
                .build().toString();
    }

    public String getQueryString() {
        return getQueryString(page, field, spec, option, keyword, zone);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
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

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "BoardListCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", field='" + field + '\'' +
                ", spec='" + spec + '\'' +
                ", option='" + option + '\'' +
                ", keyword='" + keyword + '\'' +
                ", zone='" + zone + '\'' +
                '}';
    }
}