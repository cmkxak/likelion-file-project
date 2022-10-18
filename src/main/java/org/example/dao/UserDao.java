package org.example.dao;

import org.example.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private UserDaoAbstract abstractUserDao;

    public UserDao(UserDaoAbstract userDaoAbstract) {
        this.abstractUserDao = userDaoAbstract;
    }

    public void add(User user){
        try{
            //jdbc 로드
            Connection c = abstractUserDao.makeConnection();
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
            Connection c = abstractUserDao.makeConnection();

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
            Connection c = abstractUserDao.makeConnection();

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
        UserDao userDao = new UserDao(new AwsUserDao());
        //Constructor로 user 추가
        userDao.add(new User("5", "유재석", "12345677"));

        List<User> allofUser = userDao.findAll();
        for (User user : allofUser) {
            System.out.println(user.getName());
        }

    }
}
