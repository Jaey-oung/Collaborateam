package com.collaborateam.www.service;

import com.collaborateam.www.domain.GoalDto;
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
public class GoalServiceImplTest {
    @Autowired
    TeamService teamService;
    @Autowired
    GoalService goalService;
    Integer tno;
    TeamDto teamDto1;
    GoalDto goalDto1;
    GoalDto goalDto2;

    @Before
    public void init() throws Exception {
        teamService.deleteAll();

        teamDto1 = new TeamDto("leader1", "team1", "detail1");
        teamService.create(teamDto1);

        tno = teamService.getList().get(0).getTno();
        goalService.deleteAll(tno);
        
        goalDto1 = new GoalDto(tno, "goal1");
        goalDto2 = new GoalDto(tno, "goal2");
    }

    @Test
    public void getCountTest() throws Exception {
        assertEquals(0, goalService.getCount(tno));

        assertEquals(1, goalService.create(goalDto1));
        assertEquals(1, goalService.getCount(tno));

        assertEquals(1, goalService.create(goalDto2));
        assertEquals(2, goalService.getCount(tno));
    }

    @Test
    public void getListTest() throws Exception {
        assertEquals(0, goalService.getCount(tno));

        List<GoalDto> list = goalService.getList(tno);
        assertEquals(0, list.size());

        assertEquals(1, goalService.create(goalDto1));
        list = goalService.getList(tno);
        assertEquals(1, list.size());

        assertEquals(1, goalService.create(goalDto2));
        list = goalService.getList(tno);
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(0, goalService.getCount(tno));

        assertEquals(1, goalService.create(goalDto1));
        assertEquals(1, goalService.getCount(tno));

        assertEquals(1, goalService.create(goalDto2));
        assertEquals(2, goalService.getCount(tno));

        goalService.deleteAll(tno);
        assertEquals(0, goalService.getCount(tno));
    }

    @Test
    public void createTest() throws Exception {
        assertEquals(0, goalService.getCount(tno));

        assertEquals(1, goalService.create(goalDto1));
        assertEquals(1, goalService.getCount(tno));

        assertEquals(1, goalService.create(goalDto2));
        assertEquals(2, goalService.getCount(tno));
    }

    @Test
    public void readTest() throws Exception {
        assertEquals(0, goalService.getCount(tno));

        assertEquals(1, goalService.create(goalDto1));
        assertEquals(1, goalService.getCount(tno));

        Integer gno = goalService.getList(tno).get(0).getGno();
        goalDto1.setGno(gno);

        GoalDto goalDto3 = goalService.read(gno);
        assertEquals(goalDto1, goalDto3);

        assertEquals(1, goalService.create(goalDto2));
        assertEquals(2, goalService.getCount(tno));

        gno = goalService.getList(tno).get(1).getGno();
        goalDto2.setGno(gno);

        GoalDto goalDto4 = goalService.read(gno);
        assertEquals(goalDto2, goalDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, goalService.getCount(tno));

        assertEquals(1, goalService.create(goalDto1));
        assertEquals(1, goalService.getCount(tno));

        Integer gno = goalService.getList(tno).get(0).getGno();

        goalDto1.setGno(gno);
        goalDto1.setName("goal10");

        assertEquals(1, goalService.update(goalDto1));

        GoalDto goalDto3 = goalService.read(gno);
        assertEquals(goalDto1, goalDto3);

        assertEquals(1, goalService.create(goalDto2));
        assertEquals(2, goalService.getCount(tno));

        gno = goalService.getList(tno).get(1).getGno();

        goalDto2.setGno(gno);
        goalDto2.setName("goal20");

        assertEquals(1, goalService.update(goalDto2));

        GoalDto goalDto4 = goalService.read(gno);
        assertEquals(goalDto2, goalDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, goalService.getCount(tno));

        assertEquals(1, goalService.create(goalDto1));
        assertEquals(1, goalService.getCount(tno));

        assertEquals(1, goalService.create(goalDto2));
        assertEquals(2, goalService.getCount(tno));

        Integer gno = goalService.getList(tno).get(0).getGno();

        assertEquals(0, goalService.delete(gno + 111));
        assertEquals(1, goalService.delete(gno));
        assertEquals(1, goalService.getCount(tno));
        assertNull(goalService.read(gno));

        gno = goalService.getList(tno).get(0).getGno();

        assertEquals(0, goalService.delete(gno + 222));
        assertEquals(1, goalService.delete(gno));
        assertEquals(0, goalService.getCount(tno));
        assertNull(goalService.read(gno));
    }
}