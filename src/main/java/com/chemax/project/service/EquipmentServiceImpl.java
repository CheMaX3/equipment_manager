package com.chemax.project.service;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.entity.Area;
import com.chemax.project.entity.Equipment;
import com.chemax.project.entity.EquipmentType;
import com.chemax.project.exception.EntityNotFoundException;
import com.chemax.project.repository.EquipmentRepository;
import com.chemax.project.request.EquipmentCreateRequest;
import com.chemax.project.request.EquipmentUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final DocumentServiceImpl documentServiceImpl;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, DocumentServiceImpl documentServiceImpl) {
        this.equipmentRepository = equipmentRepository;
        this.documentServiceImpl = documentServiceImpl;
    }

    public EquipmentDTO createEquipment(EquipmentCreateRequest equipmentCreateRequest) {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        try {
            Area area = equipmentCreateRequest.getArea();
            EquipmentType equipmentType = equipmentCreateRequest.getEquipmentType();
            Equipment equipment = equipmentRepository.save(buildEquipmentFromRequest(equipmentCreateRequest,
                    area, equipmentType));
            equipmentDTO = convertEquipmentToDTO(equipment);
        } catch (Exception ex) {
            log.error("Can't save object " + equipmentCreateRequest.toString());
        }
        return equipmentDTO;
    }

    public List<EquipmentDTO> getAllEquipmentDTOs() {
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        try {
            equipmentDTOList = equipmentRepository.findAll().stream()
                    .map(this::convertEquipmentToDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
        }
        return equipmentDTOList;
    }

    public void deleteEquipment(Integer equipmentId) {
        try {
            equipmentRepository.delete(equipmentRepository.getReferenceById(equipmentId));
        } catch (Exception ex) {
            log.error("Can't delete object with id=" + equipmentId);
        }
    }

    public EquipmentDTO updateEquipment(EquipmentUpdateRequest equipmentUpdateRequest) {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        try {
            Equipment equipmentFromDB = equipmentRepository.findById(equipmentUpdateRequest.getEquipmentId())
                    .orElseThrow(EntityNotFoundException::new);
            equipmentFromDB.setMachineModel(Optional.ofNullable(equipmentUpdateRequest.getMachineModel())
                    .orElse(equipmentFromDB.getMachineModel()));
            equipmentFromDB.setManufacturerCountry(Optional.ofNullable(equipmentUpdateRequest.getManufacturerCountry())
                    .orElse(equipmentFromDB.getManufacturerCountry()));
            equipmentFromDB.setManufacturer(Optional.ofNullable(equipmentUpdateRequest
                    .getManufacturer()).orElse(equipmentFromDB.getManufacturer()));
            equipmentFromDB.setManufacturingYear(Optional.ofNullable(equipmentUpdateRequest
                    .getManufacturingYear()).orElse(equipmentFromDB.getManufacturingYear()));
            equipmentFromDB.setMachineNumber(Optional.ofNullable(equipmentUpdateRequest
                    .getMachineNumber()).orElse(equipmentFromDB.getMachineNumber()));
            equipmentFromDB.setDetails(Optional.ofNullable(equipmentUpdateRequest
                    .getDetails()).orElse(equipmentFromDB.getDetails()));
            equipmentFromDB.setEquipmentType(Optional.ofNullable(equipmentUpdateRequest
                    .getEquipmentType()).orElse(equipmentFromDB.getEquipmentType()));
            equipmentFromDB.setArea(Optional.ofNullable(equipmentUpdateRequest
                    .getArea()).orElse(equipmentFromDB.getArea()));
            equipmentRepository.save(equipmentFromDB);
            equipmentDTO = convertEquipmentToDTO(equipmentFromDB);
        } catch (Exception ex) {
            log.error("Can't save object with id=" + equipmentUpdateRequest.getEquipmentId());
        }
        return equipmentDTO;
    }

    public List<EquipmentDTO> getEquipmentListByAreaId(Integer areaId) {
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        try {
            equipmentDTOList = equipmentRepository.findByAreaId(areaId).stream()
                    .map(this::convertEquipmentToDTO).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Can't retrieve objects from DB affiliated with areaId=" + areaId);
        }
        return equipmentDTOList;
    }

    public List<EquipmentDTO> getEquipmentListByEquipmentTypeId(Integer equipmentTypeId) {
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        try {
            equipmentDTOList = equipmentRepository.findByEquipmentTypeId(equipmentTypeId).stream()
                    .map(this::convertEquipmentToDTO).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Can't retrieve objects from DB affiliated with equipmentTypeId=" + equipmentTypeId);
        }
        return equipmentDTOList;
    }

    public EquipmentDTO getEquipmentDTO(Integer id) {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        try {
            equipmentDTO = convertEquipmentToDTO(equipmentRepository.getReferenceById(id));
        } catch (Exception ex) {
            log.error("Can't retrieve object from DB with id=" + id);
        }
        return equipmentDTO;
    }

    private Equipment buildEquipmentFromRequest(EquipmentCreateRequest equipmentCreateRequest,
                                                Area area, EquipmentType equipmentType) {
        Equipment builtEquipment = new Equipment();
        builtEquipment.setMachineModel(equipmentCreateRequest.getMachineModel());
        builtEquipment.setManufacturerCountry(equipmentCreateRequest.getManufacturerCountry());
        builtEquipment.setManufacturer(equipmentCreateRequest.getManufacturer());
        builtEquipment.setManufacturingYear(equipmentCreateRequest.getManufacturingYear());
        builtEquipment.setMachineNumber(equipmentCreateRequest.getMachineNumber());
        builtEquipment.setDetails(equipmentCreateRequest.getDetails());
        builtEquipment.setArea(area);
        builtEquipment.setEquipmentType(equipmentType);
        return builtEquipment;
    }

        EquipmentDTO convertEquipmentToDTO(Equipment equipment) {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setId(equipment.getId());
        equipmentDTO.setMachineModel(equipment.getMachineModel());
        equipmentDTO.setManufacturerCountry(equipment.getManufacturerCountry());
        equipmentDTO.setManufacturer(equipment.getManufacturer());
        equipmentDTO.setManufacturingYear(equipment.getManufacturingYear());
        equipmentDTO.setMachineNumber(equipment.getMachineNumber());
        equipmentDTO.setDetails(equipment.getDetails());
        equipmentDTO.setArea(equipment.getArea());
        equipmentDTO.setEquipmentType(equipment.getEquipmentType());
        equipmentDTO.setFiles(documentServiceImpl.getAllDocumentByEquipmentId(equipment.getId()));
        return equipmentDTO;
    }
}
