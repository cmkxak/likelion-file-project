package org.example.dao;

import org.example.FileManager;
import org.example.domain.User;
import org.example.parser.SqlParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {

    private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    //보안을 위해 설정한 환경변수 값들을 map으로 가져오기
    private Map<String, String> env = System.getenv();
    private String dbHost = env.get("DB_HOST");
    private String dbUser = env.get("DB_USER");
    private String dbPassword = env.get("DB_PASSWORD");

    public void add(){
        try{
            //jdbc 로드
            Class.forName(jdbcDriver);

            //1. 커넥션 생성
            //password와 source는 요거를 넣지 마세요.
            Connection c = DriverManager.getConnection(dbHost, dbUser, dbPassword);

            //2. 쿼리 작성
            PreparedStatement ps = c.prepareStatement("INSERT INTO users values(?,?,?)");
            ps.setString(1, "2");
            ps.setString(2, "김길동");
            ps.setString(3, "password");

            //3. 쿼리 실행
            ps.executeUpdate();

            //4. 자원 반납
            ps.close();
            c.close();

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String id){
        User user;

        try{
            Class.forName(jdbcDriver);

            Connection c = DriverManager.getConnection(dbHost, dbUser, dbPassword);

            PreparedStatement pstmt = c.prepareStatement("select * from users where id = ?");

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            rs.next(); //하나 읽어오기

            user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));

            rs.close();
            pstmt.close();
            c.close();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return user;
    }

    public List<User> findAll(){
        List<User> userList = new ArrayList<>();
        try {
            Class.forName(jdbcDriver);

            Connection c = DriverManager.getConnection(dbHost, dbUser, dbPassword);
            Statement statement = c.createStatement();

            ResultSet rs = statement.executeQuery("select * from users");

            while (rs.next()) {
                User user = new User(rs.getString("id"),
                        rs.getString("name"), rs.getString("password"));
                userList.add(user);
            }

            rs.close();
            statement.close();
            c.close();
        }catch(Exception e){
                throw new RuntimeException(e);
            }
        return userList;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        List<User> allofUser = userDao.findAll();
        for (User user : allofUser) {
            System.out.println(user.getName());
        }

    }
}
