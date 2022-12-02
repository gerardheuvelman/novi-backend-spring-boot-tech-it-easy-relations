package nl.ultimateapps.TechItEasy.Helpers;

import nl.ultimateapps.TechItEasy.Dtos.CiModuleDto;
import nl.ultimateapps.TechItEasy.Models.CiModule;

public class CiModuleMapper {
    public static CiModule mapToModel(CiModuleDto CiModuleDto) {
        CiModule ciModule = new CiModule();

        ciModule.setName(CiModuleDto.getName());
        ciModule.setType(CiModuleDto.getType());
        ciModule.setPrice(CiModuleDto.getPrice());

        return ciModule;
    }

    public static CiModuleDto mapToDto(CiModule ciModule) {
        CiModuleDto dto = new CiModuleDto();

        dto.setName(ciModule.getName());
        dto.setType(ciModule.getType());
        dto.setPrice(ciModule.getPrice());

        return dto;

    }
}