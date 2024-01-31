package com.collaborateam.www.service;

import com.collaborateam.www.domain.TaskDto;
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
public class TaskServiceImplTest {
    @Autowired
    TeamService teamService;
    @Autowired
    TaskService taskService;
    Integer tno;
    TeamDto teamDto1;
    TaskDto taskDto1;
    TaskDto taskDto2;

    @Before
    public void init() throws Exception {
        teamService.deleteAll();

        teamDto1 = new TeamDto("leader1", "team1", "detail1");
        teamService.create(teamDto1);

        tno = teamService.getList().get(0).getTno();
        taskService.deleteAll(tno);
        
        taskDto1 = new TaskDto(tno, teamDto1.getLeader(), "task1");
        taskDto2 = new TaskDto(tno, teamDto1.getLeader(), "task2");
    }

    @Test
    public void getCountTest() throws Exception {
        assertEquals(0, taskService.getCount(tno));

        assertEquals(1, taskService.create(taskDto1));
        assertEquals(1, taskService.getCount(tno));

        assertEquals(1, taskService.create(taskDto2));
        assertEquals(2, taskService.getCount(tno));
    }

    @Test
    public void getListTest() throws Exception {
        assertEquals(0, taskService.getCount(tno));

        List<TaskDto> list = taskService.getList(tno);
        assertEquals(0, list.size());

        assertEquals(1, taskService.create(taskDto1));
        list = taskService.getList(tno);
        assertEquals(1, list.size());

        assertEquals(1, taskService.create(taskDto2));
        list = taskService.getList(tno);
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        assertEquals(0, taskService.getCount(tno));

        assertEquals(1, taskService.create(taskDto1));
        assertEquals(1, taskService.getCount(tno));

        assertEquals(1, taskService.create(taskDto2));
        assertEquals(2, taskService.getCount(tno));

        taskService.deleteAll(tno);
        assertEquals(0, taskService.getCount(tno));
    }

    @Test
    public void createTest() throws Exception {
        assertEquals(0, taskService.getCount(tno));

        assertEquals(1, taskService.create(taskDto1));
        assertEquals(1, taskService.getCount(tno));

        assertEquals(1, taskService.create(taskDto2));
        assertEquals(2, taskService.getCount(tno));
    }

    @Test
    public void readTest() throws Exception {
        assertEquals(0, taskService.getCount(tno));

        assertEquals(1, taskService.create(taskDto1));
        assertEquals(1, taskService.getCount(tno));

        Integer tano = taskService.getList(tno).get(0).getTano();
        taskDto1.setTano(tano);

        TaskDto taskDto3 = taskService.read(tano);
        assertEquals(taskDto1, taskDto3);

        assertEquals(1, taskService.create(taskDto2));
        assertEquals(2, taskService.getCount(tno));

        tano = taskService.getList(tno).get(1).getTano();
        taskDto2.setTano(tano);

        TaskDto taskDto4 = taskService.read(tano);
        assertEquals(taskDto2, taskDto4);
    }

    @Test
    public void updateTest() throws Exception {
        assertEquals(0, taskService.getCount(tno));

        assertEquals(1, taskService.create(taskDto1));
        assertEquals(1, taskService.getCount(tno));

        Integer tano = taskService.getList(tno).get(0).getTano();
        String member = taskService.getList(tno).get(0).getMember();

        taskDto1.setTano(tano);
        taskDto1.setMember(member);
        taskDto1.setName("task10");

        assertEquals(1, taskService.update(taskDto1));

        TaskDto taskDto3 = taskService.read(tano);
        assertEquals(taskDto1, taskDto3);

        assertEquals(1, taskService.create(taskDto2));
        assertEquals(2, taskService.getCount(tno));

        tano = taskService.getList(tno).get(1).getTano();
        member = taskService.getList(tno).get(1).getMember();

        taskDto2.setTano(tano);
        taskDto2.setMember(member);
        taskDto2.setName("task20");

        assertEquals(1, taskService.update(taskDto2));

        TaskDto taskDto4 = taskService.read(tano);
        assertEquals(taskDto2, taskDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        assertEquals(0, taskService.getCount(tno));

        assertEquals(1, taskService.create(taskDto1));
        assertEquals(1, taskService.getCount(tno));

        assertEquals(1, taskService.create(taskDto2));
        assertEquals(2, taskService.getCount(tno));

        Integer tano = taskService.getList(tno).get(0).getTano();
        String member = taskService.getList(tno).get(0).getMember();

        assertEquals(0, taskService.delete(tano + 111, member));
        assertEquals(0, taskService.delete(tano, member + "111"));
        assertEquals(0, taskService.delete(tano + 111, member + "111"));
        assertEquals(1, taskService.delete(tano, member));
        assertEquals(1, taskService.getCount(tno));
        assertNull(taskService.read(tano));

        tano = taskService.getList(tno).get(0).getTano();
        member = taskService.getList(tno).get(0).getMember();

        assertEquals(0, taskService.delete(tano + 222, member));
        assertEquals(0, taskService.delete(tano, member + "222"));
        assertEquals(0, taskService.delete(tano + 222, member + "222"));
        assertEquals(1, taskService.delete(tano, member));
        assertEquals(0, taskService.getCount(tno));
        assertNull(taskService.read(tano));
    }

    @Test
    public void getMemberTaskTest() throws Exception {
        List<TaskDto> list = taskService.getMemberTask(tno, teamDto1.getLeader());
        assertEquals(0, list.size());

        assertEquals(1, taskService.create(taskDto1));

        list = taskService.getMemberTask(tno, teamDto1.getLeader());
        assertEquals(1, list.size());

        assertEquals(1, taskService.create(taskDto2));

        list = taskService.getMemberTask(tno, teamDto1.getLeader());
        assertEquals(2, list.size());
    }
}