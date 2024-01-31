package com.collaborateam.www.domain;

import java.util.Date;
import java.util.Objects;

public class MemberDto {
    private Integer mno;
    private Integer tno;
    private String id;
    private Date reg_date;
    private Date up_date;

    public MemberDto() {}
    public MemberDto(Integer tno, String id) {
        this.tno = tno;
        this.id = id;
    }

    public Integer getMno() {
        return mno;
    }

    public void setMno(Integer mno) {
        this.mno = mno;
    }

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        MemberDto memberDto = (MemberDto) o;
        return Objects.equals(mno, memberDto.mno) && Objects.equals(tno, memberDto.tno) && Objects.equals(id, memberDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mno, tno, id);
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "mno=" + mno +
                ", tno=" + tno +
                ", id='" + id + '\'' +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }
}