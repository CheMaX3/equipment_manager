package com.chemax.project.service;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.entity.Area;
import com.chemax.project.entity.Section;
import com.chemax.project.exception.EntityNotFoundException;
import com.chemax.project.repository.AreaRepository;
import com.chemax.project.request.AreaCreateRequest;
import com.chemax.project.request.AreaUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final EquipmentServiceImpl equipmentService;

    public AreaServiceImpl(AreaRepository areaRepository, EquipmentServiceImpl equipmentService) {
        this.areaRepository = areaRepository;
        this.equipmentService = equipmentService;
    }

    public AreaDTO createArea(AreaCreateRequest areaCreateRequest) {
        AreaDTO areaDTO = new AreaDTO();
        try {
            Section section = areaCreateRequest.getSection();
            Area area = areaRepository.save(buildAreaFromRequest(areaCreateRequest, section));
            areaDTO = convertAreaToDTO(area);
        } catch (Exception ex) {
            log.error("Can't save object " + areaCreateRequest.toString());
        }
        return areaDTO;
    }

    public List<AreaDTO> getAllAreaDTOs() {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        try {
            areaDTOList = areaRepository.findAll().stream()
                    .map(this::convertAreaToDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
        }
        return areaDTOList;
    }

    public boolean equipmentInclusionCheck(Integer areaId) {
        boolean areaIncludesEquipment = false;
        try {
            areaIncludesEquipment = equipmentService.getEquipmentListByAreaId(areaId).isEmpty();
        } catch (Exception ex) {
            log.error("Can't check equipment objects inclusion in area with id=" + areaId);
        }
        return areaIncludesEquipment;
    }

    public void deleteArea(Integer areaId) {
        try {
            areaRepository.delete(areaRepository.getReferenceById(areaId));
        } catch (Exception ex) {
            log.error("Can't delete object with id=" + areaId);
        }
    }

    public AreaDTO updateArea(AreaUpdateRequest areaUpdateRequest) {
        AreaDTO areaDTO = new AreaDTO();
        try {
            Area areaFromDB = areaRepository.findById(areaUpdateRequest.getAreaId())
                    .orElseThrow(EntityNotFoundException::new);
            areaFromDB.setAreaFullName(Optional.ofNullable(areaUpdateRequest.getAreaFullName())
                    .orElse(areaFromDB.getAreaFullName()));
            areaFromDB.setAreaShortName(Optional.ofNullable(areaUpdateRequest.getAreaShortName())
                    .orElse(areaFromDB.getAreaShortName()));
            areaFromDB.setAreaConversationalName(Optional.ofNullable(areaUpdateRequest
                    .getAreaConversationalName()).orElse(areaFromDB.getAreaConversationalName()));
            areaRepository.save(areaFromDB);
            areaDTO = convertAreaToDTO(areaFromDB);
        } catch (Exception ex) {
            log.error("Can't save object with id=" + areaUpdateRequest.getAreaId());
        }
        return areaDTO;
    }

    public List<AreaDTO> getAreaListBySectionId(Integer sectionId) {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        try {
            areaDTOList = areaRepository.findBySectionId(sectionId).stream()
                    .map(this::convertAreaToDTO).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Can't retrieve objects from DB with sectionId=" + sectionId);
        }
        return areaDTOList;
    }

    public AreaDTO getAreaDTOById(Integer areaId) {
        AreaDTO areaDTO = new AreaDTO();
        try {
            areaDTO = convertAreaToDTO(areaRepository.getReferenceById(areaId));
        }
        catch (Exception ex) {
            log.error("Can't retrieve object from DB with id=" + areaId);
        }
        return areaDTO;
    }

    private Area buildAreaFromRequest(AreaCreateRequest areaCreateRequest, Section section) {
        Area builtArea = new Area();
        builtArea.setAreaFullName(areaCreateRequest.getAreaFullName());
        builtArea.setAreaShortName(areaCreateRequest.getAreaShortName());
        builtArea.setAreaConversationalName(areaCreateRequest.getAreaConversationalName());
        builtArea.setSection(section);
        return builtArea;
    }

        AreaDTO convertAreaToDTO(Area area) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(area.getId());
        areaDTO.setAreaFullName(area.getAreaFullName());
        areaDTO.setAreaShortName(area.getAreaShortName());
        areaDTO.setAreaConversationalName(area.getAreaConversationalName());
        areaDTO.setSection(area.getSection());
        areaDTO.setEquipmentDTOList(equipmentService.getEquipmentListByAreaId(area.getId()));
        return areaDTO;
    }
}
