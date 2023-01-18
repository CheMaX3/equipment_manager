package com.chemax.project.request;

public class EquipmentTypeRequest {
    private String machineType;

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public EquipmentTypeRequest(String machineType) {
        this.machineType = machineType;
    }

    public EquipmentTypeRequest () {

    }
}
