package org.example.dao;

import org.example.connection.AwsConnectionMaker;
import org.example.connection.LocalDbConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory {
    @Bean
    public UserDao awsUserDao(){
        UserDao userDao = new UserDao(new AwsConnectionMaker());
        return userDao;
    }
    @Bean
    public UserDao localUserDao(){
        UserDao userDao = new UserDao(new LocalDbConnectionMaker());
        return userDao;
    }
}
