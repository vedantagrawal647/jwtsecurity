package com.securityjwtdemo.securityjwt.service;

import com.securityjwtdemo.securityjwt.dao.StudentDao;
import com.securityjwtdemo.securityjwt.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    public Student addStudentDetails(Student student)
    {
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        studentDao.save(student);
        return student;
    }

    public Student findStudentByUserName(String userName){
        return studentDao.findByUserName(userName);
    }
}
