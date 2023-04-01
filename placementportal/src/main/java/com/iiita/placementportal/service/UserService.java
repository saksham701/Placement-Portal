package com.iiita.placementportal.service;

import com.iiita.placementportal.dao.RoleDao;
import com.iiita.placementportal.dao.UserDao;
import com.iiita.placementportal.dtos.UserDto;
import com.iiita.placementportal.entity.RegisterUserRequest;
import com.iiita.placementportal.entity.Role;
import com.iiita.placementportal.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto,String email);

    void deleteUser(String email);

    UserDto getSingleUser(String email);

    List<UserDto> getAllUsers();

    void makeModerator(String email);
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private RoleDao roleDao;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    public UserDto registerNewUser(UserDto userDto){
//        User user = modelMapper.map(userDto,User.class);
//        User registeredUser = userDao.save(user);
//        return modelMapper.map(registeredUser,UserDto.class);
//    }
//
//    public void initRolesAndUser(){
////        Role adminRole = new Role();
////        adminRole.setRoleName("ADMIN");
////        roleDao.save(adminRole);
////
////        Role companyRole = new Role();
////        companyRole.setRoleName("COMPANY");
////        roleDao.save(companyRole);
////
////        Role studentRole = new Role();
////        studentRole.setRoleName("STUDENT");
////        roleDao.save(studentRole);
////
////        User adminUser = new User();
////        adminUser.setName("admin");
////        adminUser.setEmail("admin@gmail.com");
////        adminUser.setPassword(getEncodedPassword("admin@pass"));
////        Set<Role> adminRoles = new HashSet<>();
////        adminRoles.add(adminRole);
////        adminUser.setRole(adminRoles);
////        userDao.save(adminUser);
//
////        User companyUser = new User();
////        companyUser.setFirstName("tcs");
////        companyUser.setLastName("tcs");
////        companyUser.setEmail("tcs@gmail.com");
////        companyUser.setPassword(getEncodedPassword("tcs@pass"));
////        Set<Role> companyRoles = new HashSet<>();
////        companyRoles.add(companyRole);
////        companyUser.setRole(companyRoles);
////        userDao.save(companyUser);
////
////        User studentUser = new User();
////        studentUser.setFirstName("ram");
////        studentUser.setLastName("ram");
////        studentUser.setEmail("ram@gmail.com");
////        studentUser.setPassword(getEncodedPassword("ram@pass"));
////        Set<Role> studentRoles = new HashSet<>();
////        studentRoles.add(studentRole);
////        studentUser.setRole(studentRoles);
////        userDao.save(studentUser);
//
//    }
////    public User getNewUserFromRequest(RegisterUserRequest request){
////        User newUser = new User();
////        newUser.setName(request.getName());
////        newUser.setPassword(getEncodedPassword(request.getPassword()));
////        newUser.setEmail(request.getEmail());
////        newUser.setRole(new HashSet<>());
////        newUser.getRole().add(new Role(request.getRoleName()));
////        return newUser;
////    }
//
//    public String getEncodedPassword(String password){
//       return passwordEncoder.encode(password);
//    }
}
