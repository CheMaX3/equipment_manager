package com.chemax.project.service;

import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.entities.AreaEntity;
import com.chemax.project.entities.EquipmentEntity;
import com.chemax.project.entities.EquipmentTypeEntity;
import com.chemax.project.exceptions.EntityNotFoundException;
import com.chemax.project.repository.AreaEntityRepository;
import com.chemax.project.repository.EquipmentEntityRepository;
import com.chemax.project.repository.EquipmentTypeEntityRepository;
import com.chemax.project.request.EquipmentRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipmentService {

    private final AreaEntityRepository areaRepository;
    private final EquipmentTypeEntityRepository equipmentTypeRepository;
    private final EquipmentEntityRepository equipmentRepository;
    private final DocumentService documentService;

    public EquipmentService(AreaEntityRepository areaRepository, EquipmentTypeEntityRepository equipmentTypeRepository,
                            EquipmentEntityRepository equipmentRepository, DocumentService documentService) {
        this.areaRepository = areaRepository;
        this.equipmentTypeRepository = equipmentTypeRepository;
        this.equipmentRepository = equipmentRepository;
        this.documentService = documentService;
    }

    public EquipmentDTO createEquipmentEntity(EquipmentRequest request) {
        AreaEntity areaEntity = areaRepository.getReferenceById(request.getAreaId());
        EquipmentTypeEntity equipmentTypeEntity = equipmentTypeRepository.getReferenceById(request.getMachineTypeId());
        EquipmentEntity equipmentEntity = buildEquipmentEntityFromRequest(request, areaEntity, equipmentTypeEntity);
        equipmentRepository.save(equipmentEntity);
        return convertEquipmentEntityToDTO(equipmentEntity);
    }

    private EquipmentEntity getEquipmentEntity (Integer id) throws EntityNotFoundException {
        return equipmentRepository.getReferenceById(id);
    }

    public EquipmentDTO getEquipmentDTO (Integer id) {
        return convertEquipmentEntityToDTO(getEquipmentEntity(id));
    }

    public List<EquipmentDTO> getAllEquipmentDTOs() {
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        for (EquipmentEntity e: equipmentRepository.findAll()) {
            equipmentDTOList.add(convertEquipmentEntityToDTO(e));
        }
        return equipmentDTOList;
    }

    public List<EquipmentDTO> getEquipmentDTOsByCount (Integer count) {
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        for (EquipmentEntity e: equipmentRepository.findAll()) {
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
        EquipmentEntity equipmentEntity = equipmentRepository.getReferenceById(id);
        equipmentEntity.setMachineModel(Optional.ofNullable(equipmentDTO.getMachineModel()).orElse(equipmentEntity
                .getMachineModel()));
        equipmentEntity.setManufacturerCountry(Optional.ofNullable(equipmentDTO.getManufacturerCountry())
                .orElse(equipmentEntity.getManufacturerCountry()));
        equipmentEntity.setManufacturer(Optional.ofNullable(equipmentDTO.getManufacturer()).orElse(equipmentEntity
                .getManufacturer()));
        equipmentEntity.setManufacturingYear(Optional.ofNullable(equipmentDTO.getManufacturingYear())
                .orElse(equipmentEntity.getManufacturingYear()));
        equipmentEntity.setMachineNumber(Optional.ofNullable(equipmentDTO.getMachineNumber()).orElse(equipmentEntity
                .getMachineNumber()));
        equipmentEntity.setDetails(Optional.ofNullable(equipmentDTO.getDetails()).orElse(equipmentEntity.getDetails()));
        if (Objects.nonNull(equipmentDTO.getAreaId())) {
            equipmentEntity.setAreaEntity(Optional.of(areaRepository.getReferenceById(equipmentDTO.getAreaId()))
                    .orElse(equipmentEntity.getAreaEntity()));
        }
        if (Objects.nonNull(equipmentDTO.getMachineTypeId())) {
            equipmentEntity.setEquipmentType(Optional.of(equipmentTypeRepository.getReferenceById(equipmentDTO
                            .getMachineTypeId())).orElse(equipmentEntity.getEquipmentTypeEntity()));
        }
        equipmentRepository.save(equipmentEntity);
    }

    private EquipmentEntity buildEquipmentEntityFromRequest(EquipmentRequest request, AreaEntity areaEntity, EquipmentTypeEntity equipmentTypeEntity) {
        EquipmentEntity builtEquipmentEntity = new EquipmentEntity();
        builtEquipmentEntity.setMachineModel(request.getMachineModel());
        builtEquipmentEntity.setManufacturerCountry(request.getManufacturerCountry());
        builtEquipmentEntity.setManufacturer(request.getManufacturer());
        builtEquipmentEntity.setManufacturingYear(request.getManufacturingYear());
        builtEquipmentEntity.setMachineNumber(request.getMachineNumber());
        builtEquipmentEntity.setDetails(request.getDetails());
        builtEquipmentEntity.setAreaEntity(areaEntity);
        builtEquipmentEntity.setEquipmentType(equipmentTypeEntity);
        return builtEquipmentEntity;
    }

    public EquipmentDTO convertEquipmentEntityToDTO(EquipmentEntity equipmentEntity) {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setId(equipmentEntity.getId());
        equipmentDTO.setMachineModel(equipmentEntity.getMachineModel());
        equipmentDTO.setManufacturerCountry(equipmentEntity.getManufacturerCountry());
        equipmentDTO.setManufacturer(equipmentEntity.getManufacturer());
        equipmentDTO.setManufacturingYear(equipmentEntity.getManufacturingYear());
        equipmentDTO.setMachineNumber(equipmentEntity.getMachineNumber());
        equipmentDTO.setDetails(equipmentEntity.getDetails());
        equipmentDTO.setAreaId(equipmentEntity.getAreaEntity().getId());
        equipmentDTO.setMachineTypeId(equipmentEntity.getEquipmentTypeEntity().getId());
        equipmentDTO.setFiles(documentService.getAllDocumentByEquipmentId(equipmentEntity.getId()));
        return equipmentDTO;
        }
}
