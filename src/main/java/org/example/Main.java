package org.example;

import org.example.domain.Hospital;
import org.example.parser.SqlParser;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileManager<Hospital> fileManager = new FileManager<Hospital>(new SqlParser()); //구현체 주입 -> 다형성 적용
//      String fileName = "C:\\users\\cmkxak\\Downloads\\서울시 병의원 위치 정보.csv"; //서울시 병의원 위치 정보

        String dataFileName = "seoul_data.txt";

        String sqlFileName = "seoul_hospital.sql";
        List<Hospital> sqlFile = fileManager.readFiles(dataFileName); //sql 파일 생성 관련
        fileManager.write(sqlFile, sqlFileName); //sql 파일 내용 작성

//      List<Hospital> hospitalFile = fileManager.readFiles(fileName); //데이터 생성 파일 관련

//        System.out.println(hospitalFile.size());

        //hospital id
//        for (Hospital hospital : hospitalFile) {
//            System.out.println(hospital);
//        }

//        fileManager.createAFile(dataFileName); //파일 생성
//        fileManager.createAFile(sqlFileName); //sql 파일 생성

//        fileManager.write(hospitalFile, dataFileName); //파일 내용 작성
    }
}