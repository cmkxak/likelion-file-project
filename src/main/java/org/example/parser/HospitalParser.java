package org.example.parser;

import org.example.domain.Hospital;

public class HospitalParser implements Parser<Hospital>{

    @Override
    public Hospital parse(String fileContents) {
        String files = fileContents.replaceAll("\"", "");

        String[] splitedFiles = files.split(",");

        int emergency = Integer.parseInt(splitedFiles[6]);

        return new Hospital(splitedFiles[0], splitedFiles[1], splitedFiles[2],
                emergency, splitedFiles[10]);
    }

    @Override
    public String formatString(Hospital content) {
        return String.valueOf(content);
    }
}
