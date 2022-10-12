package org.example;

import org.example.parser.Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//readlines를 재사용할 수 있게 리팩토링을 해보자.
public class LineReader<T> {
    private Parser<T> parser;
    boolean isRemoveFirstLine = true;

    public LineReader(Parser<T> parser) {
        this.parser = parser;
    }

    public List<T> readFiles(String filename) throws IOException {
        List<T> file = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        if(isRemoveFirstLine){
            br.readLine(); //첫줄은 컬럼명이라 일단 허공에 이런식으로 날려줌.
        }
        while((str = br.readLine()) != null){
            file.add(parser.parse(str));
        }
        return file;
    }

}
