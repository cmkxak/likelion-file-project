package org.example.parser;

import org.example.domain.Hospital;

public class SqlParser implements Parser<Hospital>{
    @Override
    public Hospital parse(String fileContents) {
        String[] split = fileContents.split(",");

        return new Hospital(split[0],split[1], split[3], Integer.parseInt(split[4]), split[5], split[6]);
    }
}
