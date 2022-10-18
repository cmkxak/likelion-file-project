package org.example.dao;

import org.example.connection.ConnectionMaker;
import org.example.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    void addAndSelect(){
        //given
        UserDao userDao = new UserDao(new ConnectionMaker());
        User user = new User("4", "정준하", "1231231313");

        //when
        userDao.add(user);
        User savedUser = userDao.findById(user.getId());

        //then
        assertEquals(user.getName(), savedUser.getName());
    }

}