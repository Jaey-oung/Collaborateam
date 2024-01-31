package com.collaborateam.www.service;

import com.collaborateam.www.domain.CommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    int getCount(Integer bno) throws Exception;
    List<CommentDto> getList(Integer bno) throws Exception;
    void deleteAll(Integer bno) throws Exception;
    @Transactional(rollbackFor = Exception.class)
    int create(CommentDto commentDto) throws Exception;
    CommentDto read(Integer cno) throws Exception;
    int update(CommentDto commentDto) throws Exception;
    @Transactional(rollbackFor = Exception.class)
    int delete(Integer cno, Integer bno, String commenter) throws Exception;
}