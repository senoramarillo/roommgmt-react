package de.dlh.lhind.exercise.roommgmt.service;

import de.dlh.lhind.exercise.roommgmt.exception.ResourceNotFoundException;
import de.dlh.lhind.exercise.roommgmt.model.Room;
import de.dlh.lhind.exercise.roommgmt.repository.RoomRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    public Room findByRoomNumber(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber)
            .orElseThrow(
                () -> new ResourceNotFoundException(
                    "Room by number " + roomNumber + " was not found"));
    }

    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> findAllRoomsOfBuilding(String buildingNumber) {
        return roomRepository.findAllRoomsOfBuilding(buildingNumber);
    }

    public Room findByBuildingAndRoomNumber(String buildingNumber, String roomNumber) {
        return roomRepository.findByBuildingAndRoomNumber(buildingNumber, roomNumber)
            .orElseThrow(() -> new ResourceNotFoundException
                ("Building or Room by buildingNumber " + buildingNumber + " roomNumber: "
                    + roomNumber
                    + " was not found"));
    }

    public List<Room> findPublicRooms() {
        return roomRepository.findPublicRooms();
    }

}
