package com.collaborateam.www.service;

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
public class UserServiceImplTest {
    @Autowired
    UserService userService;
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
    public void countUserTest() throws Exception {
        userService.deleteAllUser();
        assertEquals(0, userService.countUser());

        assertEquals(1, userService.insertUser(userDto1));
        assertEquals(1, userService.countUser());

        assertEquals(1, userService.insertUser(userDto2));
        assertEquals(2, userService.countUser());
    }

    @Test
    public void selectAllUserTest() throws Exception {
        userService.deleteAllUser();
        assertEquals(0, userService.countUser());

        List<UserDto> userDtoList = userService.selectAllUser();
        assertEquals(0, userDtoList.size());

        assertEquals(1, userService.insertUser(userDto1));
        userDtoList = userService.selectAllUser();
        assertEquals(1, userDtoList.size());

        assertEquals(1, userService.insertUser(userDto2));
        userDtoList = userService.selectAllUser();
        assertEquals(2, userDtoList.size());
    }

    @Test
    public void deleteAllUserTest() throws Exception {
        userService.deleteAllUser();
        assertEquals(0, userService.countUser());

        assertEquals(1, userService.insertUser(userDto1));
        assertEquals(1, userService.countUser());

        assertEquals(1, userService.insertUser(userDto2));
        assertEquals(2, userService.countUser());

        userService.deleteAllUser();
        assertEquals(0, userService.countUser());
    }

    @Test
    public void insertUserTest() throws Exception {
        userService.deleteAllUser();
        assertEquals(0, userService.countUser());

        assertEquals(1, userService.insertUser(userDto1));
        assertEquals(1, userService.countUser());

        assertEquals(1, userService.insertUser(userDto2));
        assertEquals(2, userService.countUser());
    }

    @Test
    public void selectUserTest() throws Exception {
        userService.deleteAllUser();
        assertEquals(0, userService.countUser());

        assertEquals(1, userService.insertUser(userDto1));
        assertEquals(1, userService.countUser());

        String id = userService.selectAllUser().get(0).getId();
        UserDto userDto3 = userService.selectUser(id);
        assertEquals(userDto1, userDto3);

        assertEquals(1, userService.insertUser(userDto2));
        assertEquals(2, userService.countUser());

        id = userService.selectAllUser().get(1).getId();
        UserDto userDto4 = userService.selectUser(id);
        assertEquals(userDto2, userDto4);
    }

    @Test
    public void updateUserTest() throws Exception {
        userService.deleteAllUser();
        assertEquals(0, userService.countUser());

        assertEquals(1, userService.insertUser(userDto1));
        assertEquals(1, userService.countUser());

        birth.set(2002, Calendar.DECEMBER, 19);

        userDto1.setPwd("user10");
        userDto1.setEmail("user10@user10.com");
        userDto1.setName("user10");
        userDto1.setBirth(birth.getTime());

        assertEquals(1, userService.updateUser(userDto1));

        String id = userService.selectAllUser().get(0).getId();
        UserDto userDto3 = userService.selectUser(id);
        assertEquals(userDto1, userDto3);

        assertEquals(1, userService.insertUser(userDto2));
        assertEquals(2, userService.countUser());

        birth.set(2004, Calendar.DECEMBER, 19);

        userDto2.setPwd("user20");
        userDto2.setEmail("user20@user20.com");
        userDto2.setName("user20");
        userDto2.setBirth(birth.getTime());

        assertEquals(1, userService.updateUser(userDto2));

        id = userService.selectAllUser().get(1).getId();
        UserDto userDto4 = userService.selectUser(id);
        assertEquals(userDto2, userDto4);
    }

    @Test
    public void deleteUserTest() throws Exception {
        userService.deleteAllUser();
        assertEquals(0, userService.countUser());

        assertEquals(1, userService.insertUser(userDto1));
        assertEquals(1, userService.countUser());

        assertEquals(1, userService.insertUser(userDto2));
        assertEquals(2, userService.countUser());

        assertEquals(1, userService.deleteUser(userDto1.getId()));
        assertEquals(1, userService.countUser());
        assertNull(userService.selectUser(userDto1.getId()));

        assertEquals(1, userService.deleteUser(userDto2.getId()));
        assertEquals(0, userService.countUser());
        assertNull(userService.selectUser(userDto2.getId()));
    }
}