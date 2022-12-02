package nl.ultimateapps.TechItEasy.Services;

import nl.ultimateapps.TechItEasy.Dtos.WallBracketDto;
import nl.ultimateapps.TechItEasy.Exceptions.RecordNotFoundException;
import nl.ultimateapps.TechItEasy.Helpers.WallBracketMapper;
import nl.ultimateapps.TechItEasy.Models.WallBracket;
import nl.ultimateapps.TechItEasy.Repositories.WallBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class WallBracketService {

    @Autowired // KAN OOK MET CONSTRUCTOR INJECTIE
    private WallBracketRepository repos;

    public ArrayList<WallBracketDto> getWallBrackets() {
        Iterable<WallBracket> allWallBrackets = repos.findAll();
        ArrayList<WallBracketDto> resultList = new ArrayList<>();
        for (WallBracket wallBracket : allWallBrackets) {
            WallBracketDto newWallBracketDto = WallBracketMapper.mapToDto(wallBracket);
            resultList.add(newWallBracketDto);
        }
        return resultList;
    }

    public WallBracketDto getWallBracket(long id) {
        if (repos.findById(id).isPresent()) {
            WallBracket wallBracket = repos.findById(id).get();
            WallBracketDto wallBracketDto = WallBracketMapper.mapToDto(wallBracket);
            return wallBracketDto;
        } else {
            throw new RecordNotFoundException();
        }
    }

    public long createWallBracket(WallBracketDto wallBracketDto) {
        WallBracket wallBracket =  WallBracketMapper.mapToModel(wallBracketDto);
        WallBracket savedWallBracket = repos.save(wallBracket);
        return savedWallBracket.getId();
    }

    public long updateWallBracket(long id, WallBracketDto wallBracketDto) {
        if (repos.findById(id).isPresent()) {
            WallBracket wallBracket = repos.findById(id).get();

            wallBracket.setSize(wallBracketDto.getSize());
            wallBracket.setAjustable(wallBracketDto.getAjustable());
            wallBracket.setName(wallBracketDto.getName());
            wallBracket.setPrice(wallBracketDto.getPrice());

            repos.save(wallBracket);
            return wallBracket.getId();

        } else {
            throw new RecordNotFoundException();
        }
    }

    public long partialUpdateWallBracket(long id, WallBracketDto wallBracketDto) {
        if (repos.findById(id).isPresent()) {
            WallBracket wallBracket = repos.findById(id).get();

            if (wallBracket.getName() != null) {
                wallBracket.setName(wallBracketDto.getName());
            }

            repos.save(wallBracket);
            return wallBracket.getId();
        } else {
            throw new RecordNotFoundException();
        }
    }

    public long deleteWallBracket(long id) {
        if (repos.findById(id).isPresent()) {
            WallBracket wallBracket = repos.findById(id).get();
            long retrievedId = wallBracket.getId();
            repos.deleteById(id);
            return retrievedId;
        } else {
            throw new RecordNotFoundException();
        }
    }
}
