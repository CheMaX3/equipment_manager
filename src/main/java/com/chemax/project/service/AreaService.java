package com.chemax.project.service;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.request.AreaCreateRequest;
import com.chemax.project.request.AreaUpdateRequest;

import java.util.List;

public interface AreaService {

    AreaDTO createArea(AreaCreateRequest areaCreateRequest);

    List<AreaDTO> getAllAreaDTOs();

    boolean equipmentInclusionCheck(Integer areaId);

    void deleteArea(Integer areaId);

    AreaDTO updateArea(AreaUpdateRequest areaUpdateRequest);

    List<AreaDTO> getAreaListBySectionId(Integer sectionId);

    AreaDTO getAreaDTOById(Integer areaId);

}
