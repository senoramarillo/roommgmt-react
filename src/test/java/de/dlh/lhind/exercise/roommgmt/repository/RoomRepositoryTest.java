package de.dlh.lhind.exercise.roommgmt.repository;

import de.dlh.lhind.exercise.roommgmt.model.Building;
import de.dlh.lhind.exercise.roommgmt.model.Room;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTest {

    private static final Long BUILDING_ID = -1L;
    public static final String BUILDING_NUMBER = "001";
    public static final String BUILDING_DESCRIPTION_STRING = "Zentrale";
    public static final Boolean PUBLIC_ACCESS = true;

    private static final Long ROOM_ID = 1L;
    public static final String ROOM_NUMBER = "001";
    public static final Integer ROOM_SEATS = 10;
    public static final Boolean PROJECTOR_PRESENT = true;

    @Autowired
    private RoomRepository roomRepository;

    @AfterEach
    void tearDown() {
        roomRepository.deleteAll();
    }

    @Test
    public void shouldCheck_whenFindByRoomNumber_givenValidRoomNumber() {
        // given
        Building building = new Building();
        Room room = new Room();

        building.setId(BUILDING_ID);
        building.setBuildingNumber(BUILDING_NUMBER);
        building.setDescription(BUILDING_DESCRIPTION_STRING);
        building.setPublicAccess(PUBLIC_ACCESS);

        room.setId(ROOM_ID);
        room.setBuilding(building);
        room.setRoomNumber(ROOM_NUMBER);
        room.setSeats(ROOM_SEATS);
        room.setProjectorPresent(PROJECTOR_PRESENT);


        roomRepository.save(room);

        // when
        Optional<Room> expected = roomRepository.findByRoomNumber(ROOM_NUMBER);

        // then
        assertThat(expected).isPresent();
    }

    @Test
    public void shouldCheck_whenFindByRoomNumber_givenInvalidRoomNumber() {
        // given
        String INVALID_ROOM_NUMBER = "002";

        // when
        Optional<Room> expected = roomRepository.findByRoomNumber(INVALID_ROOM_NUMBER);

        // then
        assertThat(expected).isNotPresent();
    }

}