package com.czs.practice.springlearn.dto;

public class AreaDto {

    public String areaCode;

    public String areaName;

    public String pCode;

    public String level;

    @Override
    public String toString() {
        return areaCode + ","+areaName + "," + pCode + "," + level;
    }

    public String toStringWithLevel() {
        StringBuilder sb = new StringBuilder();
        sb.append(areaCode).append(",").append(areaName).append(",").append(pCode).append(",");
        if("province".equals(level)){
            sb.append("1");
        }else if("city".equals(level)){
            sb.append("2");
        }else if("district".equals(level)){
            sb.append("3");
        }
        return sb.toString();
    }

    public AreaDto(String areaCode, String areaName, String pCode, String level) {
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.pCode = pCode;
        this.level = level;
    }
}
