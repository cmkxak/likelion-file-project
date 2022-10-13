package org.example.parser;


//병원정보, 인구 정보를 읽느냐에 따라서 파싱 정보가 달라질 수도 있음 -> 구현체만 바꿔 끼워주면 된다.
public interface Parser <T>{
    T parse(String fileContents); //인터페이스를 하나 선언
    String formatString(T content);
}
