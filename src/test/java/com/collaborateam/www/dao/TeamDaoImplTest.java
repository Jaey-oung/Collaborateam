package com.collaborateam.www.dao;

import com.collaborateam.www.domain.TeamDto;
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
public class TeamDaoImplTest {
    @Autowired
    TeamDao teamDao;
    TeamDto teamDto1;
    TeamDto teamDto2;

    @Autowired
    UserDao userDao;
    Calendar birth;
    UserDto userDto1;
    UserDto userDto2;
    
    @Before
    public void init() throws Exception {
        birth = Calendar.getInstance();
        birth.clear();
        birth.set(2000, Calendar.DECEMBER, 19); // 2000-12-19

        userDao.deleteAll();
        teamDao.deleteAll();

        userDto1 = new UserDto("id1", "pwd1", "user1@user1.com", "name1", birth.getTime()); // Calendar -> Date
        userDto2 = new UserDto("id2", "pwd2", "user2@user2.com", "name2", birth.getTime()); // Calendar -> Date

        userDao.insert(userDto1);
        userDao.insert(userDto2);

        teamDto1 = new TeamDto(userDto1.getId(), "team1", "description1");
        teamDto2 = new TeamDto(userDto2.getId(), "team2", "description2");
    }
    
    @Test
    public void countTest() throws Exception {
        assertEquals(0, teamDao.count());

        assertEquals(1, teamDao.insert(teamDto1));
        assertEquals(1, teamDao.count());

        assertEquals(1, teamDao.insert(teamDto2));
        assertEquals(2, teamDao.count());
    }

    @Test
    public void selectAllTest() throws Exception {
        assertEquals(0, teamDao.count());

        List<TeamDto> list = teamDao.selectAll();
        assertEquals(0, list.size());

        assertEquals(1, teamDao.insert(teamDto1));
        list = teamDao.selectAll();
        assertEquals(1, list.size());

        assertEquals(1, teamDao.insert(teamDto2));
        list = teamDao.selectAll();
        assertEquals(2, list.size());
    }

    @Test
    public void selectUserTeamTest() throws Exception {
        List<TeamDto> list = teamDao.selectUserTeam(userDto1.getId());
        assertEquals(0, list.size());

        assertEquals(1, teamDao.insert(teamDto1));
        list = teamDao.selectUserTeam(userDto1.getId());
        assertEquals(1, list.size());

        Integer tno = teamDao.selectAll().get(0).getTno();

        assertEquals(1, teamDao.delete(tno, userDto1.getId()));
        list = teamDao.selectUserTeam(userDto1.getId());
        assertEquals(0, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(0, teamDao.count());

        assertEquals(1, teamDao.insert(teamDto1));
        assertEquals(1, teamDao.count());

        assertEquals(1, teamDao.insert(teamDto2));
        assertEquals(2, teamDao.count());

        teamDao.deleteAll();
        assertEquals(0, teamDao.count());
    }

    @Test
    public void insertTest() throws Exception {
        assertEquals(0, teamDao.count());

        assertEquals(1, teamDao.insert(teamDto1));
        assertEquals(1, teamDao.count());

        assertEquals(1, teamDao.insert(teamDto2));
        assertEquals(2, teamDao.count());
    }

    @Test
    public void selectTest() throws Exception {
        assertEquals(0, teamDao.count());

        assertEquals(1, teamDao.insert(teamDto1));
        assertEquals(1, teamDao.count());

        Integer tno = teamDao.selectAll().get(0).getTno();
        teamDto1.setTno(tno);

        TeamDto teamDto3 = teamDao.select(tno);
        assertEquals(teamDto1, teamDto3);

        assertEquals(1, teamDao.insert(teamDto2));
        assertEquals(2, teamDao.count());

        tno = teamDao.selectAll().get(0).getTno();
        teamDto2.setTno(tno);

        TeamDto teamDto4 = teamDao.select(tno);
        assertEquals(teamDto3, teamDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, teamDao.count());

        assertEquals(1, teamDao.insert(teamDto1));
        assertEquals(1, teamDao.count());

        Integer tno = teamDao.selectAll().get(0).getTno();
        String id = teamDao.selectAll().get(0).getLeader();

        teamDto1.setTno(tno);
        teamDto1.setLeader(id);
        teamDto1.setName("team10");
        teamDto1.setDescription("description10");

        assertEquals(1, teamDao.update(teamDto1));

        TeamDto teamDto3 = teamDao.select(tno);
        assertEquals(teamDto1, teamDto3);

        assertEquals(1, teamDao.insert(teamDto2));
        assertEquals(2, teamDao.count());

        tno = teamDao.selectAll().get(0).getTno();
        id = teamDao.selectAll().get(0).getLeader();

        teamDto2.setTno(tno);
        teamDto2.setLeader(id);
        teamDto2.setName("team20");
        teamDto2.setDescription("description20");

        assertEquals(1, teamDao.update(teamDto2));

        TeamDto teamDto4 = teamDao.select(tno);
        assertEquals(teamDto2, teamDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, teamDao.count());

        assertEquals(1, teamDao.insert(teamDto1));
        assertEquals(1, teamDao.count());

        assertEquals(1, teamDao.insert(teamDto2));
        assertEquals(2, teamDao.count());

        Integer tno1 = teamDao.selectAll().get(0).getTno();
        String id1 = teamDao.selectAll().get(0).getLeader();
        Integer tno2 = teamDao.selectAll().get(1).getTno();
        String id2 = teamDao.selectAll().get(1).getLeader();

        assertEquals(0, teamDao.delete(tno1 + 111, id1));
        assertEquals(0, teamDao.delete(tno1, id1 + "111"));
        assertEquals(0, teamDao.delete(tno1 + 111, id1 + "111"));
        assertEquals(1, teamDao.delete(tno1, id1));
        assertEquals(1, teamDao.count());
        assertNull(teamDao.select(tno1));

        assertEquals(0, teamDao.delete(tno2 + 222, id2));
        assertEquals(0, teamDao.delete(tno2, id2 + "222"));
        assertEquals(0, teamDao.delete(tno2 + 222, id2 + "222"));
        assertEquals(1, teamDao.delete(tno2, id2));
        assertEquals(0, teamDao.count());
        assertNull(teamDao.select(tno2));
    }
}