package nl.ultimateapps.TechItEasy.Controllers;


import nl.ultimateapps.TechItEasy.Dtos.CiModuleDto;
import nl.ultimateapps.TechItEasy.Exceptions.RecordNotFoundException;
import nl.ultimateapps.TechItEasy.Services.CiModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/televisions")
public class CiModuleController {

    @Autowired
    private CiModuleService service;

    @GetMapping("")
    public ResponseEntity<ArrayList<CiModuleDto>> getCiModules() {
        ArrayList<CiModuleDto> ciModuleDtos = service.getCiModules();
        if (ciModuleDtos.size()>0) {
            return ResponseEntity.ok(ciModuleDtos);
        } else {
            throw new RecordNotFoundException("No CI Modules found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiModuleDto> getCiModule (@PathVariable int id) {
        CiModuleDto ciModuleDto = service.getCiModule(id);
        return ResponseEntity.ok(ciModuleDto);
    }

    @PostMapping("")
    public ResponseEntity<String> postCiModule(@RequestBody CiModuleDto ciModuleDto) {
        long savedCiModule = service.createCiModule(ciModuleDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/cimodules/" + savedCiModule).toUriString());
        return ResponseEntity.created(uri).body("CI module created!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putCiModule(@PathVariable long id, @RequestBody CiModuleDto ciModuleDto) {
        long updatedCiModuleId = service.updateCiModule(id, ciModuleDto);
        return ResponseEntity.ok("CI module " + updatedCiModuleId + " was updated successfully");
    }

    //code voor een patch mapping (Om het kort te houden werkt het alleen met het veld Type ):
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchCiModule(@PathVariable long id, @RequestBody CiModuleDto ciModuleDto) {
        long partiallyUpdatedCiModuleId = service.partialUpdateCiModule(id, ciModuleDto);
        return ResponseEntity.ok("CI module " + partiallyUpdatedCiModuleId + " was partially updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCiModule(@PathVariable long id) {
        long deletedCiModule = service.deleteCiModule(id);
        return ResponseEntity.ok("CI module "+ deletedCiModule + " was deleted successfully.");
    }
}
