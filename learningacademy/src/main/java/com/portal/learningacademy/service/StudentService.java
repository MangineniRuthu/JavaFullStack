package com.portal.learningacademy.service;

import com.portal.learningacademy.model.Student;
import com.portal.learningacademy.util.MailConfiguration;
import com.portal.learningacademy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JavaMailSender javaMailSender;
   public String registerStudent(Student student){
        String toEmail=student.getEmail();
        String message="Hi Miss/Mr "+ student.getFirstName()+" Thank you for Registration.Please confirm your registration by clicking below link";
//        Util util=new Util();
//        util.mailSent(toEmail,message,link);


       student.setRegistrationConfirmation(false);
       this.studentRepository.save(student);
       String link="http://localhost:8080/"+"confirm/"+student.getStudentId();


       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
       simpleMailMessage.setFrom("magineniruthu998@gmail.com");
       simpleMailMessage.setTo(toEmail);
       simpleMailMessage.setSubject("Mail confirmation regarding your registration for learning academy");
       simpleMailMessage.setText(message + link);
       this.javaMailSender.send(simpleMailMessage);
       return "Mail Sent Successfully";

    }

    public String confirmRegistration(Integer myId) {
       Student studentData=this.studentRepository.findById(myId).get();
       studentData.setRegistrationConfirmation(true);
       this.studentRepository.save(studentData);
       return "Thank you for confirmation,Now your a confirmed Student";
    }
}
