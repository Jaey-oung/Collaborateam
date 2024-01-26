package com.collaborateam.www.service;

import com.collaborateam.www.domain.FieldDto;

import java.util.List;

public interface FieldService {
    int getCount() throws Exception;
    List<FieldDto> getList() throws Exception;
    void deleteAll() throws Exception;
    int create(FieldDto fieldDto) throws Exception;
    FieldDto read(Integer fno) throws Exception;
    int update(FieldDto fieldDto) throws Exception;
    int delete(Integer fno) throws Exception;
}