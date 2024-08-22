package com.securityjwtdemo.securityjwt.config_security;

import com.securityjwtdemo.securityjwt.entity.Student;
import com.securityjwtdemo.securityjwt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("detailsService1")
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Student student = studentService.findStudentByUserName(username);
        if (student==null)
        {
            System.out.println("4");
            throw new UsernameNotFoundException(username);
        }
        else {
            System.out.println("Hi");
            return   new CustomUserDetails(student);
        }
    }
}
