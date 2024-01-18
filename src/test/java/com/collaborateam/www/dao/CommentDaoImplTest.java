package com.collaborateam.www.dao;

import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.CommentDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentDaoImplTest {
    @Autowired
    BoardDao boardDao;
    @Autowired
    CommentDao commentDao;
    Integer bno;
    BoardDto boardDto1;
    CommentDto commentDto1;
    CommentDto commentDto2;

    @Before
    public void init() throws Exception {
        boardDao.deleteAll();

        boardDto1 = new BoardDto("IT", "Web Development", "title1", "content1", "writer1");
        boardDao.insert(boardDto1);

        bno = boardDao.selectAll().get(0).getBno();
        commentDao.deleteAll(bno);

        commentDto1 = new CommentDto(bno, 0, "comment1", "commenter1");
        commentDto2 = new CommentDto(bno, 0, "comment2", "commenter2");
    }

    @Test
    public void countTest() throws Exception {
        assertEquals(0, commentDao.count(bno));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(bno));

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(bno));
    }

    @Test
    public void selectAllTest() throws Exception {
        assertEquals(0, commentDao.count(bno));

        List<CommentDto> list = commentDao.selectAll(bno);
        assertEquals(0, list.size());

        assertEquals(1, commentDao.insert(commentDto1));
        list = commentDao.selectAll(bno);
        assertEquals(1, list.size());

        assertEquals(1, commentDao.insert(commentDto2));
        list = commentDao.selectAll(bno);
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(0, commentDao.count(bno));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(bno));

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(bno));

        commentDao.deleteAll(bno);
        assertEquals(0, commentDao.count(bno));
    }

    @Test
    public void insertTest() throws Exception {
        assertEquals(0, commentDao.count(bno));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(bno));

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(bno));
    }

    @Test
    public void selectTest() throws Exception {
        assertEquals(0, commentDao.count(bno));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(bno));

        List<CommentDto> list = commentDao.selectAll(bno);

        Integer cno = list.get(0).getCno();
        commentDto1.setCno(cno);

        CommentDto commentDto3 = commentDao.select(cno);
        assertEquals(commentDto1, commentDto3);

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(bno));

        list = commentDao.selectAll(bno);

        cno = list.get(1).getCno();
        commentDto2.setCno(cno);

        CommentDto commentDto4 = commentDao.select(cno);
        assertEquals(commentDto2, commentDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, commentDao.count(bno));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(bno));

        Integer cno = commentDao.selectAll(bno).get(0).getCno();
        String commenter = commentDao.selectAll(bno).get(0).getCommenter();

        commentDto1.setCno(cno);
        commentDto1.setComment("comment10");
        commentDto1.setCommenter(commenter);

        assertEquals(1, commentDao.update(commentDto1));

        CommentDto commentDto3 = commentDao.select(cno);
        assertEquals(commentDto1, commentDto3);

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(bno));

        cno = commentDao.selectAll(bno).get(1).getCno();
        commenter = commentDao.selectAll(bno).get(1).getCommenter();

        commentDto2.setCno(cno);
        commentDto2.setComment("comment20");
        commentDto2.setCommenter(commenter);

        assertEquals(1, commentDao.update(commentDto2));

        CommentDto commentDto4 = commentDao.select(cno);
        assertEquals(commentDto2, commentDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, commentDao.count(bno));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(bno));

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(bno));

        Integer cno1 = commentDao.selectAll(bno).get(0).getCno();
        String commenter1 = commentDao.selectAll(bno).get(0).getCommenter();
        Integer cno2 = commentDao.selectAll(bno).get(1).getCno();
        String commenter2 = commentDao.selectAll(bno).get(1).getCommenter();

        assertEquals(0, commentDao.delete(cno1 + 111, commenter1));
        assertEquals(0, commentDao.delete(cno1, commenter1 + "111"));
        assertEquals(0, commentDao.delete(cno1 + 111, commenter1 + "111"));
        assertEquals(1, commentDao.delete(cno1, commenter1));
        assertEquals(1, commentDao.count(bno));
        assertNull(commentDao.select(cno1));

        assertEquals(0, commentDao.delete(cno2 + 222, commenter2));
        assertEquals(0, commentDao.delete(cno2, commenter2 + "222"));
        assertEquals(0, commentDao.delete(cno2 + 222, commenter2 + "222"));
        assertEquals(1, commentDao.delete(cno2, commenter2));
        assertEquals(0, commentDao.count(bno));
        assertNull(commentDao.select(cno2));
    }
}