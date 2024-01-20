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
    TeamDto teamDto1;
    Integer tno;
    MemberDto memberDto1;
    MemberDto memberDto2;

    @Before
    public void init() throws Exception {
        teamService.removeAllTeams();

        teamDto1 = new TeamDto("leader1", "team1", "desc1");
        teamService.create(teamDto1);

        tno = teamService.getList().get(0).getTno();
        memberService.removeAllMembers(tno);

        memberDto1 = new MemberDto(tno, "user1", "L");
        memberDto2 = new MemberDto(tno, "user2", "L");
    }

    @Test
    public void countTest() throws Exception {
        assertEquals(0, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(1, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(2, memberService.getCount(tno));
    }

    @Test
    public void selectAllTest() throws Exception {
        assertEquals(0, memberService.getCount(tno));

        List<MemberDto> list = memberService.getList(tno);
        assertEquals(0, list.size());

        assertEquals(1, memberService.create(memberDto1));
        list = memberService.getList(tno);
        assertEquals(1, list.size());

        assertEquals(1, memberService.create(memberDto2));
        list = memberService.getList(tno);
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(0, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(1, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(2, memberService.getCount(tno));

        memberService.removeAllMembers(tno);
        assertEquals(0, memberService.getCount(tno));
    }

    @Test
    public void insertTest() throws Exception {
        assertEquals(0, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(1, memberService.getCount(tno));

        assertEquals(0, memberService.create(memberDto1));

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(2, memberService.getCount(tno));
    }

    @Test
    public void selectTest() throws Exception {
        assertEquals(0, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(1, memberService.getCount(tno));

        List<MemberDto> list = memberService.getList(tno);

        Integer mno = list.get(0).getMno();
        memberDto1.setMno(mno);

        MemberDto memberDto3 = memberService.read(mno);
        assertEquals(memberDto1, memberDto3);

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(2, memberService.getCount(tno));

        list = memberService.getList(tno);

        mno = list.get(1).getMno();
        memberDto2.setMno(mno);

        MemberDto memberDto4 = memberService.read(mno);
        assertEquals(memberDto2, memberDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(1, memberService.getCount(tno));

        Integer mno = memberService.getList(tno).get(0).getMno();
        String id = memberService.getList(tno).get(0).getId();

        memberDto1.setMno(mno);
        memberDto1.setId(id);
        memberDto1.setRole("M");

        assertEquals(1, memberService.modify(memberDto1));

        MemberDto memberDto3 = memberService.read(mno);
        assertEquals(memberDto1, memberDto3);

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(2, memberService.getCount(tno));

        mno = memberService.getList(tno).get(1).getMno();
        id = memberService.getList(tno).get(1).getId();

        memberDto2.setMno(mno);
        memberDto2.setId(id);
        memberDto2.setRole("M");

        assertEquals(1, memberService.modify(memberDto2));

        MemberDto memberDto4 = memberService.read(mno);
        assertEquals(memberDto2, memberDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto1));
        assertEquals(1, memberService.getCount(tno));

        assertEquals(1, memberService.create(memberDto2));
        assertEquals(2, memberService.getCount(tno));

        Integer mno1 = memberService.getList(tno).get(0).getMno();
        String id1 = memberService.getList(tno).get(0).getId();
        Integer mno2 = memberService.getList(tno).get(1).getMno();
        String id2 = memberService.getList(tno).get(1).getId();

        assertEquals(0, memberService.remove(mno1 + 111, id1));
        assertEquals(0, memberService.remove(mno1, id1 + "111"));
        assertEquals(0, memberService.remove(mno1 + 111, id1 + "111"));
        assertEquals(1, memberService.remove(mno1, id1));
        assertEquals(1, memberService.getCount(tno));
        assertNull(memberService.read(mno1));

        assertEquals(0, memberService.remove(mno2 + 222, id2));
        assertEquals(0, memberService.remove(mno2, id2 + "222"));
        assertEquals(0, memberService.remove(mno2 + 222, id2 + "222"));
        assertEquals(1, memberService.remove(mno2, id2));
        assertEquals(0, memberService.getCount(tno));
        assertNull(memberService.read(mno2));
    }
}