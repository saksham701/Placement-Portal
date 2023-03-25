package com.iiita.placementportal.service.impl;

import com.iiita.placementportal.dao.UserDao;
import com.iiita.placementportal.dtos.UserDto;
import com.iiita.placementportal.entity.User;
import com.iiita.placementportal.exceptions.ResourceNotFoundException;
import com.iiita.placementportal.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(getEncodedPassword(userDto.getPassword()));
        User createdUser = this.userDao.save(this.modelMapper.map(userDto, User.class));
        return this.modelMapper.map(createdUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String email) {
        User user = this.userDao.findById(email).orElseThrow(()->new ResourceNotFoundException("User","Email",email));
        user.setEmail(userDto.getEmail());
        user.setPassword(getEncodedPassword(userDto.getPassword()));
        user.setRole(userDto.getRole());
        if(userDto.getPlacedStatus() != null){
            user.setPlacedStatus(userDto.getPlacedStatus());
        }
        if(userDto.getCollege()!=null){
            user.setCollege(userDto.getCollege());
        }
        if(userDto.getName()!=null){
            user.setName(userDto.getName());
        }
        if(userDto.getRollNo()!=null){
            user.setRollNo(user.getRollNo());
        }
        User updatedUser = this.userDao.save(user);
        return this.modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public void deleteUser(String email) {
        User user = this.userDao.findById(email).orElseThrow(()->new ResourceNotFoundException("User","Email",email));
        this.userDao.delete(user);
    }

    @Override
    public UserDto getSingleUser(String email) {
        User user = this.userDao.findById(email).orElseThrow(()->new ResourceNotFoundException("User","Email",email));
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = (List<User>) this.userDao.findAll();
        return allUsers.stream().map((user -> this.modelMapper.map(user,UserDto.class))).collect(Collectors.toList());
    }

    public String getEncodedPassword(String password){
       return passwordEncoder.encode(password);
    }
}
