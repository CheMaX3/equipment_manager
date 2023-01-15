package com.chemax.project.request;

public class AreaRequest {
    private String areaFullName;
    private String areaShortName;
    private String areaConversationalName;
    private Integer sectionId;

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

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
}
