package com.collaborateam.www.domain;

import java.util.Date;
import java.util.Objects;

public class TeamDto {
    private Integer tno;
    private String leader;
    private String name;
    private String detail;
    private Date reg_date;
    private Date up_date;

    public TeamDto() {}
    public TeamDto(String leader, String name, String detail) {
        this.leader = leader;
        this.name = name;
        this.detail = detail;
    }

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDto teamDto = (TeamDto) o;
        return Objects.equals(tno, teamDto.tno) && Objects.equals(leader, teamDto.leader) &&
                Objects.equals(name, teamDto.name) && Objects.equals(detail, teamDto.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tno, leader, name, detail);
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "tno=" + tno +
                ", leader='" + leader + '\'' +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }
}