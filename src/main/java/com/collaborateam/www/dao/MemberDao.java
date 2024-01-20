package com.collaborateam.www.dao;

import com.collaborateam.www.domain.MemberDto;

import java.util.List;

public interface MemberDao {
    int count(Integer tno) throws Exception;
    List<MemberDto> selectAll(Integer tno) throws Exception;
    int deleteAll(Integer tno);
    int insert(MemberDto memberDto) throws Exception;
    MemberDto select(Integer mno) throws Exception;
    int update(MemberDto memberDto) throws Exception;
    int delete(Integer mno, String id) throws Exception;
    boolean exist(Integer tno, String id) throws Exception;
}