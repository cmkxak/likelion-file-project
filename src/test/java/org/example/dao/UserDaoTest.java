package org.example.dao;

import org.example.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {
    @Autowired
    ApplicationContext ac;

    @Test
    void addAndSelect(){
        //given
        UserDao userDao = ac.getBean("awsUserDao", UserDao.class);
        User user = new User("9", "정준하", "1231231313");

        //when
        userDao.add(user);
        User savedUser = userDao.findById(user.getId());

        //then
        assertEquals(user.getName(), savedUser.getName());
    }

}