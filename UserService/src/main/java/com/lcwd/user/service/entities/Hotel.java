package com.lcwd.user.service.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

    private String hotelId;
    private String name;
    private String location;
    private String about;
}
