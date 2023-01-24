package com.chemax.project.service;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.entities.AreaEntity;
import com.chemax.project.entities.SectionEntity;
import com.chemax.project.exceptions.EntityNotFoundException;
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
        if (request.getSectionId() != null) {
            areaEntity.setSectionEntity(sectionRepository.getReferenceById(request.getSectionId()));
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
        areaDTO.setSectionID(areaEntity.getSectionEntity().getId());
        areaDTO.setEquipmentDTOList(equipmentService.getAllEquipmentDTOs().stream().filter(equipmentDTO -> Objects.equals(equipmentDTO.getAreaId(), areaEntity.getId()))
                .collect(Collectors.toList()));
        return areaDTO;
    }

}
