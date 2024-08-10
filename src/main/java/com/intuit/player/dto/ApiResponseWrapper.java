package com.intuit.player.dto;

import lombok.Data;

@Data
public class ApiResponseWrapper {

    private Object response;
    private String message;

    public ApiResponseWrapper(Object response){
        this.response = response;
    }

    public ApiResponseWrapper(String message) {
        this.message = message;
    }
}
