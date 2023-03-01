package com.chemax.project.service;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.request.AreaRequest;

import java.util.List;

public interface AreaService {

    AreaDTO createAreaEntity(AreaRequest request);

    AreaDTO getAreaDTO (Integer id);

    List<AreaDTO> getAllAreaDTOs();

    List<AreaDTO> getAllAreaSelectedSectionDTOs(Integer id);

    List<AreaDTO> getAreaDTOsByCount(Integer count);

    boolean deleteAreaEntity (Integer id);

    void updateAreaEntity (AreaDTO areaDTO, Integer id);

}
