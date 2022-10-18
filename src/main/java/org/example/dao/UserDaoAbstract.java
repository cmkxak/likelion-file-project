package org.example.dao;

import org.example.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class UserDaoAbstract {
    public abstract Connection makeConnection() throws ClassNotFoundException, SQLException;
}
