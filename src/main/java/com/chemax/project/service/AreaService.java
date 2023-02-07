package com.chemax.project.service;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.entities.AreaEntity;
import com.chemax.project.entities.SectionEntity;
import com.chemax.project.exceptions.EntityNotFoundException;
import com.chemax.project.exceptions.NeedToMoveEntityException;
import com.chemax.project.repository.AreaEntityRepository;
import com.chemax.project.repository.SectionEntityRepository;
import com.chemax.project.request.AreaRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AreaService {

    private final AreaEntityRepository areaRepository;
    private final SectionEntityRepository sectionRepository;
    private final EquipmentService equipmentService;


    public AreaService(AreaEntityRepository areaRepository, SectionEntityRepository sectionRepository, EquipmentService equipmentService) {
        this.areaRepository = areaRepository;
        this.sectionRepository = sectionRepository;
        this.equipmentService = equipmentService;
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

    public List<AreaDTO> getAllAreaSelectedSectionDTOs(Integer id) {
        List<AreaDTO> areaDTOList = getAllAreaDTOs();
        List<AreaDTO> areaSelectedSectionDTOList = new ArrayList<>();
        for (AreaDTO a: areaDTOList) {
            if (Objects.equals(a.getSectionId(), id)) {
                areaSelectedSectionDTOList.add(a);
            }
        }
        return areaSelectedSectionDTOList;
    }

    public List<AreaDTO> getAreaDTOsByCount (Integer count) {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        for (AreaEntity a: areaRepository.findAll()) {
            areaDTOList.add(convertAreaEntityToDTO(a));
        }
        return areaDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public void deleteAreaEntity (Integer id) {
        AreaEntity areaEntityToDelete = areaRepository.getReferenceById(id);
        if (areaEntityToDelete.getEquipmentList().isEmpty()) {
            areaRepository.delete(getAreaEntity(id));
        } else {
            throw new NeedToMoveEntityException();
        }
    }

    public void updateAreaEntity (AreaDTO areaDTO, Integer id) {
        AreaEntity areaEntity = areaRepository.getReferenceById(id);
        areaEntity.setAreaFullName(Optional.ofNullable(areaDTO.getAreaFullName()).orElse(areaEntity.getAreaFullName()));
        areaEntity.setAreaShortName(Optional.ofNullable(areaDTO.getAreaShortName()).orElse(areaEntity.getAreaShortName()));
        areaEntity.setAreaConversationalName(Optional.ofNullable(areaDTO.getAreaConversationalName()).orElse(areaEntity.getAreaConversationalName()));
        if (areaDTO.getSectionId() != null) {
            areaEntity.setSectionEntity(sectionRepository.getReferenceById(areaDTO.getSectionId()));
        } else {
            areaEntity.setSectionEntity(areaEntity.getSectionEntity());
        }
        areaRepository.save(areaEntity);
    }

    private AreaEntity buildAreaEntityFromRequest(AreaRequest request, SectionEntity sectionEntity) {
        AreaEntity builtAreaEntity = new AreaEntity();
        builtAreaEntity.setAreaFullName(request.getAreaFullName());
        builtAreaEntity.setAreaShortName(request.getAreaShortName());
        builtAreaEntity.setAreaConversationalName(request.getAreaConversationalName());
        builtAreaEntity.setSectionEntity(sectionEntity);
        return builtAreaEntity;
    }

    public AreaDTO convertAreaEntityToDTO (AreaEntity areaEntity) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(areaEntity.getId());
        areaDTO.setAreaFullName(areaEntity.getAreaFullName());
        areaDTO.setAreaShortName(areaEntity.getAreaShortName());
        areaDTO.setAreaConversationalName(areaEntity.getAreaConversationalName());
        areaDTO.setSectionId(areaEntity.getSectionEntity().getId());
        areaDTO.setEquipmentDTOList(equipmentService.getAllEquipmentDTOs().stream().filter(equipmentDTO -> Objects.equals(equipmentDTO.getAreaId(), areaEntity.getId()))
                .collect(Collectors.toList()));
        areaDTO.setSectionFullName(sectionRepository.getReferenceById(areaDTO.getSectionId()).getSectionFullName());
        return areaDTO;
    }

}
