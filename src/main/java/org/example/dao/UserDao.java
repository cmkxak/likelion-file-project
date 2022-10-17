package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

public class UserDao {
    public void add(){
        //보안을 위한 설정한 환경변수 값들을 map으로 가져오기
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        String jdbcDriver = "com.mysql.cj.jdbc.Driver";

        try{
            //jdbc 로드
            Class.forName(jdbcDriver);

            //1. 커넥션 생성
            //password와 source는 요거를 넣지 마세요.
            Connection c = DriverManager.getConnection(dbHost, dbUser, dbPassword);

            //2. 쿼리 작성
            PreparedStatement ps = c.prepareStatement("INSERT INTO users values(?,?,?)");
            ps.setString(1, "1");
            ps.setString(2, "홍길동");
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

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        userDao.add();
    }
}
