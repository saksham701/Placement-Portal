package com.iiita.placementportal.service;

import com.iiita.placementportal.dtos.JobApplicationDto;
import com.iiita.placementportal.dtos.ModeratorSendEmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class EmailService {
    @Autowired
    private JobApplicationService jobApplicationService;

    public void sendEmailAsPerJobIdAndStatus(ModeratorSendEmailRequest moderatorSendEmailRequest){
        List<JobApplicationDto> jobApplicationsForJobId = jobApplicationService.getAllJobApplicationForJobOpening(moderatorSendEmailRequest.getJobId());
       List<String> recp =  jobApplicationsForJobId.stream().filter(jobApplicationDto -> jobApplicationDto.getStatus().toString().equals(moderatorSendEmailRequest.getStatus())).map(jobApplicationDto -> jobApplicationDto.getUser().getEmail()).collect(Collectors.toList());
//       System.out.println(recp);
//        for(String to:recp){
//            sendEmail(moderatorSendEmailRequest.getMessage(),moderatorSendEmailRequest.getSubject(),to,"noreply.placement.portal@gmail.com");
//        }
        sendEmail(moderatorSendEmailRequest.getMessage(),moderatorSendEmailRequest.getSubject(),recp,"noreply.placement.portal@gmail.com");
    }

    public void sendEmail(String message,String subject,List<String> to,String from){
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("noreply.placement.portal@gmail.com","wudrvyofmezfhium");
            }
        });
        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);
        try{
            m.setFrom(from);
            int counter=0;
            Address[] recp = new InternetAddress[to.size()];
            for(String tt:to){
                recp[counter++] = new InternetAddress(tt.trim());
            }
            m.addRecipients(Message.RecipientType.TO, recp);
//            m.addRecipient(Message.RecipientType.TO,new InternetAddresses(to));
            m.setSubject(subject);
            m.setText(message);
            Transport.send(m);
            System.out.println("Send success");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
