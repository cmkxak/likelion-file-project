package org.example.dao;

import org.example.connection.AwsConnectionMaker;
import org.example.connection.LocalDbConnectionMaker;

public class UserDaoFactory {
    public UserDao awsUserDao(){
        UserDao userDao = new UserDao(new AwsConnectionMaker());
        return userDao;
    }

    public UserDao localUserDao(){
        UserDao userDao = new UserDao(new LocalDbConnectionMaker());
        return userDao;
    }
}
