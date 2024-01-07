package com.collaborateam.www.dao;

import com.collaborateam.www.domain.BoardDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoImplTest {
    @Autowired
    BoardDao boardDao;
    BoardDto boardDto1;
    BoardDto boardDto2;

    @Before
    public void init() {
        boardDto1 = new BoardDto("title1", "content1", "writer1");
        boardDto2 = new BoardDto("title2", "content2", "writer2");
    }

    @Test
    public void countTest() throws Exception {
        boardDao.deleteAll();
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto2));
        assertEquals(2, boardDao.count());
    }

    @Test
    public void selectAllTest() throws Exception {
        boardDao.deleteAll();
        assertEquals(0, boardDao.count());

        List<BoardDto> boardDtoList = boardDao.selectAll();
        assertEquals(0, boardDtoList.size());

        assertEquals(1, boardDao.insert(boardDto1));
        boardDtoList = boardDao.selectAll();
        assertEquals(1, boardDtoList.size());

        assertEquals(1, boardDao.insert(boardDto2));
        boardDtoList = boardDao.selectAll();
        assertEquals(2, boardDtoList.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        boardDao.deleteAll();
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto2));
        assertEquals(2, boardDao.count());

        boardDao.deleteAll();
        assertEquals(0, boardDao.count());
    }

    @Test
    public void insertTest() throws Exception {
        boardDao.deleteAll();
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto2));
        assertEquals(2, boardDao.count());
    }

    @Test
    public void selectTest() throws Exception {
        boardDao.deleteAll();
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        Integer bno = boardDao.selectAll().get(0).getBno();
        boardDto1.setBno(bno);

        BoardDto boardDto3 = boardDao.select(bno);
        assertEquals(boardDto1, boardDto3);

        assertEquals(1, boardDao.insert(boardDto2));
        assertEquals(2, boardDao.count());

        bno = boardDao.selectAll().get(1).getBno();
        boardDto2.setBno(bno);

        BoardDto boardDto4 = boardDao.select(bno);
        assertEquals(boardDto2, boardDto4);
    }

    @Test
    public void updateTest() throws Exception {
        boardDao.deleteAll();
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        Integer bno = boardDao.selectAll().get(0).getBno();
        String writer = boardDao.selectAll().get(0).getWriter();

        boardDto1.setBno(bno);
        boardDto1.setTitle("title10");
        boardDto1.setContent("content10");
        boardDto1.setWriter(writer);

        assertEquals(1, boardDao.update(boardDto1));

        BoardDto boardDto3 = boardDao.select(bno);
        assertEquals(boardDto1, boardDto3);

        assertEquals(1, boardDao.insert(boardDto2));
        assertEquals(2, boardDao.count());

        bno = boardDao.selectAll().get(1).getBno();
        writer = boardDao.selectAll().get(1).getWriter();

        boardDto2.setBno(bno);
        boardDto2.setTitle("title20");
        boardDto2.setContent("content20");
        boardDto2.setWriter(writer);

        assertEquals(1, boardDao.update(boardDto2));

        BoardDto boardDto4 = boardDao.select(bno);
        assertEquals(boardDto2, boardDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        boardDao.deleteAll();
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto2));
        assertEquals(2, boardDao.count());

        Integer bno1 = boardDao.selectAll().get(0).getBno();
        String writer1 = boardDao.selectAll().get(0).getWriter();
        Integer bno2 = boardDao.selectAll().get(1).getBno();
        String writer2 = boardDao.selectAll().get(1).getWriter();

        assertEquals(0, boardDao.delete(bno1 + 111, writer1));
        assertEquals(0, boardDao.delete(bno1, writer1 + "111"));
        assertEquals(0, boardDao.delete(bno1 + 111, writer1 + "111"));
        assertEquals(1, boardDao.delete(bno1, writer1));
        assertEquals(1, boardDao.count());
        assertNull(boardDao.select(bno1));

        assertEquals(0, boardDao.delete(bno2 + 222, writer2));
        assertEquals(0, boardDao.delete(bno2, writer2 + "222"));
        assertEquals(0, boardDao.delete(bno2 + 222, writer2 + "222"));
        assertEquals(1, boardDao.delete(bno2, writer2));
        assertEquals(0, boardDao.count());
        assertNull(boardDao.select(bno2));
    }
}