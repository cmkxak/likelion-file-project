package org.example.parser;

import org.example.domain.Hospital;

public class HospitalParser implements Parser<Hospital>{
    private String replaceAllQuot(String str){
        return str.replaceAll("\"" , "");
    }

    @Override
    public Hospital parse(String fileContents) {
        String[] split = fileContents.split(",");

        int emergency = Integer.parseInt(split[6].replaceAll("\"", ""));

        return new Hospital(replaceAllQuot(split[0]), replaceAllQuot(split[1]), replaceAllQuot(split[2]),
                emergency, replaceAllQuot(split[10]), null);
    }
}
