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

    private Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName(jdbcDriver);
        Connection connection = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        return connection;
    }

    public void add(User user){
        try{
            //jdbc 로드
            Connection c = makeConnection();
            //2. 쿼리 작성
            PreparedStatement ps = c.prepareStatement("INSERT INTO users values(?,?,?)");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            //3. 쿼리 실행
            ps.executeUpdate();

            //4. 자원 반납
            ps.close();
            c.close();

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id){
        User user;

        try{
            Connection c = makeConnection();

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
            Connection c = makeConnection();

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
        //Constructor로 user 추가
        userDao.add(new User("5", "유재석", "12345677"));

        List<User> allofUser = userDao.findAll();
        for (User user : allofUser) {
            System.out.println(user.getName());
        }

    }
}
