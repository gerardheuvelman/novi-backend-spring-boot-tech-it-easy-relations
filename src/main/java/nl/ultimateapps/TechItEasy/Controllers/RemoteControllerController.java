package nl.ultimateapps.TechItEasy.Controllers;

import nl.ultimateapps.TechItEasy.Dtos.RemoteControllerDto;
import nl.ultimateapps.TechItEasy.Exceptions.RecordNotFoundException;
import nl.ultimateapps.TechItEasy.Services.RemoteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/remotecontrollers")

public class RemoteControllerController {

    @Autowired
    private RemoteControllerService service;

    @GetMapping("")
    public ResponseEntity<ArrayList<RemoteControllerDto>> getRemoteControllers() {
        ArrayList<RemoteControllerDto> remoteControllerDtos = service.getRemoteControllers();
        if (remoteControllerDtos.size()>0) {
            return ResponseEntity.ok(remoteControllerDtos);
        } else {
            throw new RecordNotFoundException("No remote controllers found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteController (@PathVariable int id) {
        RemoteControllerDto remoteControllerDto = service.getRemoteController(id);
        return ResponseEntity.ok(remoteControllerDto);
    }

    @PostMapping("")
    public ResponseEntity<String> postRemoteController(@RequestBody RemoteControllerDto remoteControllerDto) {
        long savedRemoteController = service.createRemoteController(remoteControllerDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/remotecontrollers/" + savedRemoteController).toUriString());
        return ResponseEntity.created(uri).body("Remote controller created!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putRemoteController(@PathVariable long id, @RequestBody RemoteControllerDto remoteControllerDto) {
        long updatedRemoteControllerId = service.updateRemoteController(id, remoteControllerDto);
        return ResponseEntity.ok("Remote controller " + updatedRemoteControllerId + " was updated successfully");
    }

    //code voor een patch mapping (Om het kort te houden werkt het alleen met het veld Name ):
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchRemoteController(@PathVariable long id, @RequestBody RemoteControllerDto remoteControllerDto) {
        long partiallyUpdatedRemoteControllerId = service.partialUpdateRemoteController(id, remoteControllerDto);
        return ResponseEntity.ok("Remote controller " + partiallyUpdatedRemoteControllerId + " was partially updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRemoteController(@PathVariable long id) {
        long deletedRemoteController = service.deleteRemoteController(id);
        return ResponseEntity.ok("Remote controller "+ deletedRemoteController + " was deleted successfully.");
    }
}
