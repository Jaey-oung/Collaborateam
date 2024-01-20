package com.collaborateam.www.dao;

import com.collaborateam.www.domain.MemberDto;
import com.collaborateam.www.domain.TeamDto;
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
public class MemberDaoImplTest {
    @Autowired
    TeamDao teamDao;
    @Autowired
    MemberDao memberDao;
    TeamDto teamDto1;
    Integer tno;
    MemberDto memberDto1;
    MemberDto memberDto2;

    @Before
    public void init() throws Exception {
        teamDao.deleteAll();

        teamDto1 = new TeamDto("leader1", "team1", "desc1");
        teamDao.insert(teamDto1);

        tno = teamDao.selectAll().get(0).getTno();
        memberDao.deleteAll(tno);

        memberDto1 = new MemberDto(tno, "user1", "L");
        memberDto2 = new MemberDto(tno, "user2", "L");
    }

    @Test
    public void countTest() throws Exception {
        assertEquals(0, memberDao.count(tno));

        assertEquals(1, memberDao.insert(memberDto1));
        assertEquals(1, memberDao.count(tno));

        assertEquals(1, memberDao.insert(memberDto2));
        assertEquals(2, memberDao.count(tno));
    }

    @Test
    public void selectAllTest() throws Exception {
        assertEquals(0, memberDao.count(tno));

        List<MemberDto> list = memberDao.selectAll(tno);
        assertEquals(0, list.size());

        assertEquals(1, memberDao.insert(memberDto1));
        list = memberDao.selectAll(tno);
        assertEquals(1, list.size());

        assertEquals(1, memberDao.insert(memberDto2));
        list = memberDao.selectAll(tno);
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(0, memberDao.count(tno));

        assertEquals(1, memberDao.insert(memberDto1));
        assertEquals(1, memberDao.count(tno));

        assertEquals(1, memberDao.insert(memberDto2));
        assertEquals(2, memberDao.count(tno));

        memberDao.deleteAll(tno);
        assertEquals(0, memberDao.count(tno));
    }

    @Test
    public void insertTest() throws Exception {
        assertEquals(0, memberDao.count(tno));

        assertEquals(1, memberDao.insert(memberDto1));
        assertEquals(1, memberDao.count(tno));

        assertEquals(1, memberDao.insert(memberDto2));
        assertEquals(2, memberDao.count(tno));
    }

    @Test
    public void selectTest() throws Exception {
        assertEquals(0, memberDao.count(tno));

        assertEquals(1, memberDao.insert(memberDto1));
        assertEquals(1, memberDao.count(tno));

        List<MemberDto> list = memberDao.selectAll(tno);

        Integer mno = list.get(0).getMno();
        memberDto1.setMno(mno);

        MemberDto memberDto3 = memberDao.select(mno);
        assertEquals(memberDto1, memberDto3);

        assertEquals(1, memberDao.insert(memberDto2));
        assertEquals(2, memberDao.count(tno));

        list = memberDao.selectAll(tno);

        mno = list.get(1).getMno();
        memberDto2.setMno(mno);
        
        MemberDto memberDto4 = memberDao.select(mno);
        assertEquals(memberDto2, memberDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, memberDao.count(tno));

        assertEquals(1, memberDao.insert(memberDto1));
        assertEquals(1, memberDao.count(tno));

        Integer mno = memberDao.selectAll(tno).get(0).getMno();
        String id = memberDao.selectAll(tno).get(0).getId();

        memberDto1.setMno(mno);
        memberDto1.setId(id);
        memberDto1.setRole("M");

        assertEquals(1, memberDao.update(memberDto1));

        MemberDto memberDto3 = memberDao.select(mno);
        assertEquals(memberDto1, memberDto3);

        assertEquals(1, memberDao.insert(memberDto2));
        assertEquals(2, memberDao.count(tno));

        mno = memberDao.selectAll(tno).get(1).getMno();
        id = memberDao.selectAll(tno).get(1).getId();

        memberDto2.setMno(mno);
        memberDto2.setId(id);
        memberDto2.setRole("M");

        assertEquals(1, memberDao.update(memberDto2));

        MemberDto memberDto4 = memberDao.select(mno);
        assertEquals(memberDto2, memberDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, memberDao.count(tno));

        assertEquals(1, memberDao.insert(memberDto1));
        assertEquals(1, memberDao.count(tno));

        assertEquals(1, memberDao.insert(memberDto2));
        assertEquals(2, memberDao.count(tno));

        Integer mno1 = memberDao.selectAll(tno).get(0).getMno();
        String id1 = memberDao.selectAll(tno).get(0).getId();
        Integer mno2 = memberDao.selectAll(tno).get(1).getMno();
        String id2 = memberDao.selectAll(tno).get(1).getId();

        assertEquals(0, memberDao.delete(mno1 + 111, id1));
        assertEquals(0, memberDao.delete(mno1, id1 + "111"));
        assertEquals(0, memberDao.delete(mno1 + 111, id1 + "111"));
        assertEquals(1, memberDao.delete(mno1, id1));
        assertEquals(1, memberDao.count(tno));
        assertNull(memberDao.select(mno1));

        assertEquals(0, memberDao.delete(mno2 + 222, id2));
        assertEquals(0, memberDao.delete(mno2, id2 + "222"));
        assertEquals(0, memberDao.delete(mno2 + 222, id2 + "222"));
        assertEquals(1, memberDao.delete(mno2, id2));
        assertEquals(0, memberDao.count(tno));
        assertNull(memberDao.select(mno2));
    }

    @Test
    public void existTest() throws Exception {
        assertEquals(0, memberDao.count(tno));

        assertEquals(1, memberDao.insert(memberDto1));
        assertEquals(1, memberDao.count(tno));

        Integer mno = memberDao.selectAll(tno).get(0).getMno();
        String id1 = memberDao.selectAll(tno).get(0).getId();

        assertTrue(memberDao.exist(tno, id1));

        assertEquals(1, memberDao.delete(mno, id1));
        assertFalse(memberDao.exist(tno, id1));
    }
}