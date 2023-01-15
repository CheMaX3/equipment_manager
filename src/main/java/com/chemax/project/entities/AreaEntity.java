package com.chemax.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "operational_area")
public class AreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "full_name")
    private String areaFullName;

    @Column (name = "short_name")
    private String areaShortName;

    @Column (name = "conversational_name")
    private String areaConversationalName;

    public SectionEntity getSectionEntity() {
        return sectionEntity;
    }

    public void setSectionEntity(SectionEntity sectionEntity) {
        this.sectionEntity = sectionEntity;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "section_id")
    private SectionEntity sectionEntity;

    public AreaEntity(String areaFullName, String areaShortName, String areaConversationalName) {
        this.areaFullName = areaFullName;
        this.areaShortName = areaShortName;
        this.areaConversationalName = areaConversationalName;
    }

    public AreaEntity(String fullName) {
    }

    public AreaEntity(String fullName, String conversationalName) {
    }

    public AreaEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
