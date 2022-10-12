package org.example.parser;

import org.example.domain.Hospital;

public class HospitalParser implements Parser<Hospital>{
    @Override
    public Hospital parse(String fileContents) {
        String[] split = fileContents.split(",");
        return new Hospital(split[0]);
    }
}
