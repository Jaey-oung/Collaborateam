package com.collaborateam.www.service;

import com.collaborateam.www.domain.TeamDto;
import com.collaborateam.www.domain.TeamListCondition;
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
public class TeamServiceImplTest {
    @Autowired
    TeamService teamService;
    TeamDto teamDto1;
    TeamDto teamDto2;

    @Before
    public void init() throws Exception {
        teamService.deleteAll();

        teamDto1 = new TeamDto("leader1", "team1", "detail1");
        teamDto2 = new TeamDto("leader2", "team2", "detail2");
    }

    @Test
    public void insertData() throws Exception {
        for(int i=1; i<=50; i++) {
            TeamDto teamDto = new TeamDto("leader1", "team"+i, "description"+i);
            teamService.create(teamDto);
        }
    }

    @Test
    public void getCountTest() throws Exception {
        assertEquals(0, teamService.getCount());

        assertEquals(1, teamService.create(teamDto1));
        assertEquals(1, teamService.getCount());

        assertEquals(1, teamService.create(teamDto2));
        assertEquals(2, teamService.getCount());
    }

    @Test
    public void getListTest() throws Exception {
        assertEquals(0, teamService.getCount());

        List<TeamDto> list = teamService.getList();
        assertEquals(0, list.size());

        assertEquals(1, teamService.create(teamDto1));
        list = teamService.getList();
        assertEquals(1, list.size());

        assertEquals(1, teamService.create(teamDto2));
        list = teamService.getList();
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(0, teamService.getCount());

        assertEquals(1, teamService.create(teamDto1));
        assertEquals(1, teamService.getCount());

        assertEquals(1, teamService.create(teamDto2));
        assertEquals(2, teamService.getCount());

        teamService.deleteAll();
        assertEquals(0, teamService.getCount());
    }

    @Test
    public void createTest() throws Exception {
        assertEquals(0, teamService.getCount());

        assertEquals(1, teamService.create(teamDto1));
        assertEquals(1, teamService.getCount());

        assertEquals(1, teamService.create(teamDto2));
        assertEquals(2, teamService.getCount());
    }

    @Test
    public void readTest() throws Exception {
        assertEquals(0, teamService.getCount());

        assertEquals(1, teamService.create(teamDto1));
        assertEquals(1, teamService.getCount());

        Integer tno = teamService.getList().get(0).getTno();
        teamDto1.setTno(tno);

        TeamDto teamDto3 = teamService.read(tno);
        assertEquals(teamDto1, teamDto3);

        assertEquals(1, teamService.create(teamDto2));
        assertEquals(2, teamService.getCount());

        tno = teamService.getList().get(1).getTno();
        teamDto2.setTno(tno);

        TeamDto teamDto4 = teamService.read(tno);
        assertEquals(teamDto2, teamDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, teamService.getCount());

        assertEquals(1, teamService.create(teamDto1));
        assertEquals(1, teamService.getCount());

        Integer tno = teamService.getList().get(0).getTno();
        String id = teamService.getList().get(0).getLeader();

        teamDto1.setTno(tno);
        teamDto1.setLeader(id);
        teamDto1.setName("team10");
        teamDto1.setDetail("detail10");

        assertEquals(1, teamService.update(teamDto1));

        TeamDto teamDto3 = teamService.read(tno);
        assertEquals(teamDto1, teamDto3);

        assertEquals(1, teamService.create(teamDto2));
        assertEquals(2, teamService.getCount());

        tno = teamService.getList().get(1).getTno();
        id = teamService.getList().get(1).getLeader();

        teamDto2.setTno(tno);
        teamDto2.setLeader(id);
        teamDto2.setName("team20");
        teamDto2.setDetail("detail20");

        assertEquals(1, teamService.update(teamDto2));

        TeamDto teamDto4 = teamService.read(tno);
        assertEquals(teamDto2, teamDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, teamService.getCount());

        assertEquals(1, teamService.create(teamDto1));
        assertEquals(1, teamService.getCount());

        assertEquals(1, teamService.create(teamDto2));
        assertEquals(2, teamService.getCount());

        Integer tno = teamService.getList().get(0).getTno();
        String id = teamService.getList().get(0).getLeader();

        assertEquals(0, teamService.delete(tno + 111, id));
        assertEquals(0, teamService.delete(tno, id + "111"));
        assertEquals(0, teamService.delete(tno + 111, id + "111"));
        assertEquals(1, teamService.delete(tno, id));
        assertEquals(1, teamService.getCount());
        assertNull(teamService.read(tno));

        tno = teamService.getList().get(0).getTno();
        id = teamService.getList().get(0).getLeader();

        assertEquals(0, teamService.delete(tno + 222, id));
        assertEquals(0, teamService.delete(tno, id + "222"));
        assertEquals(0, teamService.delete(tno + 222, id + "222"));
        assertEquals(1, teamService.delete(tno, id));
        assertEquals(0, teamService.getCount());
        assertNull(teamService.read(tno));
    }

    @Test
    public void getTeamPageTest() throws Exception {
        TeamListCondition tlc = new TeamListCondition(1, 4);

        List<TeamDto> list = teamService.getTeamPage(teamDto1.getLeader(), tlc);
        assertEquals(0, list.size());

        assertEquals(1, teamService.create(teamDto1));

        list = teamService.getTeamPage(teamDto1.getLeader(), tlc);
        assertEquals(1, list.size());

        assertEquals(1, teamService.create(teamDto1));

        list = teamService.getTeamPage(teamDto1.getLeader(), tlc);
        assertEquals(2, list.size());
    }

    @Test
    public void getTeamCntTest() throws Exception {
        int cnt = teamService.getTeamCnt(teamDto1.getLeader());
        assertEquals(0, cnt);

        assertEquals(1, teamService.create(teamDto1));

        cnt = teamService.getTeamCnt(teamDto1.getLeader());
        assertEquals(1, cnt);

        assertEquals(1, teamService.create(teamDto1));

        cnt = teamService.getTeamCnt(teamDto1.getLeader());
        assertEquals(2, cnt);
    }

    @Test
    public void getLeaderTeamTest() throws Exception {
        List<TeamDto> list = teamService.getLeaderTeam(teamDto1.getLeader());
        assertEquals(0, list.size());

        assertEquals(1, teamService.create(teamDto1));

        list = teamService.getLeaderTeam(teamDto1.getLeader());
        assertEquals(1, list.size());

        assertEquals(1, teamService.create(teamDto1));

        list = teamService.getLeaderTeam(teamDto1.getLeader());
        assertEquals(2, list.size());
    }
}