package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        // generate unique userId everytime
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        // TODO: implement RATING SERVICE CALL: using REST Template
        return userRepository.findAll();
    }

    @Override
    public User getSingleUser(String userId) {
        // get user from database with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !!: " + userId));

        // fetch rating of the above user from RATING-SERVICE
        // http://localhost:8083/ratings/users/c5440992-dded-4daa-9bdd-ab47c19e3d13
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get hotel
            // http://localhost:8082/hotels/a1c0fc64-ae25-4e5d-b680-d4e5d6146a4d
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("response status code: {}", forEntity.getStatusCode());

            // set the hotel to rating
            rating.setHotel(hotel);

            // return rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
