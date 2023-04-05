package com.chemax.project.request;

public class EquipmentTypeCreateRequest {

    private String machineType;

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public EquipmentTypeCreateRequest() {
    }

    @Override
    public String toString() {
        return "EquipmentTypeCreateRequest{" +
                "machineType='" + machineType + '\'' +
                '}';
    }
}
