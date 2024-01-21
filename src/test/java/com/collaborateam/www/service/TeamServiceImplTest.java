package com.collaborateam.www.service;

import com.collaborateam.www.domain.TeamDto;
import com.collaborateam.www.domain.TeamListCondition;
import com.collaborateam.www.domain.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class TeamServiceImplTest {
    @Autowired
    TeamService teamService;
    TeamDto teamDto1;
    TeamDto teamDto2;
    
    @Autowired
    UserService userService;
    Calendar birth;
    UserDto userDto1;
    UserDto userDto2;

    @Before
    public void init() throws Exception {
        birth = Calendar.getInstance();
        birth.clear();
        birth.set(2000, Calendar.DECEMBER, 19); // 2000-12-19

        userService.removeAllUsers();
        teamService.removeAllTeams();

        userDto1 = new UserDto("user1", "pwd1", "user1@user1.com", "name1", birth.getTime()); // Calendar -> Date
        userDto2 = new UserDto("user2", "pwd2", "user2@user2.com", "name2", birth.getTime()); // Calendar -> Date

        userService.addUser(userDto1);
        userService.addUser(userDto2);

        teamDto1 = new TeamDto(userDto1.getId(), "team1", "description1");
        teamDto2 = new TeamDto(userDto2.getId(), "team2", "description2");
    }

    @Test
    public void insertData() throws Exception {
        for(int i=1; i<=50; i++) {
            TeamDto teamDto = new TeamDto(userDto1.getId(), "team"+i, "description"+i);
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
    public void removeAllTeamsTest() throws Exception {
        assertEquals(0, teamService.getCount());

        assertEquals(1, teamService.create(teamDto1));
        assertEquals(1, teamService.getCount());

        assertEquals(1, teamService.create(teamDto2));
        assertEquals(2, teamService.getCount());

        teamService.removeAllTeams();
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

        tno = teamService.getList().get(0).getTno();
        teamDto2.setTno(tno);

        TeamDto teamDto4 = teamService.read(tno);
        assertEquals(teamDto3, teamDto4);
    }

    @Test
    public void modifyTest() throws Exception {
        assertEquals(0, teamService.getCount());

        assertEquals(1, teamService.create(teamDto1));
        assertEquals(1, teamService.getCount());

        Integer tno = teamService.getList().get(0).getTno();
        String id = teamService.getList().get(0).getLeader();

        teamDto1.setTno(tno);
        teamDto1.setLeader(id);
        teamDto1.setName("team10");
        teamDto1.setDescription("description10");

        assertEquals(1, teamService.modify(teamDto1));

        TeamDto teamDto3 = teamService.read(tno);
        assertEquals(teamDto1, teamDto3);

        assertEquals(1, teamService.create(teamDto2));
        assertEquals(2, teamService.getCount());

        tno = teamService.getList().get(0).getTno();
        id = teamService.getList().get(0).getLeader();

        teamDto2.setTno(tno);
        teamDto2.setLeader(id);
        teamDto2.setName("team20");
        teamDto2.setDescription("description20");

        assertEquals(1, teamService.modify(teamDto2));

        TeamDto teamDto4 = teamService.read(tno);
        assertEquals(teamDto2, teamDto4);
    }

    @Test
    public void removeTest() throws Exception {
        assertEquals(0, teamService.getCount());

        assertEquals(1, teamService.create(teamDto1));
        assertEquals(1, teamService.getCount());

        assertEquals(1, teamService.create(teamDto2));
        assertEquals(2, teamService.getCount());

        Integer tno1 = teamService.getList().get(0).getTno();
        String id1 = teamService.getList().get(0).getLeader();
        Integer tno2 = teamService.getList().get(1).getTno();
        String id2 = teamService.getList().get(1).getLeader();

        assertEquals(0, teamService.remove(tno1 + 111, id1));
        assertEquals(0, teamService.remove(tno1, id1 + "111"));
        assertEquals(0, teamService.remove(tno1 + 111, id1 + "111"));
        assertEquals(1, teamService.remove(tno1, id1));
        assertEquals(1, teamService.getCount());
        assertNull(teamService.read(tno1));

        assertEquals(0, teamService.remove(tno2 + 222, id2));
        assertEquals(0, teamService.remove(tno2, id2 + "222"));
        assertEquals(0, teamService.remove(tno2 + 222, id2 + "222"));
        assertEquals(1, teamService.remove(tno2, id2));
        assertEquals(0, teamService.getCount());
        assertNull(teamService.read(tno2));
    }

    @Test
    public void getTeamPageTest() throws Exception {
        TeamListCondition tlc = new TeamListCondition(1, 4);

        List<TeamDto> list = teamService.getTeamPage(userDto1.getId(), tlc);
        assertEquals(0, list.size());

        assertEquals(1, teamService.create(teamDto1));

        list = teamService.getTeamPage(userDto1.getId(), tlc);
        assertEquals(1, list.size());

        assertEquals(1, teamService.create(teamDto1));

        list = teamService.getTeamPage(userDto1.getId(), tlc);
        assertEquals(2, list.size());
    }

    @Test
    public void getTeamCntTest() throws Exception {
        int cnt = teamService.getTeamCnt(userDto1.getId());
        assertEquals(0, cnt);

        assertEquals(1, teamService.create(teamDto1));

        cnt = teamService.getTeamCnt(userDto1.getId());
        assertEquals(1, cnt);

        assertEquals(1, teamService.create(teamDto1));

        cnt = teamService.getTeamCnt(userDto1.getId());
        assertEquals(2, cnt);
    }
}