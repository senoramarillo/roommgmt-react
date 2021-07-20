package de.dlh.lhind.exercise.roommgmt.repository;

import de.dlh.lhind.exercise.roommgmt.model.Room;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("FROM Room r WHERE r.roomNumber = (:roomNumber)")
    Optional<Room> findByRoomNumber(@Param("roomNumber") String roomNumber);

    @Query("FROM Building b, Room r WHERE b.buildingNumber = (:buildingNumber) AND r.roomNumber = (:roomNumber)")
    Optional<Room> findByBuildingAndRoomNumber(String buildingNumber, String roomNumber);

    @Query(value = "SELECT r FROM Room r JOIN Building b ON b.id = r.building.id WHERE b.buildingNumber = (:buildingNumber)")
    List<Room> findAllRoomsOfBuilding(String buildingNumber);

    @Query("SELECT r FROM Room r JOIN Building b ON b.id = r.building.id WHERE b.publicAccess = true")
    List<Room> findPublicRooms();

}
