package com.collaborateam.www.domain;

import java.util.Date;
import java.util.Objects;

public class TaskDto {
    private Integer tano;
    private Integer tno;
    private String member;
    private String name;
    private Date reg_date;
    private Date up_date;

    public TaskDto() {}
    public TaskDto(Integer tno, String member, String name) {
        this.tno = tno;
        this.member = member;
        this.name = name;
    }

    public Integer getTano() {
        return tano;
    }

    public void setTano(Integer tano) {
        this.tano = tano;
    }

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        TaskDto taskDto = (TaskDto) o;
        return Objects.equals(tano, taskDto.tano) && Objects.equals(tno, taskDto.tno) && Objects.equals(member, taskDto.member) && Objects.equals(name, taskDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tano, tno, member, name);
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "tano=" + tano +
                ", tno=" + tno +
                ", member='" + member + '\'' +
                ", name='" + name + '\'' +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }
}