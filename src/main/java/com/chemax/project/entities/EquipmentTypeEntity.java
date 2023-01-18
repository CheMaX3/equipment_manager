package com.chemax.project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "equipment_type")
public class EquipmentTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column(name = "machine_type")
    private String machineType;

    @OneToMany(mappedBy = "equipmentTypeEntity")
    private List<EquipmentEntity> equipmentEntityList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public List<EquipmentEntity> getEquipmentList() {
        return equipmentEntityList;
    }

    public void setEquipmentList(List<EquipmentEntity> equipmentEntityList) {
        this.equipmentEntityList = equipmentEntityList;
    }

    public EquipmentTypeEntity(Integer id, String machineType, List<EquipmentEntity> equipmentEntityList) {
        this.id = id;
        this.machineType = machineType;
        this.equipmentEntityList = equipmentEntityList;
    }

    public EquipmentTypeEntity() {

    }
}

