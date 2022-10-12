package org.example;

import org.example.domain.Hospital;
import org.example.parser.HospitalParser;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LineReader<Hospital> lr = new LineReader<Hospital>(new HospitalParser()); //구현체 주입 -> 다형성 적용
        String fileName = "C:\\users\\cmkxak\\Downloads\\서울시 병의원 위치 정보.csv"; //서울시 병의원 위치 정보
        List<Hospital> hospitalFile = lr.readFiles(fileName);

        //file size
        System.out.println(hospitalFile.size());

        //hospital id
        for (Hospital hospital : hospitalFile) {
            System.out.println(hospital.getId());
        }
    }
}