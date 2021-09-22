package de.dlh.lhind.exercise.roommgmt.controller;

import de.dlh.lhind.exercise.roommgmt.model.Meeting;
import de.dlh.lhind.exercise.roommgmt.service.MeetingService;
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
@RequestMapping(value = {"/meetings"})
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4500"})
public class MeetingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeetingController.class);
    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public ResponseEntity<Meeting> addBuilding(@Valid @RequestBody Meeting meeting) {
        LOGGER.info("Add Meeting: {}", meeting);
        var newMeeting = meetingService.addMeeting(meeting);
        return new ResponseEntity<>(newMeeting, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Meeting> updateMeeting(@RequestBody Meeting meeting) {
        LOGGER.info("Update Meeting: {}", meeting);
        var updateMeeting = meetingService.updateMeeting(meeting);
        return new ResponseEntity<>(updateMeeting, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Meeting> deleteBuildingById(@PathVariable("id") Long id) {
        LOGGER.info("Delete Meeting: {}", id);
        meetingService.deleteMeetingById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Meeting>> findAllMeetings() {
        LOGGER.info("Get All Meetings");
        List<Meeting> meetings = meetingService.findAllMeetings();
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }

}
