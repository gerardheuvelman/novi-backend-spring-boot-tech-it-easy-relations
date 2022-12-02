package nl.ultimateapps.TechItEasy.Controllers;


import nl.ultimateapps.TechItEasy.Dtos.TelevisionDto;
import nl.ultimateapps.TechItEasy.Dtos.WallBracketDto;
import nl.ultimateapps.TechItEasy.Exceptions.RecordNotFoundException;
import nl.ultimateapps.TechItEasy.Services.WallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/wallbrackets")
public class WallBracketController {

    @Autowired
    private WallBracketService service;

    @GetMapping("")
    public ResponseEntity<ArrayList<WallBracketDto>> getWallBrackets() {
        ArrayList<WallBracketDto> wallBracketDtos = service.getWallBrackets();
        if (wallBracketDtos.size()>0) {
            return ResponseEntity.ok(wallBracketDtos);
        } else {
            throw new RecordNotFoundException("No wall brackets found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket (@PathVariable int id) {
        WallBracketDto wallBracketDto = service.getWallBracket(id);
        return ResponseEntity.ok(wallBracketDto);
    }

    @PostMapping("")
    public ResponseEntity<String> postWallBracket(@RequestBody WallBracketDto wallBracketDto) {
        long savedWallBracket = service.createWallBracket(wallBracketDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/wallbrackets/" + savedWallBracket).toUriString());
        return ResponseEntity.created(uri).body("Wall bracket created!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putWallBracket(@PathVariable long id, @RequestBody WallBracketDto wallBracketDto) {
        long updatedWallBracketId = service.updateWallBracket(id, wallBracketDto);
        return ResponseEntity.ok("Wall bracket " + updatedWallBracketId + " was updated successfully");
    }

    //code voor een patch mapping (Om het kort te houden werkt het alleen met het veld Name ):
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchWallBracket(@PathVariable long id, @RequestBody WallBracketDto wallBracketDto) {
        long partiallyUpdatedWallBracketId = service.partialUpdateWallBracket(id, wallBracketDto);
        return ResponseEntity.ok("Wall bracket " + partiallyUpdatedWallBracketId + " was partially updated successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallBracket(@PathVariable long id) {
        long deletedWallBracket = service.deleteWallBracket(id);
        return ResponseEntity.ok("Wall bracket "+ deletedWallBracket + " was deleted successfully.");
    }
}
