package com.chemax.project.request;

import com.chemax.project.entity.Area;
import com.chemax.project.entity.EquipmentType;

public class EquipmentCreateRequest {

    private String machineModel;
    private String manufacturerCountry;
    private String manufacturer;
    private String manufacturingYear;
    private String machineNumber;
    private String details;
    private EquipmentType equipmentType;
    private Area area;

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

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "EquipmentCreateRequest{" +
                "machineModel='" + machineModel + '\'' +
                ", manufacturerCountry='" + manufacturerCountry + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", manufacturingYear='" + manufacturingYear + '\'' +
                ", machineNumber='" + machineNumber + '\'' +
                ", details='" + details + '\'' +
                ", equipmentType=" + equipmentType +
                ", area=" + area +
                '}';
    }
}
