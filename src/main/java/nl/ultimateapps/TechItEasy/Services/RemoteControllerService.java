package nl.ultimateapps.TechItEasy.Services;

import nl.ultimateapps.TechItEasy.Dtos.RemoteControllerDto;
import nl.ultimateapps.TechItEasy.Dtos.TelevisionDto;
import nl.ultimateapps.TechItEasy.Exceptions.RecordNotFoundException;
import nl.ultimateapps.TechItEasy.Helpers.RemoteControllerMapper;
import nl.ultimateapps.TechItEasy.Helpers.TelevisionMapper;
import nl.ultimateapps.TechItEasy.Models.RemoteController;
import nl.ultimateapps.TechItEasy.Models.Television;
import nl.ultimateapps.TechItEasy.Repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RemoteControllerService {

    private RemoteControllerRepository repos;

    public ArrayList<RemoteControllerDto> getRemoteControllers() {
        Iterable<RemoteController> allRemoteControllers = repos.findAll();
        ArrayList<RemoteControllerDto> resultList = new ArrayList<>();
        for (RemoteController remoteController : allRemoteControllers) {
            RemoteControllerDto newRemoteControllerDto = RemoteControllerMapper.mapToDto(remoteController);
            resultList.add(newRemoteControllerDto);
        }
        return resultList;
    }

    public RemoteControllerDto getRemoteController(long id) {
        if (repos.findById(id).isPresent()) {
            RemoteController remoteController = repos.findById(id).get();
            RemoteControllerDto remoteControllerDto = RemoteControllerMapper.mapToDto(remoteController);
            return remoteControllerDto;
        } else {
            throw new RecordNotFoundException();
        }
    }

    public long createRemoteController(RemoteControllerDto remoteControllerDto) {
        RemoteController remoteController =  RemoteControllerMapper.mapToModel(remoteControllerDto);
        RemoteController savedRemoteController = repos.save(remoteController);
        return savedRemoteController.getId();
    }

    public long updateRemoteController(long id, RemoteControllerDto remoteControllerDto) {
        if (repos.findById(id).isPresent()) {
            RemoteController remoteController = repos.findById(id).get();

            remoteController.setCompatibleWith(remoteControllerDto.getCompatibleWith());
            remoteController.setBatteryType(remoteControllerDto.getBatteryType());
            remoteController.setName(remoteControllerDto.getName());
            remoteController.setBrand(remoteControllerDto.getBrand());
            remoteController.setPrice(remoteControllerDto.getPrice());
            remoteController.setOriginalStock(remoteControllerDto.getOriginalStock());

            repos.save(remoteController);
            return remoteController.getId();

        } else {
            throw new RecordNotFoundException();
        }
    }

    public long partialUpdateRemoteController(long id, RemoteControllerDto remoteControllerDto) {
        if (repos.findById(id).isPresent()) {
            RemoteController remoteController = repos.findById(id).get();

            if (remoteController.getName() != null) {
                remoteController.setName(remoteControllerDto.getName());
            }

            repos.save(remoteController);
            return remoteController.getId();
        } else {
            throw new RecordNotFoundException();
        }
    }

    public long deleteRemoteController(long id) {
        if (repos.findById(id).isPresent()) {
            RemoteController remoteController = repos.findById(id).get();
            long retrievedId = remoteController.getId();
            repos.deleteById(id);
            return retrievedId;
        } else {
            throw new RecordNotFoundException();
        }
    }
}
