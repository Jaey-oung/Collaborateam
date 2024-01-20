package com.collaborateam.www.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class TeamListCondition extends PageCondition {
    public TeamListCondition() {
        super();
        this.pageSize = 4;
    }

    public TeamListCondition(Integer page, Integer pageSize, String option, String keyword) {
        super(page, pageSize, option, keyword);
    }

    @Override
    public String getQueryString(Integer page, String option, String keyword) {
        return UriComponentsBuilder.fromUriString(super.getQueryString(page, option, keyword))
                .build().toString();
    }

    public String getQueryString() {
        return getQueryString(page, option, keyword);
    }


    @Override
    public String toString() {
        return "TeamListCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", option='" + option + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}