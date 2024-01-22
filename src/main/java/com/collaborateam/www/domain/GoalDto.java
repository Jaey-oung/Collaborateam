package com.collaborateam.www.domain;

import java.util.Date;
import java.util.Objects;

public class GoalDto {
    private Integer gno;
    private Integer tno;
    private String name;
    private Date reg_date;
    private Date up_date;

    public GoalDto() {}

    public GoalDto(Integer tno, String name) {
        this.tno = tno;
        this.name = name;
    }

    public Integer getGno() {
        return gno;
    }

    public void setGno(Integer gno) {
        this.gno = gno;
    }

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
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
        GoalDto goalDto = (GoalDto) o;
        return Objects.equals(gno, goalDto.gno) && Objects.equals(tno, goalDto.tno) && Objects.equals(name, goalDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gno, tno, name);
    }

    @Override
    public String toString() {
        return "GoalDto{" +
                "gno=" + gno +
                ", tno=" + tno +
                ", name='" + name + '\'' +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }
}