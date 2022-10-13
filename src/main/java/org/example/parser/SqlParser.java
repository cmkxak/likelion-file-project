package org.example.parser;

import org.example.domain.Hospital;

public class SqlParser implements Parser<Hospital>{
    private final String insertSqlQuery = "INSERT INTO seoul_hospital values (";

    @Override
    public Hospital parse(String fileContents) {
        String[] split = fileContents.split(",");

        return new Hospital(split[0],split[1], split[3], Integer.parseInt(split[4]), split[5]);
    }

    @Override
    public String formatString(Hospital content) {
        StringBuilder sb = new StringBuilder();
        sb.append(insertSqlQuery)
                .append(String.valueOf(content))
                .append(");\n");

        return sb.toString();
    }
}
