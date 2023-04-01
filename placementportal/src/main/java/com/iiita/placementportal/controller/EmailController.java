package com.iiita.placementportal.controller;

import com.iiita.placementportal.dtos.ModeratorSendEmailRequest;
import com.iiita.placementportal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

   @PostMapping("/send")
    public ResponseEntity<?> test(@RequestBody ModeratorSendEmailRequest moderatorSendEmailRequest){
       this.emailService.sendEmailAsPerJobIdAndStatus(moderatorSendEmailRequest);
//       this.emailService.sendEmail("hello","Testing","iit2019022@iiita.ac.in","noreply.placement.portal@gmail.com");
       return new ResponseEntity<>(HttpStatus.OK);
   }
}
