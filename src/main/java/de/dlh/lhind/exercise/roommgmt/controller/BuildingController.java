package de.dlh.lhind.exercise.roommgmt.controller;

import de.dlh.lhind.exercise.roommgmt.model.Building;
import de.dlh.lhind.exercise.roommgmt.service.BuildingService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/buildings"})
@CrossOrigin(origins = {"http://localhost:3000"})
public class BuildingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuildingController.class);
    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping
    public ResponseEntity<Building> addBuilding(@Valid @RequestBody Building building) {
        LOGGER.info("Add Building: {}", building);
        var newBuilding = buildingService.addBuilding(building);
        return new ResponseEntity<>(newBuilding, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Building> updateBuilding(@RequestBody Building building) {
        LOGGER.info("Update Building: {}", building);
        var updateBuilding = buildingService.updateBuilding(building);
        return new ResponseEntity<>(updateBuilding, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Building> deleteBuildingById(@PathVariable("id") Long id) {
        LOGGER.info("Delete Building: {}", id);
        buildingService.deleteBuildingById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieves all buildings",
        notes = "A list of buildings",
        response = Building.class,
        responseContainer = "List",
        produces = "application/json")
    @GetMapping
    public ResponseEntity<List<Building>> getAllBuildings() {
        LOGGER.info("Get All Buildings");
        List<Building> buildings = buildingService.getAllBuildings();
        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }

    @GetMapping("/{buildingNumber}")
    public ResponseEntity<Building> findByBuildNumber(
        @PathVariable("buildingNumber") String buildingNumber) {
        LOGGER.info("Get Building by buildingNumber: {}", buildingNumber);
        var building = buildingService.findByBuildingNumber(buildingNumber);
        return new ResponseEntity<>(building, HttpStatus.OK);
    }

    @GetMapping("/public")
    public ResponseEntity<List<Building>> findPublicBuildings() {
        LOGGER.info("Get All Public Buildings");
        List<Building> building = buildingService.findPublicBuildings();
        return new ResponseEntity<>(building, HttpStatus.OK);
    }

}
