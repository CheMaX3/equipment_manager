package com.chemax.project.request;

import com.chemax.project.entity.Section;

public class AreaCreateRequest {

    private String areaFullName;
    private String areaShortName;
    private String areaConversationalName;
    private Section section;

    public String getAreaFullName() {
        return areaFullName;
    }

    public void setAreaFullName(String areaFullName) {
        this.areaFullName = areaFullName;
    }

    public String getAreaShortName() {
        return areaShortName;
    }

    public void setAreaShortName(String areaShortName) {
        this.areaShortName = areaShortName;
    }

    public String getAreaConversationalName() {
        return areaConversationalName;
    }

    public void setAreaConversationalName(String areaConversationalName) {
        this.areaConversationalName = areaConversationalName;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "AreaCreateRequest{" +
                "areaFullName='" + areaFullName + '\'' +
                ", areaShortName='" + areaShortName + '\'' +
                ", areaConversationalName='" + areaConversationalName + '\'' +
                ", section=" + section +
                '}';
    }
}
