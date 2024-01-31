package com.collaborateam.www.domain;

import java.util.Date;
import java.util.Objects;

public class InviteDto {
    private Integer ino;
    private Integer tno;
    private String id;
    private String name;
    private Date reg_date;
    private Date up_date;

    public InviteDto() {}
    public InviteDto(Integer tno, String id) {
        this.tno = tno;
        this.id = id;
    }

    public Integer getIno() {
        return ino;
    }

    public void setIno(Integer ino) {
        this.ino = ino;
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
        InviteDto inviteDto = (InviteDto) o;
        return Objects.equals(ino, inviteDto.ino) && Objects.equals(tno, inviteDto.tno) && Objects.equals(id, inviteDto.id) && Objects.equals(name, inviteDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ino, tno, id, name);
    }

    @Override
    public String toString() {
        return "InviteDto{" +
                "ino=" + ino +
                ", tno=" + tno +
                ", id='" + id + '\'' +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }
}