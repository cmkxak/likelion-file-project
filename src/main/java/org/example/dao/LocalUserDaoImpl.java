package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class LocalUserDaoImpl extends UserDaoAbstract{
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        Class.forName(jdbcDriver);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/likelion-db", "root", "12345");
        return connection;

    }
}
