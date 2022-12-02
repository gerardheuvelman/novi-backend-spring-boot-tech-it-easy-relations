package nl.ultimateapps.TechItEasy.Services;

import nl.ultimateapps.TechItEasy.Dtos.CiModuleDto;
import nl.ultimateapps.TechItEasy.Exceptions.RecordNotFoundException;
import nl.ultimateapps.TechItEasy.Helpers.CiModuleMapper;
import nl.ultimateapps.TechItEasy.Models.CiModule;
import nl.ultimateapps.TechItEasy.Repositories.CiModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class CiModuleService {


    private CiModuleRepository repos;

    public ArrayList<CiModuleDto> getCiModules() {
        Iterable<CiModule> allCiModules = repos.findAll();
        ArrayList<CiModuleDto> resultList = new ArrayList<>();
        for (CiModule ciModule : allCiModules) {
            CiModuleDto newCiModuleDto = CiModuleMapper.mapToDto(ciModule);
            resultList.add(newCiModuleDto);
        }
        return resultList;
    }

    public CiModuleDto getCiModule(long id) {
        if (repos.findById(id).isPresent()) {
            CiModule ciModule = repos.findById(id).get();
            CiModuleDto ciModuleDto = CiModuleMapper.mapToDto(ciModule);
            return ciModuleDto;
        } else {
            throw new RecordNotFoundException();
        }
    }

    public long createCiModule(CiModuleDto CiModuleDto) {
        CiModule ciModule =  CiModuleMapper.mapToModel(CiModuleDto);
        CiModule savedCiModule = repos.save(ciModule);
        return savedCiModule.getId();
    }

    public long updateCiModule(long id, CiModuleDto ciModuleDto) {
        if (repos.findById(id).isPresent()) {
            CiModule ciModule = repos.findById(id).get();

            ciModule.setName((ciModuleDto.getName()));
            ciModule.setType((ciModuleDto.getType()));
            ciModule.setPrice((ciModuleDto.getPrice()));

            repos.save(ciModule);
            return ciModule.getId();

        } else {
            throw new RecordNotFoundException();
        }
    }

    public long partialUpdateCiModule(long id, CiModuleDto ciModuleDto) {
        if (repos.findById(id).isPresent()) {
            CiModule ciModule = repos.findById(id).get();

            if (ciModule.getType() != null) {
                ciModule.setType(ciModuleDto.getType());
            }

            repos.save(ciModule);
            return ciModule.getId();
        } else {
            throw new RecordNotFoundException();
        }
    }

    public long deleteCiModule(long id) {
        if (repos.findById(id).isPresent()) {
            CiModule ciModule = repos.findById(id).get();
            long retrievedId = ciModule.getId();
            repos.deleteById(id);
            return retrievedId;
        } else {
            throw new RecordNotFoundException();
        }
    }
}
