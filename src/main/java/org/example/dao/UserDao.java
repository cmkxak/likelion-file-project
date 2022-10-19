package org.example.dao;

import org.example.connection.ConnectionMaker;
import org.example.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user){
        try{
            //jdbc 로드
            Connection c = connectionMaker.makeConnection();
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
            Connection c = connectionMaker.makeConnection();

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
            Connection c = connectionMaker.makeConnection();

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

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.makeConnection();

        PreparedStatement pstmt = connection.prepareStatement("SELECT count(users) as cnt from users");

        int result = pstmt.executeUpdate();
        return result;
    }

    public void deleteAll(){
    }
}
