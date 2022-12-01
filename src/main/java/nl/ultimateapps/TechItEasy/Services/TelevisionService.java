package nl.ultimateapps.TechItEasy.Servi
import nl.ultimateapps.TechItEasy.Dtos.TelevisionDto;
import nl.ultimateapps.TechItEasy.Exceptions.RecordNotFoundException;
import nl.ultimateapps.TechItEasy.Helpers.TelevisionMapper;
import nl.ultimateapps.TechItEasy.Models.Television;
import nl.ultimateapps.TechItEasy.Repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class TelevisionService {

    @Autowired // KAN OOK MET CONSTRUCTOR INJECTIE
    private TelevisionRepository repos;

    public ArrayList<TelevisionDto> getTelevisions() {
        Iterable<Television> allTelevisions = repos.findAll();
        ArrayList<TelevisionDto> resultList = new ArrayList<>();
        for (Television television : allTelevisions) {
            TelevisionDto newTelevisionDto = TelevisionMapper.mapToDto(television);
            resultList.add(newTelevisionDto);
        }
        return resultList;
    }

    public TelevisionDto getTelevision(long id) {
        if (repos.findById(id).isPresent()) {
            Television television = repos.findById(id).get();
            TelevisionDto televisionDto = TelevisionMapper.mapToDto(television);
            return televisionDto;
        } else {
            throw new RecordNotFoundException();
        }
    }

    public long createTelevision(TelevisionDto televisionDto) {
        Television television =  TelevisionMapper.mapToModel(televisionDto);
        Television savedTelevision = repos.save(television);
        return savedTelevision.getId();
    }

    public long updateTelevision(long id, TelevisionDto televisionDto) {
        if (repos.findById(id).isPresent()) {
            Television television = repos.findById(id).get();

            television.setType(televisionDto.getType());
            television.setBrand(televisionDto.getBrand());
            television.setName(televisionDto.getName());
            television.setPrice(televisionDto.getPrice());
            television.setAvailableSize(televisionDto.getAvailableSize());
            television.setRefreshRate(televisionDto.getRefreshRate());
            television.setScreenType(televisionDto.getScreenType());
            television.setScreenQuality(televisionDto.getScreenQuality());
            television.setSmartTv(televisionDto.getSmartTv());
            television.setWifi(televisionDto.getWifi());
            television.setVoiceControl(televisionDto.getVoiceControl());
            television.setHdr(televisionDto.getHdr());
            television.setBluetooth(televisionDto.getBluetooth());
            television.setAmbiLight(televisionDto.getAmbiLight());
            television.setOriginalStock(televisionDto.getOriginalStock());
            television.setSold(televisionDto.getSold());

            repos.save(television);
            return television.getId();

        } else {
            throw new RecordNotFoundException();
        }
    }

    public long partialUpdateTelevision(long id, TelevisionDto televisionDto) {
        if (repos.findById(id).isPresent()) {
            Television television = repos.findById(id).get();

            if (television.getType() != null) {
                television.setType(televisionDto.getType());
            }

            repos.save(television);
            return television.getId();
        } else {
            throw new RecordNotFoundException();
        }
    }

    public long deleteTelevision(long id) {
        if (repos.findById(id).isPresent()) {
            Television television = repos.findById(id).get();
            long retrievedId = television.getId();
            repos.deleteById(id);
            return retrievedId;
        } else {
            throw new RecordNotFoundException();
        }
    }

    public Iterable<TelevisionDto> getTelevisionContaining(String query) {
        Iterable<Television> foundTelevisions = repos.findByBrandContaining(query);
        ArrayList<TelevisionDto> resultList = new ArrayList<>();
        for (Television t : foundTelevisions) {
            TelevisionDto newTelevisionDto = TelevisionMapper.mapToDto(t);
            resultList.add(newTelevisionDto);
        }
        return resultList;
    }
}
