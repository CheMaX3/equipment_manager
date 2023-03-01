package com.chemax.project.service;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.entity.Area;
import com.chemax.project.entity.Section;
import com.chemax.project.repository.AreaRepository;
import com.chemax.project.repository.SectionRepository;
import com.chemax.project.request.AreaRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final SectionRepository sectionRepository;
    private final EquipmentServiceImpl equipmentServiceImpl;


    public AreaServiceImpl(AreaRepository areaRepository, SectionRepository sectionRepository, EquipmentServiceImpl equipmentServiceImpl) {
        this.areaRepository = areaRepository;
        this.sectionRepository = sectionRepository;
        this.equipmentServiceImpl = equipmentServiceImpl;
    }

    public AreaDTO createAreaEntity(AreaRequest request) {
        Section section = sectionRepository.getReferenceById(request.getSectionId());
        Area area = buildAreaEntityFromRequest(request, section);
        areaRepository.save(area);
        return convertAreaEntityToDTO(area);
    }

    private Area getAreaEntity (Integer id) {
        return areaRepository.getReferenceById(id);
    }

    public AreaDTO getAreaDTO (Integer id) {
        return convertAreaEntityToDTO(getAreaEntity(id));
    }

    public List<AreaDTO> getAllAreaDTOs() {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        for (Area a: areaRepository.findAll()) {
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
        for (Area a: areaRepository.findAll()) {
            areaDTOList.add(convertAreaEntityToDTO(a));
        }
        return areaDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public boolean deleteAreaEntity (Integer id) {
        Area areaToDelete = areaRepository.getReferenceById(id);
        if (areaToDelete.getEquipmentList().isEmpty()) {
            areaRepository.delete(getAreaEntity(id));
            return true;
        } else {
            return false;
        }
    }

    public void updateAreaEntity (AreaDTO areaDTO, Integer id) {
        Area area = areaRepository.getReferenceById(id);
        area.setAreaFullName(Optional.ofNullable(areaDTO.getAreaFullName()).orElse(area.getAreaFullName()));
        area.setAreaShortName(Optional.ofNullable(areaDTO.getAreaShortName()).orElse(area.getAreaShortName()));
        area.setAreaConversationalName(Optional.ofNullable(areaDTO.getAreaConversationalName()).orElse(area.getAreaConversationalName()));
        if (areaDTO.getSectionId() != null) {
            area.setSectionEntity(sectionRepository.getReferenceById(areaDTO.getSectionId()));
        } else {
            area.setSectionEntity(area.getSectionEntity());
        }
        areaRepository.save(area);
    }

    private Area buildAreaEntityFromRequest(AreaRequest request, Section section) {
        Area builtArea = new Area();
        builtArea.setAreaFullName(request.getAreaFullName());
        builtArea.setAreaShortName(request.getAreaShortName());
        builtArea.setAreaConversationalName(request.getAreaConversationalName());
        builtArea.setSectionEntity(section);
        return builtArea;
    }

    private AreaDTO convertAreaEntityToDTO (Area area) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(area.getId());
        areaDTO.setAreaFullName(area.getAreaFullName());
        areaDTO.setAreaShortName(area.getAreaShortName());
        areaDTO.setAreaConversationalName(area.getAreaConversationalName());
        areaDTO.setSectionId(area.getSectionEntity().getId());
        areaDTO.setEquipmentDTOList(equipmentServiceImpl.getAllEquipmentDTOs().stream().filter(equipmentDTO -> Objects.equals(equipmentDTO.getAreaId(), area.getId()))
                .collect(Collectors.toList()));
        areaDTO.setSectionFullName(sectionRepository.getReferenceById(areaDTO.getSectionId()).getSectionFullName());
        return areaDTO;
    }

}
