package com.collaborateam.www.dao;

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
    CommentDao commentDao;
    CommentDto commentDto1;
    CommentDto commentDto2;
    @Before
    public void init() {
        commentDto1 = new CommentDto(1, 0, "comment1", "commenter1");
        commentDto2 = new CommentDto(1, 0, "comment2", "commenter2");
    }

    @Test
    public void countTest() throws Exception {
        commentDao.deleteAll(1);
        assertEquals(0, commentDao.count(1));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(1));

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(1));
    }

    @Test
    public void selectAllTest() throws Exception {
        commentDao.deleteAll(1);
        assertEquals(0, commentDao.count(1));

        List<CommentDto> list = commentDao.selectAll(1);
        assertEquals(0, list.size());

        assertEquals(1, commentDao.insert(commentDto1));
        list = commentDao.selectAll(1);
        assertEquals(1, list.size());

        assertEquals(1, commentDao.insert(commentDto2));
        list = commentDao.selectAll(1);
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        commentDao.deleteAll(1);
        assertEquals(0, commentDao.count(1));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(1));

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(1));

        commentDao.deleteAll(1);
        assertEquals(0, commentDao.count(1));
    }

    @Test
    public void insertTest() throws Exception {
        commentDao.deleteAll(1);
        assertEquals(0, commentDao.count(1));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(1));

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(1));
    }

    @Test
    public void selectTest() throws Exception {
        commentDao.deleteAll(1);
        assertEquals(0, commentDao.count(1));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(1));

        List<CommentDto> list = commentDao.selectAll(1);

        Integer cno = list.get(0).getCno();
        commentDto1.setCno(cno);

        CommentDto commentDto3 = commentDao.select(cno);
        assertEquals(commentDto1, commentDto3);

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(1));

        list = commentDao.selectAll(1);

        cno = list.get(1).getCno();
        commentDto2.setCno(cno);

        CommentDto commentDto4 = commentDao.select(cno);
        assertEquals(commentDto2, commentDto4);
    }

    @Test
    public void updateTest() throws Exception {
        commentDao.deleteAll(1);
        assertEquals(0, commentDao.count(1));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(1));

        Integer cno = commentDao.selectAll(1).get(0).getCno();
        String commenter = commentDao.selectAll(1).get(0).getCommenter();

        commentDto1.setCno(cno);
        commentDto1.setComment("comment10");
        commentDto1.setCommenter(commenter);

        assertEquals(1, commentDao.update(commentDto1));

        CommentDto commentDto3 = commentDao.select(cno);
        assertEquals(commentDto1, commentDto3);

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(1));

        cno = commentDao.selectAll(1).get(1).getCno();
        commenter = commentDao.selectAll(1).get(1).getCommenter();

        commentDto2.setCno(cno);
        commentDto2.setComment("comment20");
        commentDto2.setCommenter(commenter);

        assertEquals(1, commentDao.update(commentDto2));

        CommentDto commentDto4 = commentDao.select(cno);
        assertEquals(commentDto2, commentDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        commentDao.deleteAll(1);
        assertEquals(0, commentDao.count(1));

        assertEquals(1, commentDao.insert(commentDto1));
        assertEquals(1, commentDao.count(1));

        assertEquals(1, commentDao.insert(commentDto2));
        assertEquals(2, commentDao.count(1));

        Integer cno1 = commentDao.selectAll(1).get(0).getCno();
        String commenter1 = commentDao.selectAll(1).get(0).getCommenter();
        Integer cno2 = commentDao.selectAll(1).get(1).getCno();
        String commenter2 = commentDao.selectAll(1).get(1).getCommenter();

        assertEquals(0, commentDao.delete(cno1 + 111, commenter1));
        assertEquals(0, commentDao.delete(cno1, commenter1 + "111"));
        assertEquals(0, commentDao.delete(cno1 + 111, commenter1 + "111"));
        assertEquals(1, commentDao.delete(cno1, commenter1));
        assertEquals(1, commentDao.count(1));
        assertNull(commentDao.select(cno1));

        assertEquals(0, commentDao.delete(cno2 + 222, commenter2));
        assertEquals(0, commentDao.delete(cno2, commenter2 + "222"));
        assertEquals(0, commentDao.delete(cno2 + 222, commenter2 + "222"));
        assertEquals(1, commentDao.delete(cno2, commenter2));
        assertEquals(0, commentDao.count(1));
        assertNull(commentDao.select(cno2));
    }
}