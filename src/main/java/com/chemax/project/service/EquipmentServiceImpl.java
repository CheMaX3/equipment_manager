package com.chemax.project.service;

import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.entity.Area;
import com.chemax.project.entity.Equipment;
import com.chemax.project.entity.EquipmentType;
import com.chemax.project.exception.EntityNotFoundException;
import com.chemax.project.repository.AreaRepository;
import com.chemax.project.repository.EquipmentRepository;
import com.chemax.project.repository.EquipmentTypeRepository;
import com.chemax.project.request.EquipmentRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final AreaRepository areaRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;
    private final EquipmentRepository equipmentRepository;
    private final DocumentServiceImpl documentServiceImpl;

    public EquipmentServiceImpl(AreaRepository areaRepository, EquipmentTypeRepository equipmentTypeRepository,
                                EquipmentRepository equipmentRepository, DocumentServiceImpl documentServiceImpl) {
        this.areaRepository = areaRepository;
        this.equipmentTypeRepository = equipmentTypeRepository;
        this.equipmentRepository = equipmentRepository;
        this.documentServiceImpl = documentServiceImpl;
    }

    public EquipmentDTO createEquipmentEntity(EquipmentRequest request) {
        Area area = areaRepository.getReferenceById(request.getAreaId());
        EquipmentType equipmentType = equipmentTypeRepository.getReferenceById(request.getMachineTypeId());
        Equipment equipment = buildEquipmentEntityFromRequest(request, area, equipmentType);
        equipmentRepository.save(equipment);
        return convertEquipmentEntityToDTO(equipment);
    }

    private Equipment getEquipmentEntity (Integer id) throws EntityNotFoundException {
        return equipmentRepository.getReferenceById(id);
    }

    public EquipmentDTO getEquipmentDTO (Integer id) {
        return convertEquipmentEntityToDTO(getEquipmentEntity(id));
    }

    public List<EquipmentDTO> getAllEquipmentDTOs() {
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        for (Equipment e: equipmentRepository.findAll()) {
            equipmentDTOList.add(convertEquipmentEntityToDTO(e));
        }
        return equipmentDTOList;
    }

    public List<EquipmentDTO> getEquipmentDTOsByCount (Integer count) {
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        for (Equipment e: equipmentRepository.findAll()) {
            equipmentDTOList.add(convertEquipmentEntityToDTO(e));
        }
        return equipmentDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public List<EquipmentDTO> getAllEquipmentSelectedAreaDTOs(Integer id) {
        List<EquipmentDTO> equipmentDTOList = getAllEquipmentDTOs();
        List<EquipmentDTO> equipmentSelectedAreaDTOList = new ArrayList<>();
        for (EquipmentDTO e : equipmentDTOList) {
            if (Objects.equals(e.getAreaId(), id)) {
                equipmentSelectedAreaDTOList.add(e);
            }
        }
        return equipmentSelectedAreaDTOList;
    }

    public List<EquipmentDTO> getAllEquipmentSelectedMachineTypeDTOs(Integer id) {
        List<EquipmentDTO> equipmentDTOList = getAllEquipmentDTOs();
        List<EquipmentDTO> equipmentSelectedMachineTypeDTOList = new ArrayList<>();
        for (EquipmentDTO e : equipmentDTOList) {
            if (Objects.equals(e.getMachineTypeId(), id)) {
                equipmentSelectedMachineTypeDTOList.add(e);
            }
        }
        return equipmentSelectedMachineTypeDTOList;
    }

    public void deleteEquipmentEntity (Integer id) {
        equipmentRepository.delete(getEquipmentEntity(id));
    }

    public void updateEquipmentEntity (EquipmentDTO equipmentDTO, Integer id) {
        Equipment equipment = equipmentRepository.getReferenceById(id);
        equipment.setMachineModel(Optional.ofNullable(equipmentDTO.getMachineModel()).orElse(equipment
                .getMachineModel()));
        equipment.setManufacturerCountry(Optional.ofNullable(equipmentDTO.getManufacturerCountry())
                .orElse(equipment.getManufacturerCountry()));
        equipment.setManufacturer(Optional.ofNullable(equipmentDTO.getManufacturer()).orElse(equipment
                .getManufacturer()));
        equipment.setManufacturingYear(Optional.ofNullable(equipmentDTO.getManufacturingYear())
                .orElse(equipment.getManufacturingYear()));
        equipment.setMachineNumber(Optional.ofNullable(equipmentDTO.getMachineNumber()).orElse(equipment
                .getMachineNumber()));
        equipment.setDetails(Optional.ofNullable(equipmentDTO.getDetails()).orElse(equipment.getDetails()));
        if (Objects.nonNull(equipmentDTO.getAreaId())) {
            equipment.setAreaEntity(Optional.of(areaRepository.getReferenceById(equipmentDTO.getAreaId()))
                    .orElse(equipment.getAreaEntity()));
        }
        if (Objects.nonNull(equipmentDTO.getMachineTypeId())) {
            equipment.setEquipmentType(Optional.of(equipmentTypeRepository.getReferenceById(equipmentDTO
                            .getMachineTypeId())).orElse(equipment.getEquipmentTypeEntity()));
        }
        equipmentRepository.save(equipment);
    }

    private Equipment buildEquipmentEntityFromRequest(EquipmentRequest request, Area area, EquipmentType equipmentType) {
        Equipment builtEquipment = new Equipment();
        builtEquipment.setMachineModel(request.getMachineModel());
        builtEquipment.setManufacturerCountry(request.getManufacturerCountry());
        builtEquipment.setManufacturer(request.getManufacturer());
        builtEquipment.setManufacturingYear(request.getManufacturingYear());
        builtEquipment.setMachineNumber(request.getMachineNumber());
        builtEquipment.setDetails(request.getDetails());
        builtEquipment.setAreaEntity(area);
        builtEquipment.setEquipmentType(equipmentType);
        return builtEquipment;
    }

    public EquipmentDTO convertEquipmentEntityToDTO(Equipment equipment) {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setId(equipment.getId());
        equipmentDTO.setMachineModel(equipment.getMachineModel());
        equipmentDTO.setManufacturerCountry(equipment.getManufacturerCountry());
        equipmentDTO.setManufacturer(equipment.getManufacturer());
        equipmentDTO.setManufacturingYear(equipment.getManufacturingYear());
        equipmentDTO.setMachineNumber(equipment.getMachineNumber());
        equipmentDTO.setDetails(equipment.getDetails());
        equipmentDTO.setAreaId(equipment.getAreaEntity().getId());
        equipmentDTO.setMachineTypeId(equipment.getEquipmentTypeEntity().getId());
        equipmentDTO.setFiles(documentServiceImpl.getAllDocumentByEquipmentId(equipment.getId()));
        return equipmentDTO;
        }
}
