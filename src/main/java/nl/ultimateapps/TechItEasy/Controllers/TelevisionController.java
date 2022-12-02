package nl.ultimateapps.TechItEasy.Controllers;

import nl.ultimateapps.TechItEasy.Dtos.TelevisionDto;
import nl.ultimateapps.TechItEasy.Exceptions.RecordNotFoundException;
import nl.ultimateapps.TechItEasy.Services.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    @Autowired
    private TelevisionService service;

    @GetMapping("")
    public ResponseEntity<ArrayList<TelevisionDto>> getTelevisions() {
        ArrayList<TelevisionDto> televisionDtos = service.getTelevisions();
        if (televisionDtos.size()>0) {
            return ResponseEntity.ok(televisionDtos);
        } else {
            throw new RecordNotFoundException("No televisions found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevision (@PathVariable int id) {
        TelevisionDto televisionDto = service.getTelevision(id);
        return ResponseEntity.ok(televisionDto);
    }

    @PostMapping("")
    public ResponseEntity<String> postTelevision(@RequestBody TelevisionDto televisionDto) {
        long savedTelevision = service.createTelevision(televisionDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/televisions/" + savedTelevision).toUriString());
        return ResponseEntity.created(uri).body("Television created!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putTelevision(@PathVariable long id, @RequestBody TelevisionDto televisionDto) {
        long updatedTelevisionId = service.updateTelevision(id, televisionDto);
        return ResponseEntity.ok("Television " + updatedTelevisionId + " was updated successfully");
    }

    //code voor een patch mapping (Om het kort te houden werkt het alleen met het veld Type ):
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchTelevision(@PathVariable long id, @RequestBody TelevisionDto televisionDto) {
        long partiallyUpdatedTelevisionId = service.partialUpdateTelevision(id, televisionDto);
        return ResponseEntity.ok("Television " + partiallyUpdatedTelevisionId + " was partially updated successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable long id) {
        long deletedTelevision = service.deleteTelevision(id);
        return ResponseEntity.ok("Television "+ deletedTelevision + " was deleted successfully.");
    }

    @GetMapping("/findbrand")
    public ResponseEntity<Iterable<TelevisionDto>> getTelevisionContaining(@RequestParam String query) {
        return ResponseEntity.ok(service.getTelevisionContaining(query));
    }
}