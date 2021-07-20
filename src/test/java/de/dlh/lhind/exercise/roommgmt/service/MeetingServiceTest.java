package de.dlh.lhind.exercise.roommgmt.service;

import static org.mockito.Mockito.verify;

import de.dlh.lhind.exercise.roommgmt.repository.MeetingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MeetingServiceTest {

    @Mock
    private MeetingRepository meetingRepository;
    private MeetingService meetingService;

    @BeforeEach
    void setUp() {
        meetingService = new MeetingService(meetingRepository);
    }

    @Test
    @DisplayName("Get all meetings")
    void shouldFindAll_whenGetAllRooms() {
        // when
        meetingService.findAllMeetings();

        // then
        verify(meetingRepository).findAll();
    }

}