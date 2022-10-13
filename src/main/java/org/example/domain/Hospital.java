package org.example.domain;

public class Hospital {
    private String id;
    private String address; //주소
    private String district; //구
    private String category; //카테고리
    private String name; //병원명
    private String subDivision; //세부분과
    private int emergencyRoom; //응급 운영 현황


    public Hospital(String id){
        this.id = id;
    }

    public Hospital(String id, String address, String category, int emergencyRoom,
                    String name, String subDivision){
        this.id = id;
        this.address = address;
        this.category = category;
        this.name = name;
        this.emergencyRoom = emergencyRoom;
        this.subDivision = subDivision;
        this.setDistrict();
    }

    public String getId() {
        return id.replace("\"", "");
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDistrict() {
        String[] address = this.address.split(" ");
        this.district = address[0] + " " + address[1];
    }

    @Override
    public String toString() {
        return "\"" + this.id + "\"" + "," + "\"" + this.address + "\"" + "," + "\""
                + this.district + "\"" + "," + "\"" + this.category + "\"" + "," + this.emergencyRoom + "," + "\"" +
                this.name + "\"" + "," + this.subDivision;
    }

}
