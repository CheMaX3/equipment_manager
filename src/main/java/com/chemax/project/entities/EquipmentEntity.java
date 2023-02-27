package com.chemax.project.entities;

import javax.persistence.*;

@Entity
@Table(name = "equipment")
public class EquipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "machine_model")
    private String machineModel;

    @Column(name = "manufacturer_country")
    private String manufacturerCountry;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "manufacturing_year")
    private String manufacturingYear;

    @Column(name = "machine_number")
    private String machineNumber;

    @Column(name = "details")
    private String details;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private EquipmentTypeEntity equipmentTypeEntity;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaEntity areaEntity;

    public EquipmentEntity() {
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public EquipmentTypeEntity getEquipmentTypeEntity() {
        return equipmentTypeEntity;
    }

    public void setEquipmentType(EquipmentTypeEntity equipmentTypeEntity) {
        this.equipmentTypeEntity = equipmentTypeEntity;
    }

    public EquipmentEntity(Integer id, String machineModel, String manufacturerCountry, String manufacturer, String manufacturingYear, String machineNumber, String details, AreaEntity areaEntity) {
        this.id = id;
        this.machineModel = machineModel;
        this.manufacturerCountry = manufacturerCountry;
        this.manufacturer = manufacturer;
        this.manufacturingYear = manufacturingYear;
        this.machineNumber = machineNumber;
        this.details = details;
        this.areaEntity = areaEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineModel() {
        return machineModel;
    }

    public void setMachineModel(String machineModel) {
        this.machineModel = machineModel;
    }

    public String getManufacturerCountry() {
        return manufacturerCountry;
    }

    public void setManufacturerCountry(String manufacturerCountry) {
        this.manufacturerCountry = manufacturerCountry;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(String manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public AreaEntity getAreaEntity() {
        return areaEntity;
    }

    public void setAreaEntity(AreaEntity areaEntity) {
        this.areaEntity = areaEntity;
    }
}
