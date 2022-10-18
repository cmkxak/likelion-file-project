package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class AwsUserDao extends UserDaoAbstract{
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        Class.forName(jdbcDriver);

        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Connection connection = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        return connection;
    }
}
