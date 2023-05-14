package com.lcwd.hotel.service.HotelService.services;

import com.lcwd.hotel.service.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    // create hotel
    Hotel create(Hotel hotel);

    // get all hotel
    List<Hotel> getAllHotel();

    // get single hotel
    Hotel getHotel(String hotelId);
}
