package com.iiita.placementportal.entity;

import com.iiita.placementportal.dtos.UserDto;

public class JwtResponse {
    private UserDto user;

    public JwtResponse(UserDto user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    private String jwtToken;
}
