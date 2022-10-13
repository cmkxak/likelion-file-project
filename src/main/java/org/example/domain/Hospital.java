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
                    String name){
        this.id = id;
        this.address = address;
        this.category = category;
        this.name = name;
        this.emergencyRoom = emergencyRoom;
        this.setSubDivision();
        this.setDistrict();
    }

    public String getId() {
        return id.replace("\"", "");
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getSubDivision() {
        return subDivision;
    }

    public int getEmergencyRoom() {
        return emergencyRoom;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDistrict() {
        String[] address = this.address.split(" ");
        this.district = address[0] + " " + address[1];
    }

    public void setSubDivision(){
        String subDivisionType[] =  {"내과", "외과", "소아", "피부", "성형", "정형외과", "척추", "교정",
                "산부인과", "관절", "봉합", "화상", "골절", "영유아", "안과", "가정의학과", "비뇨기과", "치과", "이비인후과",
                "재활", "한의원", "가정", "요양"
        };

        for (String subDivision : subDivisionType) {
            if(this.name.contains(subDivision)){
                this.subDivision = subDivision;
            }
        }
    }

    @Override
    public String toString() {
        return "\"" + this.id + "\"" + "," + "\"" + this.address + "\"" + "," + "\""
                + this.district + "\"" + "," + "\"" + this.category + "\"" + "," + this.emergencyRoom + "," + "\"" +
                this.name + "\"" + "," + "\"" + this.subDivision + "\"";
    }

}
