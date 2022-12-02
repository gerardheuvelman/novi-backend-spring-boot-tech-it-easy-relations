package nl.ultimateapps.TechItEasy.Helpers;

import nl.ultimateapps.TechItEasy.Dtos.WallBracketDto;
import nl.ultimateapps.TechItEasy.Models.WallBracket;


public class WallBracketMapper {
    public static WallBracket mapToModel(WallBracketDto wallBracketDto) {
        WallBracket wallBracket = new WallBracket();

        wallBracket.setSize(wallBracketDto.getSize());
        wallBracket.setAjustable(wallBracketDto.getAjustable());
        wallBracket.setName(wallBracketDto.getName());
        wallBracket.setPrice(wallBracketDto.getPrice());

        return wallBracket;
    }

    public static WallBracketDto mapToDto(WallBracket wallBracket) {
        WallBracketDto dto = new WallBracketDto();

        dto.setSize(wallBracket.getSize());
        dto.setAjustable(wallBracket.getAjustable());
        dto.setName(wallBracket.getName());
        dto.setPrice(wallBracket.getPrice());

        return dto;
    }
}