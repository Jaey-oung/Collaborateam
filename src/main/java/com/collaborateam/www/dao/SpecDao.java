package com.collaborateam.www.dao;

import com.collaborateam.www.domain.SpecDto;

import java.util.List;

public interface SpecDao {
    int count() throws Exception;
    List<SpecDto> selectAll() throws Exception;
    void removeAll() throws Exception;
    int insert(SpecDto specDto) throws Exception;
    SpecDto select(Integer sno) throws Exception;
    int modify(SpecDto specDto) throws Exception;
    int remove(Integer sno) throws Exception;
    List<SpecDto> selectSpecsByField(String field) throws Exception;
}