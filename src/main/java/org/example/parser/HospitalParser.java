package org.example.parser;

import org.example.domain.Hospital;

public class HospitalParser implements Parser<Hospital>{
    @Override
    public Hospital parse(String fileContents) {
        String[] split = fileContents.split(",");

        int emergency = Integer.parseInt(split[6].replaceAll("\"", ""));

        return new Hospital(split[0], split[1], split[2],
                emergency, split[10], null);
    }
}
