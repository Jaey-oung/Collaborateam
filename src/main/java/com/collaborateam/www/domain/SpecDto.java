package com.collaborateam.www.domain;

import java.util.Date;
import java.util.Objects;

public class SpecDto {
    private Integer sno;
    private String value;
    private String name;
    private String field;
    private Date reg_date;
    private Date up_date;

    public SpecDto() {}

    public SpecDto(String value, String name, String field) {
        this.value = value;
        this.name = name;
        this.field = field;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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
        SpecDto specDto = (SpecDto) o;
        return Objects.equals(sno, specDto.sno) && Objects.equals(value, specDto.value) && Objects.equals(name, specDto.name) && Objects.equals(field, specDto.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sno, value, name, field);
    }

    @Override
    public String toString() {
        return "SpecDto{" +
                "sno=" + sno +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", field='" + field + '\'' +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }
}