package org.example.dao;

import org.example.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    void addAndSelect(){
        //given
        UserDao userDao = new UserDaoFactory().awsUserDao();
        User user = new User("6", "정준하", "1231231313");

        //when
        userDao.add(user);
        User savedUser = userDao.findById(user.getId());

        //then
        assertEquals(user.getName(), savedUser.getName());
    }

}