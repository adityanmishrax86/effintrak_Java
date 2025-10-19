package com.azaxxc.effintrakj.effinTrak.globalcomponents.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {

    private String message;
    private HttpStatus status;
    private long timestamp;
    private String description;

}
