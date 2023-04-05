package com.chemax.project.request;


public class SectionUpdateRequest {

    private Integer sectionId;
    private String sectionFullName;
    private String sectionShortName;
    private String sectionConversationalName;

    public SectionUpdateRequest() {
    }

    public SectionUpdateRequest(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionFullName() {
        return sectionFullName;
    }

    public void setSectionFullName(String sectionFullName) {
        this.sectionFullName = sectionFullName;
    }

    public String getSectionShortName() {
        return sectionShortName;
    }

    public void setSectionShortName(String sectionShortName) {
        this.sectionShortName = sectionShortName;
    }

    public String getSectionConversationalName() {
        return sectionConversationalName;
    }

    public void setSectionConversationalName(String sectionConversationalName) {
        this.sectionConversationalName = sectionConversationalName;
    }

}
