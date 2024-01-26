package com.collaborateam.www.service;

import com.collaborateam.www.dao.SpecDao;
import com.collaborateam.www.domain.SpecDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecServiceImpl implements SpecService {
    @Autowired
    SpecDao specDao;

    @Override
    public int getCount() throws Exception {
        return specDao.count();
    }

    @Override
    public List<SpecDto> getList() throws Exception {
        return specDao.selectAll();
    }

    @Override
    public void deleteAll() throws Exception {
        specDao.removeAll();
    }

    @Override
    public int create(SpecDto specDto) throws Exception {
        return specDao.insert(specDto);
    }

    @Override
    public SpecDto read(Integer sno) throws Exception {
        return specDao.select(sno);
    }

    @Override
    public int update(SpecDto specDto) throws Exception {
        return specDao.modify(specDto);
    }

    @Override
    public int delete(Integer sno) throws Exception {
        return specDao.remove(sno);
    }

    @Override
    public List<SpecDto> getSpecsByField(String field) throws Exception {
        return specDao.selectSpecsByField(field);
    }

}