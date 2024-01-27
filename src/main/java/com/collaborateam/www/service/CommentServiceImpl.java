package com.collaborateam.www.service;

import com.collaborateam.www.dao.BoardDao;
import com.collaborateam.www.dao.CommentDao;
import com.collaborateam.www.domain.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    BoardDao boardDao;
    @Autowired
    CommentDao commentDao;

    @Override
    public int getCount(Integer bno) throws Exception {
        return commentDao.count(bno);
    }

    @Override
    public List<CommentDto> getList(Integer bno) throws Exception {
        return commentDao.selectAll(bno);
    }

    @Override
    public void deleteAll(Integer bno) {
        commentDao.removeAll(bno);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(CommentDto commentDto) throws Exception {
        boardDao.updateCommentCnt(commentDto.getBno(), 1);
        return commentDao.insert(commentDto);
    }

    @Override
    public CommentDto read(Integer cno) throws Exception {
        return commentDao.select(cno);
    }

    @Override
    public int update(CommentDto commentDto) throws Exception {
        return commentDao.modify(commentDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer cno, Integer bno, String commenter) throws Exception {
        boardDao.updateCommentCnt(bno, -1);
        return commentDao.remove(cno, commenter);
    }
}
