package com.collaborateam.www.service;

import com.collaborateam.www.domain.InviteDto;
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
public class InviteServiceImplTest {
    @Autowired
    TeamService teamService;
    @Autowired
    InviteService inviteService;
    Integer tno;
    TeamDto teamDto1;
    InviteDto inviteDto1;
    InviteDto inviteDto2;

    @Before
    public void init() throws Exception {
        teamService.deleteAll();

        teamDto1 = new TeamDto("leader1", "team1", "detail1");
        teamService.create(teamDto1);

        tno = teamService.getList().get(0).getTno();
        inviteService.deleteAll();

        inviteDto1 = new InviteDto(tno, "user1");
        inviteDto2 = new InviteDto(tno, "user2");
    }

    @Test
    public void getCountTest() throws Exception {
        assertEquals(0, inviteService.getCount());

        assertEquals(1, inviteService.create(inviteDto1));
        assertEquals(1, inviteService.getCount());

        assertEquals(1, inviteService.create(inviteDto2));
        assertEquals(2, inviteService.getCount());
    }

    @Test
    public void getListTest() throws Exception {
        assertEquals(0, inviteService.getCount());

        List<InviteDto> list = inviteService.getList();
        assertEquals(0, list.size());

        assertEquals(1, inviteService.create(inviteDto1));
        list = inviteService.getList();
        assertEquals(1, list.size());

        assertEquals(1, inviteService.create(inviteDto2));
        list = inviteService.getList();
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(0, inviteService.getCount());

        assertEquals(1, inviteService.create(inviteDto1));
        assertEquals(1, inviteService.getCount());

        assertEquals(1, inviteService.create(inviteDto2));
        assertEquals(2, inviteService.getCount());

        inviteService.deleteAll();
        assertEquals(0, inviteService.getCount());
    }

    @Test
    public void createTest() throws Exception {
        assertEquals(0, inviteService.getCount());

        assertEquals(1, inviteService.create(inviteDto1));
        assertEquals(1, inviteService.getCount());

        assertEquals(0, inviteService.create(inviteDto1));
        assertEquals(1, inviteService.getCount());

        assertEquals(1, inviteService.create(inviteDto2));
        assertEquals(2, inviteService.getCount());

        assertEquals(0, inviteService.create(inviteDto2));
        assertEquals(2, inviteService.getCount());
    }

    @Test
    public void readTest() throws Exception {
        assertEquals(0, inviteService.getCount());

        assertEquals(1, inviteService.create(inviteDto1));
        assertEquals(1, inviteService.getCount());

        Integer ino = inviteService.getList().get(0).getIno();
        inviteDto1.setIno(ino);

        InviteDto inviteDto3 = inviteService.read(ino);
        assertEquals(inviteDto1, inviteDto3);

        assertEquals(1, inviteService.create(inviteDto2));
        assertEquals(2, inviteService.getCount());

        ino = inviteService.getList().get(1).getIno();
        inviteDto2.setIno(ino);

        InviteDto inviteDto4 = inviteService.read(ino);
        assertEquals(inviteDto2, inviteDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, inviteService.getCount());

        assertEquals(1, inviteService.create(inviteDto1));
        assertEquals(1, inviteService.getCount());

        Integer ino = inviteService.getList().get(0).getIno();
        String id = inviteService.getList().get(0).getId();

        inviteDto1.setIno(ino);
        inviteDto1.setId(id);

        assertEquals(1, inviteService.update(inviteDto1));

        InviteDto inviteDto3 = inviteService.read(ino);
        assertEquals(inviteDto1, inviteDto3);

        assertEquals(1, inviteService.create(inviteDto2));
        assertEquals(2, inviteService.getCount());

        ino = inviteService.getList().get(1).getIno();
        id = inviteService.getList().get(1).getId();

        inviteDto2.setIno(ino);
        inviteDto2.setId(id);

        assertEquals(1, inviteService.update(inviteDto2));

        InviteDto inviteDto4 = inviteService.read(ino);
        assertEquals(inviteDto2, inviteDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, inviteService.getCount());

        assertEquals(1, inviteService.create(inviteDto1));
        assertEquals(1, inviteService.getCount());

        assertEquals(1, inviteService.create(inviteDto2));
        assertEquals(2, inviteService.getCount());

        Integer ino = inviteService.getList().get(0).getIno();
        String id = inviteService.getList().get(0).getId();

        assertEquals(0, inviteService.delete(ino + 111, id));
        assertEquals(0, inviteService.delete(ino, id + "111"));
        assertEquals(0, inviteService.delete(ino + 111, id + "111"));
        assertEquals(1, inviteService.delete(ino, id));
        assertEquals(1, inviteService.getCount());
        assertNull(inviteService.read(ino));

        ino = inviteService.getList().get(0).getIno();
        id = inviteService.getList().get(0).getId();

        assertEquals(0, inviteService.delete(ino + 222, id));
        assertEquals(0, inviteService.delete(ino, id + "222"));
        assertEquals(0, inviteService.delete(ino + 222, id + "222"));
        assertEquals(1, inviteService.delete(ino, id));
        assertEquals(0, inviteService.getCount());
        assertNull(inviteService.read(ino));
    }

    @Test
    public void getUserInviteTest() throws Exception {
        List<InviteDto> list = inviteService.getUserInvite(inviteDto1.getId());
        assertEquals(0, list.size());

        assertEquals(1, inviteService.create(inviteDto1));

        list = inviteService.getUserInvite(inviteDto1.getId());
        assertEquals(1, list.size());

        assertEquals(0, inviteService.create(inviteDto1));

        list = inviteService.getUserInvite(inviteDto1.getId());
        assertEquals(1, list.size());
    }
}