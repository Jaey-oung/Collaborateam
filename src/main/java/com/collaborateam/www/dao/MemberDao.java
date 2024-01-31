package com.collaborateam.www.dao;

import com.collaborateam.www.domain.MemberDto;

import java.util.List;

public interface MemberDao {
    int count(Integer tno) throws Exception;
    List<MemberDto> selectAll(Integer tno) throws Exception;
    int removeAll(Integer tno) throws Exception;
    int insert(MemberDto memberDto) throws Exception;
    MemberDto select(Integer mno) throws Exception;
    int modify(MemberDto memberDto) throws Exception;
    int remove(Integer mno, String id) throws Exception;
    boolean isUserExist(Integer tno, String id) throws Exception;
    int leave(Integer tno, String id) throws Exception;
}