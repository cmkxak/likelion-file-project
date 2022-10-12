package org.example;

import org.example.parser.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//readlines를 재사용할 수 있게 리팩토링을 해보자.
public class FileManager<T> {
    private Parser<T> parser;
    boolean isRemoveFirstLine = true;

    public FileManager(Parser<T> parser) {
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

    public void createAFile(String filename){
        File file = new File(filename);
        try{
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(List<T> fileContents, String filename) throws IOException {
        File file = new File(filename);
        try{
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            for (T content : fileContents) {
                br.write("INSERT INTO seoul_hospital values (" + String.valueOf(content) + ")\n"  );
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
