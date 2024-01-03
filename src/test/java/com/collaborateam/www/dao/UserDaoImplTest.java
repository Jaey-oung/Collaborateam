package com.collaborateam.www.dao;

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
public class UserDaoImplTest {
    @Autowired
    UserDao userDao;
    Calendar birth;
    UserDto userDto1;
    UserDto userDto2;

    @Before
    public void init() {
        birth = Calendar.getInstance();
        birth.clear();
        birth.set(2000, Calendar.DECEMBER, 19); // 2000-12-19

        userDto1 = new UserDto("user1", "user1", "user1@user1.com", "user1", birth.getTime()); // Calendar -> Date
        userDto2 = new UserDto("user2", "user2", "user2@user2.com", "user2", birth.getTime()); // Calendar -> Date
    }

    @Test
    public void countTest() throws Exception {
        userDao.deleteAll();
        assertEquals(0, userDao.count());

        assertEquals(1, userDao.insert(userDto1));
        assertEquals(1, userDao.count());

        assertEquals(1, userDao.insert(userDto2));
        assertEquals(2, userDao.count());
    }

    @Test
    public void selectAllTest() throws Exception {
        userDao.deleteAll();
        assertEquals(0, userDao.count());

        List<UserDto> userDtoList = userDao.selectAll();
        assertEquals(0, userDtoList.size());

        assertEquals(1, userDao.insert(userDto1));
        userDtoList = userDao.selectAll();
        assertEquals(1, userDtoList.size());

        assertEquals(1, userDao.insert(userDto2));
        userDtoList = userDao.selectAll();
        assertEquals(2, userDtoList.size());
    }

    @Test
    public void deleteAllTest() throws Exception {
        userDao.deleteAll();
        assertEquals(0, userDao.count());

        assertEquals(1, userDao.insert(userDto1));
        assertEquals(1, userDao.count());

        assertEquals(1, userDao.insert(userDto2));
        assertEquals(2, userDao.count());

        userDao.deleteAll();
        assertEquals(0, userDao.count());
    }

    @Test
    public void insertTest() throws Exception {
        userDao.deleteAll();
        assertEquals(0, userDao.count());

        assertEquals(1, userDao.insert(userDto1));
        assertEquals(1, userDao.count());

        assertEquals(1, userDao.insert(userDto2));
        assertEquals(2, userDao.count());
    }

    @Test
    public void selectTest() throws Exception {
        userDao.deleteAll();
        assertEquals(0, userDao.count());

        assertEquals(1, userDao.insert(userDto1));
        assertEquals(1, userDao.count());

        String id = userDao.selectAll().get(0).getId();
        UserDto userDto3 = userDao.select(id);
        assertEquals(userDto1, userDto3);

        assertEquals(1, userDao.insert(userDto2));
        assertEquals(2, userDao.count());

        id = userDao.selectAll().get(1).getId();
        UserDto userDto4 = userDao.select(id);
        assertEquals(userDto2, userDto4);
    }

    @Test
    public void updateTest() throws Exception {
        userDao.deleteAll();
        assertEquals(0, userDao.count());

        assertEquals(1, userDao.insert(userDto1));
        assertEquals(1, userDao.count());

        birth.set(2002, Calendar.DECEMBER, 19);

        userDto1.setPwd("user10");
        userDto1.setEmail("user10@user10.com");
        userDto1.setName("user10");
        userDto1.setBirth(birth.getTime());

        assertEquals(1, userDao.update(userDto1));

        String id = userDao.selectAll().get(0).getId();
        UserDto userDto3 = userDao.select(id);
        assertEquals(userDto1, userDto3);

        assertEquals(1, userDao.insert(userDto2));
        assertEquals(2, userDao.count());

        birth.set(2004, Calendar.DECEMBER, 19);

        userDto2.setPwd("user20");
        userDto2.setEmail("user20@user20.com");
        userDto2.setName("user20");
        userDto2.setBirth(birth.getTime());

        assertEquals(1, userDao.update(userDto2));

        id = userDao.selectAll().get(1).getId();
        UserDto userDto4 = userDao.select(id);
        assertEquals(userDto2, userDto4);
    }

    @Test
    public void deleteTest() throws Exception {
        userDao.deleteAll();
        assertEquals(0, userDao.count());

        assertEquals(1, userDao.insert(userDto1));
        assertEquals(1, userDao.count());

        assertEquals(1, userDao.insert(userDto2));
        assertEquals(2, userDao.count());

        assertEquals(1, userDao.delete(userDto1.getId()));
        assertEquals(1, userDao.count());
        assertNull(userDao.select(userDto1.getId()));

        assertEquals(1, userDao.delete(userDto2.getId()));
        assertEquals(0, userDao.count());
        assertNull(userDao.select(userDto2.getId()));
    }
}