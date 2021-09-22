package de.dlh.lhind.exercise.roommgmt.controller;

import de.dlh.lhind.exercise.roommgmt.model.Building;
import de.dlh.lhind.exercise.roommgmt.model.Room;
import de.dlh.lhind.exercise.roommgmt.service.RoomService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4500"})
public class RoomController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/rooms")
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        LOGGER.info("Add Room: {}", room);
        var newRoom = roomService.addRoom(room);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @PutMapping("/rooms")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        LOGGER.info("Update Room: {}", room);
        var updateRoom = roomService.updateRoom(room);
        return new ResponseEntity<>(updateRoom, HttpStatus.OK);
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<Room> deleteRoomById(@PathVariable("id") Long id) {
        LOGGER.info("Delete Room: {}", id);
        roomService.deleteRoomById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieves all rooms",
        notes = "A list of rooms",
        response = Room.class,
        responseContainer = "List",
        produces = "application/json")
    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> findAllRooms() {
        LOGGER.info("Get All Rooms");
        List<Room> rooms = roomService.findAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/rooms/{roomNumber}")
    public ResponseEntity<Room> findByRoomNumber(@PathVariable("roomNumber") String roomNumber) {
        LOGGER.info("Get Room by roomNumber: {}", roomNumber);
        var room = roomService.findByRoomNumber(roomNumber);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/buildings/{buildingNumber}/rooms")
    public ResponseEntity<List<Room>> findAllRoomsOfBuilding(
        @PathVariable("buildingNumber") String buildingNumber) {
        LOGGER.info("Get All Rooms of Building");
        var room = roomService.findAllRoomsOfBuilding(buildingNumber);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/buildings/{buildingNumber}/rooms/{roomNumber}")
    public ResponseEntity<Building> findByBuildingAndRoomNumber(
        @PathVariable("buildingNumber") String buildingNumber,
        @PathVariable("roomNumber") String roomNumber) {
        LOGGER.info("Get Building by buildingNumber and roomNumber: {}{}", buildingNumber,
            roomNumber);
        var building = roomService.findByBuildingAndRoomNumber(buildingNumber, roomNumber);
        return new ResponseEntity<>(building, HttpStatus.OK);
    }

    @GetMapping("/rooms/public")
    public ResponseEntity<List<Room>> findPublicRooms() {
        LOGGER.info("Get All Public Rooms");
        List<Room> rooms = roomService.findPublicRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

}
