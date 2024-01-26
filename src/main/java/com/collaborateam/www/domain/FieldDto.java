package com.collaborateam.www.domain;

import java.util.Date;
import java.util.Objects;

public class FieldDto {
    private Integer fno;
    private String value;
    private String name;
    private Date reg_date;
    private Date up_date;

    public FieldDto() {}
    public FieldDto(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getFno() {
        return fno;
    }

    public void setFno(Integer fno) {
        this.fno = fno;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        FieldDto fieldDto = (FieldDto) o;
        return Objects.equals(fno, fieldDto.fno)  && Objects.equals(value, fieldDto.value) &&
                Objects.equals(name, fieldDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fno, value, name);
    }

    @Override
    public String toString() {
        return "FieldDto{" +
                "fno=" + fno +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }
}