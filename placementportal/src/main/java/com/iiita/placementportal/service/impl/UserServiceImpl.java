package com.iiita.placementportal.service.impl;

import com.iiita.placementportal.dao.UserDao;
import com.iiita.placementportal.dtos.UserDto;
import com.iiita.placementportal.entity.User;
import com.iiita.placementportal.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User createdUser = this.userDao.save(this.modelMapper.map(userDto, User.class));
        return this.modelMapper.map(createdUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String email) {
        User user = this.userDao.findById(email).orElseThrow(()->new RuntimeException("User doesnt exist"));
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        User updatedUser = this.userDao.save(user);
        return this.modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public void deleteUser(String email) {
        User user = this.userDao.findById(email).orElseThrow(()->new RuntimeException("User doesnt exist"));
        this.userDao.delete(user);
    }

    @Override
    public UserDto getSingleUser(String email) {
        User user = this.userDao.findById(email).orElseThrow(()->new RuntimeException("User doesnt exist"));
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = (List<User>) this.userDao.findAll();
        return allUsers.stream().map((user -> this.modelMapper.map(user,UserDto.class))).collect(Collectors.toList());
    }
}
