package com.lcwd.hotel.service.HotelService.exceptions;

public class ResourceNotfoundException extends RuntimeException {

    public ResourceNotfoundException() {
        super("Resource not found on server !!");
    }

    public ResourceNotfoundException(String message) {
        super(message);
    }
}
