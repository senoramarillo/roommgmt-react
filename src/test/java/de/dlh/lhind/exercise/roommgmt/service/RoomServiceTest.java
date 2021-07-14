package de.dlh.lhind.exercise.roommgmt.service;

import de.dlh.lhind.exercise.roommgmt.model.Building;
import de.dlh.lhind.exercise.roommgmt.model.Room;
import de.dlh.lhind.exercise.roommgmt.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    private static final Long ROOM_ID = 1L;
    public static final String ROOM_NUMBER = "001";
    public static final Integer ROOM_SEATS = 10;
    public static final Boolean PROJECTOR_PRESENT = true;

    private static final Long BUILDING_ID = 1L;
    public static final String BUILDING_NUMBER = "001";
    public static final String BUILDING_DESCRIPTION_STRING = "Zentrale";
    public static final Boolean PUBLIC_ACCESS = true;


    @Mock
    private RoomRepository roomRepository;
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        roomService = new RoomService(roomRepository);
    }

    @Test
    @DisplayName("Add new room")
    void shouldAdd_whenAddRoom_givenValidRoom() {
        // given
        Room room = new Room();
        Building building = new Building();

        building.setId(BUILDING_ID);
        building.setBuildingNumber(BUILDING_NUMBER);
        building.setDescription(BUILDING_DESCRIPTION_STRING);
        building.setPublicAccess(PUBLIC_ACCESS);

        room.setId(ROOM_ID);
        room.setBuilding(building);
        room.setRoomNumber(ROOM_NUMBER);
        room.setSeats(ROOM_SEATS);
        room.setProjectorPresent(PROJECTOR_PRESENT);

        // when
        roomService.addRoom(room);

        // then
        ArgumentCaptor<Room> roomArgumentCaptor = ArgumentCaptor.forClass(Room.class);
        verify(roomRepository).save(roomArgumentCaptor.capture());
        Room capturedRoom = roomArgumentCaptor.getValue();
        assertThat(capturedRoom).isEqualTo(room);
    }

    @Test
    @DisplayName("Get all rooms")
    void shouldFindAll_whenGetAllRooms() {
        // when
        roomService.findAllRooms();

        // then
        verify(roomRepository).findAll();
    }

}