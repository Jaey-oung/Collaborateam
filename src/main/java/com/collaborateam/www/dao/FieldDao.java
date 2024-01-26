package com.collaborateam.www.dao;

import com.collaborateam.www.domain.FieldDto;

import java.util.List;

public interface FieldDao {
    int count() throws Exception;
    List<FieldDto> selectAll() throws Exception;
    void removeAll() throws Exception;
    int insert(FieldDto fieldDto) throws Exception;
    FieldDto select(Integer fno) throws Exception;
    int modify(FieldDto fieldDto) throws Exception;
    int remove(Integer fno) throws Exception;
}