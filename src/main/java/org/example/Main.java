package org.example;

import org.example.domain.Hospital;
import org.example.parser.SqlParser;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //구현체 주입 -> 다형성 적용
        FileManager<Hospital> fileManager = new FileManager<Hospital>(new SqlParser());

        String dataFileName = "seoul_data.txt";
        String sqlFileName = "seoul_hospital.sql";

        //sql 파일 생성 관련
        List<Hospital> sqlFile = fileManager.readFiles(dataFileName);
        fileManager.write(sqlFile, sqlFileName);
    }
}