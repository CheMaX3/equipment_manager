package com.chemax.project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "operational_section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "section_full_name")
    private String sectionFullName;

    @Column (name = "section_short_name")
    private String sectionShortName;

    @Column (name = "section_conversational_name")
    private String sectionConversationalName;

    @OneToMany(mappedBy = "section")
    private List<Area> areas;



    public Section() {
    }

    public Section(String sectionFullName, String sectionShortName, String sectionConversationalName) {
        this.sectionFullName = sectionFullName;
        this.sectionShortName = sectionShortName;
        this.sectionConversationalName = sectionConversationalName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }



}

