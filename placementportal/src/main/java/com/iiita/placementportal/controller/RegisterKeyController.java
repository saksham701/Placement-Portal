package com.iiita.placementportal.controller;

import com.iiita.placementportal.dao.RegisterKeyDao;
import com.iiita.placementportal.dtos.RegisterKeyDto;
import com.iiita.placementportal.entity.RegisterKey;
import com.iiita.placementportal.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/key")
public class RegisterKeyController {
    @Autowired
    private RegisterKeyDao registerKeyDao;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<RegisterKeyDto> addKey(@RequestBody RegisterKeyDto registerKeyDto){
        return new ResponseEntity<>(this.modelMapper.map(this.registerKeyDao.save(this.modelMapper.map(registerKeyDto, RegisterKey.class)),RegisterKeyDto.class), HttpStatus.OK) ;
    }

    @DeleteMapping("/{invite_key}")
    public void deleteKey(@PathVariable("invite_key")String inviteKey){
        RegisterKey registerKey = this.registerKeyDao.findById(inviteKey).orElseThrow(()->new ResourceNotFoundException("Invite key","Register key",inviteKey));
        this.registerKeyDao.delete(registerKey);
    }
    @GetMapping("/all")
    public ResponseEntity<List<RegisterKeyDto>> getAllKeys(){
        List<RegisterKey> allKeys =(List<RegisterKey>) this.registerKeyDao.findAll();
        return new ResponseEntity<>(allKeys.stream().map((key)->this.modelMapper.map(key,RegisterKeyDto.class)).collect(Collectors.toList()),HttpStatus.OK);
    }
}
