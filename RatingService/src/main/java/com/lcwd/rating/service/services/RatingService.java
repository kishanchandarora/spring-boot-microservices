package com.lcwd.rating.service.services;

import com.lcwd.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    // create rating
    Rating create(Rating rating);

    // get all rating
    List<Rating> getRatings();

    // get ratings user wise
    List<Rating> getRatingByUserId(String userId);

    // get ratings hotel wise
    List<Rating> getRatingByHotelId(String hotelId);

}
