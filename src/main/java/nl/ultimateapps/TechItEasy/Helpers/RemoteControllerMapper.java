package nl.ultimateapps.TechItEasy.Helpers;

import nl.ultimateapps.TechItEasy.Dtos.RemoteControllerDto;
import nl.ultimateapps.TechItEasy.Dtos.TelevisionDto;
import nl.ultimateapps.TechItEasy.Models.RemoteController;
import nl.ultimateapps.TechItEasy.Models.Television;

public class RemoteControllerMapper {

    public static RemoteController mapToModel(RemoteControllerDto remoteControllerDto) {
        RemoteController remoteController = new RemoteController();

        remoteController.setCompatibleWith(remoteControllerDto.getCompatibleWith());
        remoteController.setBatteryType(remoteControllerDto.getBatteryType());
        remoteController.setName(remoteControllerDto.getName());
        remoteController.setBrand(remoteControllerDto.getBrand());
        remoteController.setPrice(remoteControllerDto.getPrice());
        remoteController.setOriginalStock(remoteControllerDto.getOriginalStock());

        return remoteController;
    }

    public static RemoteControllerDto mapToDto(RemoteController remoteController) {
        RemoteControllerDto dto = new RemoteControllerDto();

        dto.setCompatibleWith(remoteController.getCompatibleWith());
        dto.setBatteryType(remoteController.getBatteryType());
        dto.setName(remoteController.getName());
        dto.setBrand(remoteController.getBrand());
        dto.setPrice(remoteController.getPrice());
        dto.setOriginalStock(remoteController.getOriginalStock());

        return dto;
    }
}
