package org.example.connection;

import org.example.FileManager;
import org.example.domain.Hospital;
import org.example.parser.SqlParser;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class ConnectionMaker {
    private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private Map<String, String> env = System.getenv();

    String dbHost = env.get("DB_HOST");
    String dbUser = env.get("DB_USER");
    String dbPassword = env.get("DB_PASSWORD");

    public Connection getConnection() throws IOException, SQLException, ClassNotFoundException {

        Class.forName(jdbcDriver);

        Connection c = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        return c;
    }

}
