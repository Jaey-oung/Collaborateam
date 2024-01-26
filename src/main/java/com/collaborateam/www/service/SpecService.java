package com.collaborateam.www.service;

import com.collaborateam.www.domain.SpecDto;

import java.util.List;

public interface SpecService {
    int getCount() throws Exception;
    List<SpecDto> getList() throws Exception;
    void deleteAll() throws Exception;
    int create(SpecDto specDto) throws Exception;
    SpecDto read(Integer sno) throws Exception;
    int update(SpecDto specDto) throws Exception;
    int delete(Integer sno) throws Exception;
    List<SpecDto> getSpecsByField(String field) throws Exception;
}