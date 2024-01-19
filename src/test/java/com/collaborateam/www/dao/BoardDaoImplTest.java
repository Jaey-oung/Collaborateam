package com.collaborateam.www.dao;

import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.BoardListCondition;
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
    public void init() throws Exception {
        boardDao.deleteAll();
        boardDto1 = new BoardDto("IT", "WD", "title1", "content1", "writer1");
        boardDto2 = new BoardDto("IT", "SD", "title2", "content2", "writer2");
    }

    @Test
    public void countTest() throws Exception {
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto2));
        assertEquals(2, boardDao.count());
    }

    @Test
    public void selectAllTest() throws Exception {
        assertEquals(0, boardDao.count());

        List<BoardDto> list = boardDao.selectAll();
        assertEquals(0, list.size());

        assertEquals(1, boardDao.insert(boardDto1));
        list = boardDao.selectAll();
        assertEquals(1, list.size());

        assertEquals(1, boardDao.insert(boardDto2));
        list = boardDao.selectAll();
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
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
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto2));
        assertEquals(2, boardDao.count());
    }

    @Test
    public void selectTest() throws Exception {
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        List<BoardDto> list = boardDao.selectAll();

        Integer bno = list.get(0).getBno();
        boardDto1.setBno(bno);

        BoardDto boardDto3 = boardDao.select(bno);
        assertEquals(boardDto1, boardDto3);

        assertEquals(1, boardDao.insert(boardDto2));
        assertEquals(2, boardDao.count());

        bno = boardDao.selectAll().get(0).getBno();
        boardDto2.setBno(bno);

        BoardDto boardDto4 = boardDao.select(bno);
        assertEquals(boardDto2, boardDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        Integer bno = boardDao.selectAll().get(0).getBno();
        String writer = boardDao.selectAll().get(0).getWriter();

        boardDto1.setBno(bno);
        boardDto1.setField("Finance");
        boardDto1.setSpecialization("RM");
        boardDto1.setTitle("title10");
        boardDto1.setContent("content10");
        boardDto1.setWriter(writer);

        assertEquals(1, boardDao.update(boardDto1));

        BoardDto boardDto3 = boardDao.select(bno);
        assertEquals(boardDto1, boardDto3);

        assertEquals(1, boardDao.insert(boardDto2));
        assertEquals(2, boardDao.count());

        bno = boardDao.selectAll().get(0).getBno();
        writer = boardDao.selectAll().get(0).getWriter();

        boardDto2.setBno(bno);
        boardDto2.setField("Finance");
        boardDto2.setSpecialization("FA");
        boardDto2.setTitle("title20");
        boardDto2.setContent("content20");
        boardDto2.setWriter(writer);

        assertEquals(1, boardDao.update(boardDto2));

        BoardDto boardDto4 = boardDao.select(bno);
        assertEquals(boardDto2, boardDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto2));
        assertEquals(2, boardDao.count());

        Integer bno1 = boardDao.selectAll().get(1).getBno();
        String writer1 = boardDao.selectAll().get(1).getWriter();
        Integer bno2 = boardDao.selectAll().get(0).getBno();
        String writer2 = boardDao.selectAll().get(0).getWriter();

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

    @Test
    public void selectPageTest() throws Exception {
        for (int i = 1; i <= 10; i++) {
            BoardDto boardDto3 = new BoardDto("IT", "WD", "title"+i, "content"+i, "writer"+i);
            assertEquals(1, boardDao.insert(boardDto3));
        }

        int offset = 0;
        int pageSize = 3;

        List<BoardDto> list = boardDao.selectPage(offset, pageSize);
        assertEquals("title10", list.get(0).getTitle());
        assertEquals("title9", list.get(1).getTitle());
        assertEquals("title8", list.get(2).getTitle());

        pageSize = 1;

        list = boardDao.selectPage(offset, pageSize);
        assertEquals("title10", list.get(0).getTitle());

        offset = 7;
        pageSize = 3;

        list = boardDao.selectPage(offset, pageSize);
        assertEquals("title3", list.get(0).getTitle());
        assertEquals("title2", list.get(1).getTitle());
        assertEquals("title1", list.get(2).getTitle());
    }

    @Test
    public void increaseViewCntTest() throws Exception {
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        Integer bno = boardDao.selectAll().get(0).getBno();

        assertEquals(1, boardDao.increaseViewCnt(bno));
        BoardDto boardDto3 = boardDao.select(bno);
        assertNotNull(boardDto3);
        assertEquals(1, boardDto3.getView_cnt());

        assertEquals(1, boardDao.increaseViewCnt(bno));
        boardDto3 = boardDao.select(bno);
        assertNotNull(boardDto3);
        assertEquals(2, boardDto3.getView_cnt());
    }

    @Test
    public void searchResultPageTest() throws Exception {
        String[] titles = {"title1", "title2"};
        String[] contents = {"content1", "content2"};
        String[] writers = {"writer1", "writer2"};

        for (String title : titles) {
            for (String content : contents) {
                for (String writer : writers) {
                    BoardDto boardDto = new BoardDto("IT", "WD", title, content, writer);
                    boardDao.insert(boardDto);
                }   // title1, content1, writer1 / title1, content1, writer2
            }       // title1, content2, writer1 / title1, content2, writer2
        }           // title2, content1, writer1 / title2, content1, writer2
                    // title2, content2, writer1 / title2, content2, writer2

        BoardListCondition blc = new BoardListCondition(1, 5, "T", "title", "IT", "WD");
        List<BoardDto> list = boardDao.searchResultPage(blc);    // LIMIT #{offset}, #{pageSize}
        assertEquals(5, list.size());                  // If searchResultPage exceeds pageSize, it returns pageSize

        blc = new BoardListCondition(1, 10, "T", "test", "IT", "WD");
        list = boardDao.searchResultPage(blc);
        assertEquals(0, list.size());

        blc = new BoardListCondition(1, 10, "T", "title", "IT", "WD");
        list = boardDao.searchResultPage(blc);
        assertEquals(8, list.size());

        blc = new BoardListCondition(1, 10, "T", "title2", "IT", "WD");
        list = boardDao.searchResultPage(blc);
        assertEquals(4, list.size());

        blc = new BoardListCondition(1, 10, "W", "writer", "IT", "WD");
        list = boardDao.searchResultPage(blc);
        assertEquals(8, list.size());

        blc = new BoardListCondition(1, 10, "W", "writer2", "IT", "WD");
        list = boardDao.searchResultPage(blc);
        assertEquals(4, list.size());

        blc = new BoardListCondition(1, 10, "TC", "title", "IT", "WD");
        list = boardDao.searchResultPage(blc);
        assertEquals(8, list.size());

        blc = new BoardListCondition(1, 10, "TC", "content2", "IT", "WD");
        list = boardDao.searchResultPage(blc);
        assertEquals(4, list.size());

        blc = new BoardListCondition(1, 10, "A", "content2", "TEST", "TEST");
        list = boardDao.searchResultPage(blc);
        assertEquals(0, list.size());
    }

    @Test
    public void searchResultCntTest() throws Exception {
        String[] titles = {"title1", "title2"};
        String[] contents = {"content1", "content2"};
        String[] writers = {"writer1", "writer2"};

        for (String title : titles) {
            for (String content : contents) {
                for (String writer : writers) {
                    BoardDto boardDto = new BoardDto("IT", "WD", title, content, writer);
                    boardDao.insert(boardDto);
                }   // title1, content1, writer1 / title1, content1, writer2
            }       // title1, content2, writer1 / title1, content2, writer2
        }           // title2, content1, writer1 / title2, content1, writer2
                    // title2, content2, writer1 / title2, content2, writer2

        BoardListCondition blc = new BoardListCondition(1, 5, "T", "title", "IT", "WD");
        int cnt = boardDao.searchResultCnt(blc);
        assertEquals(8, cnt);

        blc = new BoardListCondition(1, 10, "T", "test", "IT", "WD");
        cnt = boardDao.searchResultCnt(blc);
        assertEquals(0, cnt);

        blc = new BoardListCondition(1, 10, "T", "title", "IT", "WD");
        cnt = boardDao.searchResultCnt(blc);
        assertEquals(8, cnt);

        blc = new BoardListCondition(1, 10, "T", "title2", "IT", "WD");
        cnt = boardDao.searchResultCnt(blc);
        assertEquals(4, cnt);

        blc = new BoardListCondition(1, 10, "W", "writer", "IT", "WD");
        cnt = boardDao.searchResultCnt(blc);
        assertEquals(8, cnt);

        blc = new BoardListCondition(1, 10, "W", "writer2", "IT", "WD");
        cnt = boardDao.searchResultCnt(blc);
        assertEquals(4, cnt);

        blc = new BoardListCondition(1, 10, "TC", "title", "IT", "WD");
        cnt = boardDao.searchResultCnt(blc);
        assertEquals(8, cnt);

        blc = new BoardListCondition(1, 10, "TC", "content2", "IT", "WD");
        cnt = boardDao.searchResultCnt(blc);
        assertEquals(4, cnt);

        blc = new BoardListCondition(1, 10, "A", "content2", "TEST", "TEST");
        cnt = boardDao.searchResultCnt(blc);
        assertEquals(0, cnt);
    }

    @Test
    public void updateCommentCntTest() throws Exception {
        assertEquals(0, boardDao.count());

        assertEquals(1, boardDao.insert(boardDto1));
        assertEquals(1, boardDao.count());

        Integer bno = boardDao.selectAll().get(0).getBno();

        assertEquals(1, boardDao.updateCommentCnt(bno, 1));
        BoardDto boardDto3 = boardDao.select(bno);
        assertNotNull(boardDto3);
        assertEquals(1, boardDto3.getComment_cnt());

        assertEquals(1, boardDao.updateCommentCnt(bno, -1));
        boardDto3 = boardDao.select(bno);
        assertNotNull(boardDto3);
        assertEquals(0, boardDto3.getComment_cnt());
    }
}