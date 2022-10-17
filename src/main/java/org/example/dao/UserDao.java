package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void select(){
        try{
            Class.forName(jdbcDriver);

            Connection c = DriverManager.getConnection(dbHost, dbUser, dbPassword);

            PreparedStatement ps = c.prepareStatement("select * from users where id = \"1\"");

            //결과 가져오기
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.printf("%s\t%s\t%s\t\n"
                        ,rs.getString("id")
                        ,rs.getString("name")
                        ,rs.getString("password"));
            }

            ps.close();
            c.close();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
//        userDao.add();
        userDao.select();
    }
}
