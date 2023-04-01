package com.chemax.project.request;


public class SectionCreateRequest {

    private String sectionFullName;
    private String sectionShortName;
    private String sectionConversationalName;

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
