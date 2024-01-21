package com.collaborateam.www.service;

import com.collaborateam.www.domain.MemberDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberService {
    int getCount(Integer tno) throws Exception;
    List<MemberDto> getList(Integer tno) throws Exception;
    void removeAllMembers(Integer tno) throws Exception;
    @Transactional(rollbackFor = Exception.class)
    int create(MemberDto memberDto) throws Exception;
    MemberDto read(Integer mno) throws Exception;
    int modify(MemberDto memberDto) throws Exception;
    int remove(Integer mno, String id) throws Exception;
    boolean exist(Integer tno, String id) throws Exception;
}