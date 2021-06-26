package de.dlh.lhind.exercise.roommgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BuildingNotFoundException extends RuntimeException{

    public BuildingNotFoundException(String msg) {
        super(msg);
    }

}
