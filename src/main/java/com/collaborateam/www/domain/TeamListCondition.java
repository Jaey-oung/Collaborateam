package com.collaborateam.www.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class TeamListCondition extends PageCondition {
    public TeamListCondition() {
        super();
        this.pageSize = 4;
    }

    public TeamListCondition(Integer page, Integer pageSize) {
        super(page, pageSize);
    }

    public String getQueryString(Integer page) {
        return UriComponentsBuilder.fromUriString(super.getQueryString(page))
                .build().toString();
    }

    public String getQueryString() {
        return getQueryString(page);
    }

    @Override
    public String toString() {
        return "TeamListCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}