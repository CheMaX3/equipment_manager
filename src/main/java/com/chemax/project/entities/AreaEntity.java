package com.chemax.project.entities;

import javax.persistence.*;

@Entity
@Table(name = "operational_area")
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;
    @Column (name = "area_full_name")
    private String areaFullName;
    @Column (name = "area_short_name")
    private String areaShortName;
    @Column (name = "area_conversational_name")
    private String areaConversationalName;
    //@Column (name = "section_id")
   //private Integer sectionId;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private SectionEntity sectionEntity;

    public AreaEntity(String areaFullName, String areaShortName, String areaConversationalName, Integer sectionId) {
        this.areaFullName = areaFullName;
        this.areaShortName = areaShortName;
        this.areaConversationalName = areaConversationalName;
        //this.sectionId = sectionId;
    }

    public AreaEntity(String fullName, String shortName, String conversationalName){
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
//
//    public Integer getSectionId() {
//        return sectionId;
//    }
//
//    public void setSectionId(Integer sectionId) {
//        this.sectionId = sectionId;
//    }
}
