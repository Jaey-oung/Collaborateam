package com.collaborateam.www.domain;

import java.util.Date;
import java.util.Objects;

public class InviteDto {
    private Integer ino;
    private Integer tno;
    private String team_name;
    private String id;
    private Date reg_date;

    public InviteDto() {}

    public InviteDto(Integer tno, String team_name, String id) {
        this.tno = tno;
        this.team_name = team_name;
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

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InviteDto inviteDto = (InviteDto) o;
        return Objects.equals(ino, inviteDto.ino) && Objects.equals(tno, inviteDto.tno) && Objects.equals(team_name, inviteDto.team_name) && Objects.equals(id, inviteDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ino, tno, team_name, id);
    }

    @Override
    public String toString() {
        return "InviteDto{" +
                "ino=" + ino +
                ", tno=" + tno +
                ", team_name='" + team_name + '\'' +
                ", id='" + id + '\'' +
                ", reg_date=" + reg_date +
                '}';
    }
}