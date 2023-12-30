package com.collaborateam.www.dao;


import com.collaborateam.www.domain.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {
    @Autowired
    UserDao userDao;
    Calendar calendar;
    UserDto userDto1;
    UserDto userDto2;

    @Before
    public void init() {
        calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.DECEMBER, 30); // 2023-12-30

        userDto1 = new UserDto("user1", "user1", "user1@user1.com", "user1", calendar.getTime(), calendar.getTime(), calendar.getTime()); // Calendar -> Date
        userDto2 = new UserDto("user2", "user2", "user2@user2.com", "user2", calendar.getTime(), calendar.getTime(), calendar.getTime()); // Calendar -> Date
    }

    @Test
    public void insertUserTest() throws Exception {
        userDao.deleteAllUser();
        assertEquals(0, userDao.countUser());

        assertEquals(1, userDao.insertUser(userDto1));
        assertEquals(1, userDao.countUser());

        assertEquals(1, userDao.insertUser(userDto2));
        assertEquals(2, userDao.countUser());

        System.out.println("userDto1 = " + userDto1);
        System.out.println("userDto2 = " + userDto2);

        userDao.deleteAllUser();
        assertEquals(0, userDao.countUser());
    }
}