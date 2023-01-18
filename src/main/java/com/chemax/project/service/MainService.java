package com.chemax.project.service;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.dto.SectionDTO;
import com.chemax.project.entities.AreaEntity;
import com.chemax.project.entities.EquipmentEntity;
import com.chemax.project.entities.EquipmentTypeEntity;
import com.chemax.project.exceptions.EntityNotFoundException;
import com.chemax.project.repository.AreaEntityRepository;
import com.chemax.project.repository.EquipmentEntityRepository;
import com.chemax.project.repository.EquipmentTypeEntityRepository;
import com.chemax.project.repository.SectionEntityRepository;
import com.chemax.project.entities.SectionEntity;
import com.chemax.project.request.AreaRequest;
import com.chemax.project.request.EquipmentRequest;
import com.chemax.project.request.EquipmentTypeRequest;
import com.chemax.project.request.SectionRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MainService {
    private final SectionEntityRepository sectionRepository;
    private final AreaEntityRepository areaRepository;
    private final EquipmentTypeEntityRepository equipmentTypeRepository;
    private final EquipmentEntityRepository equipmentRepository;

    public MainService(SectionEntityRepository sectionRepository, AreaEntityRepository areaRepository, EquipmentTypeEntityRepository equipmentTypeRepository, EquipmentEntityRepository equipmentRepository) {
        this.sectionRepository = sectionRepository;
        this.areaRepository = areaRepository;
        this.equipmentTypeRepository = equipmentTypeRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public SectionDTO createSectionEntity (SectionRequest request) {
        SectionEntity sectionEntity = buildSectionEntityFromRequest(request);
        sectionRepository.save(sectionEntity);
        return convertSectionEntityToDTO(sectionEntity);
    }

    private SectionEntity getSectionEntity (Integer id) throws EntityNotFoundException {
        Optional<SectionEntity> returnedEntity = sectionRepository.findById(id);
        return returnedEntity.orElseThrow(EntityNotFoundException::new);
    }

    public SectionDTO getSectionDTO (Integer id) {
        return convertSectionEntityToDTO(getSectionEntity(id));
    }

    public List<SectionDTO> getAllSectionDTOs() {
        List<SectionDTO> sectionDTOList = new ArrayList<>();
        for (SectionEntity s: sectionRepository.findAll()) {
            sectionDTOList.add(convertSectionEntityToDTO(s));
        }
        return sectionDTOList;
    }

    public List<SectionDTO> getSectionDTOsByCount (Integer count) {
        List<SectionDTO> sectionDTOList = new ArrayList<>();
        for (SectionEntity s: sectionRepository.findAll()) {
            sectionDTOList.add(convertSectionEntityToDTO(s));
        }
        return sectionDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public void deleteSectionEntity (Integer id) {
        sectionRepository.delete(getSectionEntity(id));
    }

    public void updateSectionEntity (SectionRequest request, Integer id) {
        Optional<SectionEntity> returnedEntity = sectionRepository.findById(id);
        SectionEntity entityToChange = returnedEntity.orElseThrow(EntityNotFoundException::new);
        if (request.getSectionFullName() != null) {
            entityToChange.setSectionFullName(request.getSectionFullName());
        }
        if (request.getSectionShortName() != null) {
            entityToChange.setSectionShortName(request.getSectionShortName());
        }
        entityToChange.setSectionConversationalName(Optional.ofNullable(request.getSectionConversationalName())
                .orElse(entityToChange.getSectionConversationalName()));
        sectionRepository.save(entityToChange);
    }

    public AreaDTO createAreaEntity(AreaRequest request) {
        SectionEntity sectionEntity = sectionRepository.getReferenceById(request.getSectionId());
        AreaEntity areaEntity = buildAreaEntityFromRequest(request, sectionEntity);
        areaRepository.save(areaEntity);
        return convertAreaEntityToDTO(areaEntity);
    }

    private AreaEntity getAreaEntity (Integer id) throws EntityNotFoundException {
        return areaRepository.getReferenceById(id);
    }

    public AreaDTO getAreaDTO (Integer id) {
        return convertAreaEntityToDTO(getAreaEntity(id));
    }

    public List<AreaDTO> getAllAreaDTOs() {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        for (AreaEntity a: areaRepository.findAll()) {
            areaDTOList.add(convertAreaEntityToDTO(a));
        }
        return areaDTOList;
    }

    public List<AreaDTO> getAreaDTOsByCount (Integer count) {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        for (AreaEntity a: areaRepository.findAll()) {
            areaDTOList.add(convertAreaEntityToDTO(a));
        }
        return areaDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public void deleteAreaEntity (Integer id) {
        areaRepository.delete(getAreaEntity(id));
    }

    public void updateAreaEntity (AreaRequest request, Integer id) {
        AreaEntity areaEntity = areaRepository.getReferenceById(id);
        areaEntity.setAreaFullName(Optional.ofNullable(request.getAreaFullName()).orElse(areaEntity.getAreaFullName()));
        areaEntity.setAreaShortName(Optional.ofNullable(request.getAreaShortName()).orElse(areaEntity.getAreaShortName()));
        areaEntity.setAreaConversationalName(Optional.ofNullable(request.getAreaConversationalName()).orElse(areaEntity.getAreaConversationalName()));
        areaEntity.setSectionEntity(Optional.ofNullable(sectionRepository.getReferenceById(request.getSectionId())).orElse(areaEntity.getSectionEntity()));
        areaRepository.save(areaEntity);
    }

    public EquipmentTypeDTO createEquipmentTypeEntity (EquipmentTypeRequest request) {
        EquipmentTypeEntity equipmentTypeEntity = buildEquipmentTypeEntityFromRequest(request);
        equipmentTypeRepository.save(equipmentTypeEntity);
        return convertEquipmentTypeEntityToDTO(equipmentTypeEntity);
    }

    private EquipmentTypeEntity getEquipmentTypeEntity (Integer id) throws EntityNotFoundException {
        return equipmentTypeRepository.getReferenceById(id);
    }

    public EquipmentTypeDTO getEquipmentTypeDTO (Integer id) {
        return convertEquipmentTypeEntityToDTO(getEquipmentTypeEntity(id));
    }

    private List<EquipmentTypeDTO> getAllEquipmentTypeDTOs() {
        List<EquipmentTypeDTO> equipmentTypeDTOList = new ArrayList<>();
        for (EquipmentTypeEntity et: equipmentTypeRepository.findAll()) {
            equipmentTypeDTOList.add(convertEquipmentTypeEntityToDTO(et));
        }
        return equipmentTypeDTOList;
    }

    public List<EquipmentTypeDTO> getEquipmentTypeDTOsByCount (Integer count) {
        List<EquipmentTypeDTO> equipmentTypeDTOList = new ArrayList<>();
        for (EquipmentTypeEntity et: equipmentTypeRepository.findAll()) {
            equipmentTypeDTOList.add(convertEquipmentTypeEntityToDTO(et));
        }
        return equipmentTypeDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public void deleteEquipmentTypeEntity (Integer id) {
        equipmentTypeRepository.delete(getEquipmentTypeEntity(id));
    }

    public EquipmentTypeDTO updateEquipmentTypeEntity (EquipmentTypeRequest request, Integer id) {
        EquipmentTypeEntity equipmentTypeEntity = equipmentTypeRepository.getReferenceById(id);
        equipmentTypeEntity.setMachineType(Optional.ofNullable(request.getMachineType()).orElse(equipmentTypeEntity.getMachineType()));
        equipmentTypeRepository.save(equipmentTypeEntity);
        return getEquipmentTypeDTO(id);
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

    private List<EquipmentDTO> getAllEquipmentDTOs() {
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

    public void deleteEquipmentEntity (Integer id) {
        equipmentRepository.delete(getEquipmentEntity(id));
    }

    public EquipmentDTO updateEquipmentEntity (EquipmentRequest request, Integer id) {
        EquipmentEntity equipmentEntity = equipmentRepository.getReferenceById(id);
        equipmentEntity.setMachineModel(Optional.ofNullable(request.getMachineModel()).orElse(equipmentEntity.getMachineModel()));
        equipmentEntity.setManufacturerCountry(Optional.ofNullable(request.getManufacturerCountry()).orElse(equipmentEntity.getManufacturerCountry()));
        equipmentEntity.setManufacturer(Optional.ofNullable(request.getManufacturer()).orElse(equipmentEntity.getManufacturer()));
        equipmentEntity.setManufacturingYear(Optional.ofNullable(request.getManufacturingYear()).orElse(equipmentEntity.getManufacturingYear()));
        equipmentEntity.setMachineNumber(Optional.ofNullable(request.getMachineNumber()).orElse(equipmentEntity.getMachineNumber()));
        equipmentEntity.setDetails(Optional.ofNullable(request.getDetails()).orElse(equipmentEntity.getDetails()));
        equipmentEntity.setAreaEntity(Optional.of(areaRepository.getReferenceById(request.getAreaId())).orElse(equipmentEntity.getAreaEntity()));
        equipmentEntity.setEquipmentType(Optional.of(equipmentTypeRepository.getReferenceById(request.getMachineTypeId())).
                orElse(equipmentEntity.getEquipmentTypeEntity()));
        equipmentRepository.save(equipmentEntity);
        return getEquipmentDTO(id);
    }

    private SectionEntity buildSectionEntityFromRequest (SectionRequest request) {
        SectionEntity builtSectionEntity = new SectionEntity();
        builtSectionEntity.setSectionFullName(request.getSectionFullName());
        builtSectionEntity.setSectionShortName(request.getSectionShortName());
        builtSectionEntity.setSectionConversationalName((request.getSectionConversationalName()));
        return builtSectionEntity;
    }

    private SectionDTO convertSectionEntityToDTO (SectionEntity sectionEntityToConvert) {
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(sectionEntityToConvert.getId());
        sectionDTO.setSectionFullName(sectionEntityToConvert.getSectionFullName());
        sectionDTO.setSectionShortName(sectionEntityToConvert.getSectionShortName());
        sectionDTO.setSectionConversationalName(sectionEntityToConvert.getSectionConversationalName());
        sectionDTO.setAreaDTOList(getAllAreaDTOs().stream().filter(areaDTO -> Objects.equals(areaDTO.getSectionID(), sectionEntityToConvert.getId()))
                .collect(Collectors.toList()));
        return sectionDTO;
    }

    private AreaEntity buildAreaEntityFromRequest(AreaRequest request, SectionEntity sectionEntity) {
        AreaEntity builtAreaEntity = new AreaEntity();
        builtAreaEntity.setAreaFullName(request.getAreaFullName());
        builtAreaEntity.setAreaShortName(request.getAreaShortName());
        builtAreaEntity.setAreaConversationalName(request.getAreaConversationalName());
        builtAreaEntity.setSectionEntity(sectionEntity);
        return builtAreaEntity;
    }

    private AreaDTO convertAreaEntityToDTO (AreaEntity areaEntity) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(areaEntity.getId());
        areaDTO.setAreaFullName(areaEntity.getAreaFullName());
        areaDTO.setAreaShortName(areaEntity.getAreaShortName());
        areaDTO.setAreaConversationalName(areaEntity.getAreaConversationalName());
        areaDTO.setSectionID(areaEntity.getSectionEntity().getId());
        return areaDTO;
    }

    private EquipmentTypeEntity buildEquipmentTypeEntityFromRequest(EquipmentTypeRequest request) {
        EquipmentTypeEntity builtEquipmentTypeEntity = new EquipmentTypeEntity();
        builtEquipmentTypeEntity.setMachineType(request.getMachineType());
        return builtEquipmentTypeEntity;
    }

    private EquipmentTypeDTO convertEquipmentTypeEntityToDTO (EquipmentTypeEntity equipmentTypeEntityToConvert) {
        EquipmentTypeDTO equipmentTypeDTO = new EquipmentTypeDTO();
        equipmentTypeDTO.setId(equipmentTypeEntityToConvert.getId());
        equipmentTypeDTO.setMachineType(equipmentTypeEntityToConvert.getMachineType());
        return equipmentTypeDTO;
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

    private EquipmentDTO convertEquipmentEntityToDTO(EquipmentEntity equipmentEntity) {
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
        return equipmentDTO;
        }


}
