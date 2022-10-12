package org.example.domain;

public class Hospital {
    private String id;
    private String address;

    public Hospital(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
