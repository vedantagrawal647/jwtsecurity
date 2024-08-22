package com.securityjwtdemo.securityjwt.controller;

import com.securityjwtdemo.securityjwt.entity.Student;
import com.securityjwtdemo.securityjwt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/ab")
    public String m2()
    {
        return "hello";
    }



    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student)
    {
        Student resStudent = studentService.addStudentDetails(student);
        return ResponseEntity.ok(resStudent);
    }

    @GetMapping("/getStudentByName/{userName}")
    public  Student  getStudentByName(@PathVariable("userName") String name)
    {
        System.out.println(name);
        return studentService.findStudentByUserName(name);
    }


}
