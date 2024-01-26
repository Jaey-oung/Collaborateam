package com.collaborateam.www.service;

import com.collaborateam.www.dao.BoardDao;
import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.BoardListCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao;

    @Override
    public int getCount() throws Exception {
        return boardDao.count();
    }

    @Override
    public List<BoardDto> getList() throws Exception {
        return boardDao.selectAll();
    }

    @Override
    public void deleteAll() throws Exception {
        boardDao.removeAll();
    }

    @Override
    public int create(BoardDto boardDto) throws Exception {
        return boardDao.insert(boardDto);
    }

    @Override
    public BoardDto read(Integer bno) throws Exception {
        BoardDto boardDto = boardDao.select(bno);
        boardDao.increaseViewCnt(bno);
        return boardDto;
    }

    @Override
    public int update(BoardDto boardDto) throws Exception {
        return boardDao.modify(boardDto);
    }

    @Override
    public int delete(Integer bno, String writer) throws Exception {
        return boardDao.remove(bno, writer);
    }


    @Override
    public List<BoardDto> getBoardPage(BoardListCondition blc) throws Exception {
        return boardDao.boardPage(blc);
    }

    @Override
    public int getBoardCnt(BoardListCondition blc) throws Exception {
        return boardDao.boardCnt(blc);
    }
}