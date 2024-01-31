package com.collaborateam.www.service;

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
public class MemberServiceImplTest {
    @Autowired
    TeamService teamService;
    @Autowired
    MemberService memberService;
    Integer tno;
    TeamDto teamDto1;
    MemberDto memberDto1;
    MemberDto memberDto2;

    @Before
    public void init() throws Exception {
        teamService.deleteAll();

        teamDto1 = new TeamDto("leader1", "team1", "detail1");
        teamService.create(teamDto1);

        tno = teamService.getList().get(0).getTno();

        memberDto1 = new MemberDto(tno, "user1");
        memberDto2 = new MemberDto(tno, "user2");
    }

    @Test
    public void getCountTest() throws Exception {
        assertEquals(1, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(2, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(3, memberService.getCount(tno));
    }

    @Test
    public void getListTest() throws Exception {
        assertEquals(1, memberService.getCount(tno));

        List<MemberDto> list = memberService.getList(tno);
        assertEquals(1, list.size());

        assertEquals(1, memberService.create(memberDto1));
        list = memberService.getList(tno);
        assertEquals(2, list.size());

        assertEquals(1, memberService.create(memberDto2));
        list = memberService.getList(tno);
        assertEquals(3, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(1, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(2, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(3, memberService.getCount(tno));

        memberService.deleteAll(tno);
        assertEquals(0, memberService.getCount(tno));
    }

    @Test
    public void createTest() throws Exception {
        assertEquals(1, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(2, memberService.getCount(tno));

        assertEquals(0, memberService.create(memberDto1));
        assertEquals(2, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(3, memberService.getCount(tno));

        assertEquals(0, memberService.create(memberDto2));
        assertEquals(3, memberService.getCount(tno));
    }

    @Test
    public void readTest() throws Exception {
        assertEquals(1, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(2, memberService.getCount(tno));

        List<MemberDto> list = memberService.getList(tno);

        Integer mno = list.get(1).getMno();
        memberDto1.setMno(mno);

        MemberDto memberDto3 = memberService.read(mno);
        assertEquals(memberDto1, memberDto3);

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(3, memberService.getCount(tno));

        list = memberService.getList(tno);

        mno = list.get(2).getMno();
        memberDto2.setMno(mno);

        MemberDto memberDto4 = memberService.read(mno);
        assertEquals(memberDto2, memberDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(1, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(2, memberService.getCount(tno));

        Integer mno = memberService.getList(tno).get(1).getMno();
        String id = memberService.getList(tno).get(1).getId();

        memberDto1.setMno(mno);
        memberDto1.setId(id);

        assertEquals(1, memberService.update(memberDto1));

        MemberDto memberDto3 = memberService.read(mno);
        assertEquals(memberDto1, memberDto3);

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(3, memberService.getCount(tno));

        mno = memberService.getList(tno).get(2).getMno();
        id = memberService.getList(tno).get(2).getId();

        memberDto2.setMno(mno);
        memberDto2.setId(id);

        assertEquals(1, memberService.update(memberDto2));

        MemberDto memberDto4 = memberService.read(mno);
        assertEquals(memberDto2, memberDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(1, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(2, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(3, memberService.getCount(tno));

        Integer mno = memberService.getList(tno).get(1).getMno();
        String id = memberService.getList(tno).get(1).getId();

        assertEquals(0, memberService.delete(mno + 111, id));
        assertEquals(0, memberService.delete(mno, id + "111"));
        assertEquals(0, memberService.delete(mno + 111, id + "111"));
        assertEquals(1, memberService.delete(mno, id));
        assertEquals(2, memberService.getCount(tno));
        assertNull(memberService.read(mno));

        mno = memberService.getList(tno).get(1).getMno();
        id = memberService.getList(tno).get(1).getId();

        assertEquals(0, memberService.delete(mno + 222, id));
        assertEquals(0, memberService.delete(mno, id + "222"));
        assertEquals(0, memberService.delete(mno + 222, id + "222"));
        assertEquals(1, memberService.delete(mno, id));
        assertEquals(1, memberService.getCount(tno));
        assertNull(memberService.read(mno));
    }

    @Test
    public void withdrawTest() throws Exception {
        assertEquals(1, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(2, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(3, memberService.getCount(tno));

        assertEquals(1, memberService.withdraw(tno, memberDto1.getId()));
        assertEquals(2, memberService.getCount(tno));

        assertEquals(1, memberService.withdraw(tno, memberDto2.getId()));
        assertEquals(1, memberService.getCount(tno));
    }
}