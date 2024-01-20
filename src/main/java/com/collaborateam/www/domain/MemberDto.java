package com.collaborateam.www.domain;

import java.util.Date;
import java.util.Objects;

public class MemberDto {
    private Integer mno;
    private Integer tno;
    private String id;
    private String role;
    private Date reg_date;
    private Date up_date;

    public MemberDto(Integer tno, String id, String role) {
        this.tno = tno;
        this.id = id;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        return Objects.equals(tno, memberDto.tno) && Objects.equals(id, memberDto.id) && Objects.equals(role, memberDto.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tno, id, role);
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "mno=" + mno +
                ", tno=" + tno +
                ", id='" + id + '\'' +
                ", role='" + role + '\'' +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }
}