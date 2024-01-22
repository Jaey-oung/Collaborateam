package com.collaborateam.www.service;

import com.collaborateam.www.dao.GoalDao;
import com.collaborateam.www.domain.GoalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {
    @Autowired
    GoalDao goalDao;

    @Override
    public int getCount(Integer tno) throws Exception {
        return goalDao.count(tno);
    }

    @Override
    public List<GoalDto> getList(Integer tno) throws Exception {
        return goalDao.selectAll(tno);
    }

    @Override
    public void removeAllGoals(Integer tno) {
        goalDao.deleteAll(tno);
    }

    @Override
    public int create(GoalDto goalDto) throws Exception {
        return goalDao.insert(goalDto);
    }

    @Override
    public GoalDto read(Integer gno) throws Exception {
        return goalDao.select(gno);
    }

    @Override
    public int modify(GoalDto goalDto) throws Exception {
        return goalDao.update(goalDto);
    }

    @Override
    public int remove(Integer gno) throws Exception {
        return goalDao.delete(gno);
    }
}