package edu.example.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseEntity {
    private String message;
    private String error;
    private int status;
}
