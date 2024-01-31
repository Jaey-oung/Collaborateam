package com.collaborateam.www.service;

import com.collaborateam.www.dao.MemberDao;
import com.collaborateam.www.domain.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;

    @Override
    public int getCount(Integer tno) throws Exception {
        return memberDao.count(tno);
    }

    @Override
    public List<MemberDto> getList(Integer tno) throws Exception {
        return memberDao.selectAll(tno);
    }

    @Override
    public void deleteAll(Integer tno) throws Exception {
        memberDao.removeAll(tno);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(MemberDto memberDto) throws Exception {
        return memberDao.isUserExist(memberDto.getTno(), memberDto.getId()) ? 0 : memberDao.insert(memberDto);
    }

    @Override
    public MemberDto read(Integer mno) throws Exception {
        return memberDao.select(mno);
    }

    @Override
    public int update(MemberDto memberDto) throws Exception {
        return memberDao.modify(memberDto);
    }

    @Override
    public int delete(Integer mno, String id) throws Exception {
        return memberDao.remove(mno, id);
    }

    @Override
    public int withdraw(Integer tno, String id) throws Exception {
        return memberDao.leave(tno, id);
    }
}