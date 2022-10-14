package org.example.parser;

import org.example.domain.Hospital;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HospitalParserTest {
    HospitalParser hospitalParser = new HospitalParser();

    String line1 = "\"A1117873\",\"서울특별시 관악구 신원로 38 5층 (신림동 청암빌딩)\",\"N\",\"치과의원\",\"G099\"," +
            "\"응급의료기관 이외\",\"2\",\"대표번호1 지역번호 추가20170118150453\",\"서울시 송파구 문정동 장지동 법조단지 위례 가락동 가락시장역 " +
            "위치 삼성서울병원 외래교수 출신 구강외과 전문의 진료 진료과목 - 임플란트 치조골 뼈이식 수술 매복 사랑니 발치 턱관절 악관절 질환의 치료 교정 치료 " +
            "및 기타 보존 보철(크라운 브릿지 인레이) 신경치료\",\"서월치안센터 인근 청암빌딩 5층\",\"가로수치과의원\",\"02-882-2750\",\"02-920-5374\",\"1900\",\"2100\"," +
            "\"1900\",\"2100\",\"1900\",\"1400\",\"1500\",\"1500\",\"1000\",\"1000\",\"0930\",\"1400\",\"1000\",\"1000\",\"1000\",\"1000\",\"087\",\"76\"," +
            "\"126.92937673003041\",\"37.48191798611885\",\"2022-01-07 14:54:55.0\"";

    String line4 = "\"A1118077\",\"서울특별시 중구 한강대로 416 지하1층 (남대문로5가 서울스퀘어빌딩)\",\"N\",\"치과의원\",\"G099\",\"응급의료기관 이외\",\"2\",\"법정 공휴일 : 외래 진료 휴진 설날 및 추석 연휴 : 외래 진료 휴진 인공신장실은 정상 진료\",\"발 발목 무릎 어깨 상지  척추 특화 진료\",\"수도권 지하철 2호선 서울대입구역 4번 출구 10M\"," +
            "\"연세건치과의원\",\"02-755-0882\",\"070-4665-9119\",\"1800\",\"1800\",\"1800\",\"1800\",\"1800\",\"1400\",\"1300\",\"1300\",\"0900\",\"0900\",\"0900\",\"0900\",\"0900\",\"1000\"," +
            "\"0900\",\"0900\",\"046\",\"37\",\"126.97375281464887\",\"37.5555048939659\",\"2022-09-07 14:55:29.0\"";

    @Test
    void hospitalParsing(){
        Hospital hospital = hospitalParser.parse(this.line1);

        assertHospital(hospital, "A1117873", "서울특별시 관악구 신원로 38 5층 (신림동 청암빌딩)", "서울특별시 관악구",
                "N", 2, "가로수치과의원", "치과");
        }

    private void assertHospital(Hospital hospital, String eId, String eAddress, String eDistrict, String eCategory, Integer eEmergencyRoom, String eName, String eSubdivision) {
        assertEquals(eId, hospital.getId());
        assertEquals(eAddress, hospital.getAddress());
        assertEquals(eDistrict, hospital.getDistrict());
        assertEquals(eCategory, hospital.getCategory());
        assertEquals(eEmergencyRoom, hospital.getEmergencyRoom());
        assertEquals(eName, hospital.getName());
        assertEquals(eSubdivision, hospital.getSubDivision());
    }

    @Test
    void SqlTest(){
        String sql = "INSERT INTO `likelion-db`.`seoul_hospital`\n" +
                "(`id`,`address`,`district`,`category`,`emergency_room`,`name`,`subdivision`)\n" +
                "VALUES\n" +
                "(\"A1117873\",\n" +
                "\"서울특별시 관악구 신원로 38 5층 (신림동 청암빌딩)\",\n" +
                "\"서울특별시 관악구\",\n" +
                "\"N\",\n" + "2,\n" +
                "\"가로수치과의원\",\n" +
                "\"치과\");";

        Hospital hospital = hospitalParser.parse(line1);
        assertEquals(sql, hospital.getSqlInsertQuery());

    }
}



