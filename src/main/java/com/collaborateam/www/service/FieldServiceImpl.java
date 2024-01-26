package com.collaborateam.www.service;

import com.collaborateam.www.dao.FieldDao;
import com.collaborateam.www.domain.FieldDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    FieldDao fieldDao;

    @Override
    public int getCount() throws Exception {
        return fieldDao.count();
    }

    @Override
    public List<FieldDto> getList() throws Exception {
        return fieldDao.selectAll();
    }

    @Override
    public void deleteAll() throws Exception {
        fieldDao.removeAll();
    }

    @Override
    public int create(FieldDto fieldDto) throws Exception {
        return fieldDao.insert(fieldDto);
    }

    @Override
    public FieldDto read(Integer fno) throws Exception {
        return fieldDao.select(fno);
    }

    @Override
    public int update(FieldDto fieldDto) throws Exception {
        return fieldDao.modify(fieldDto);
    }

    @Override
    public int delete(Integer fno) throws Exception {
        return fieldDao.remove(fno);
    }
}