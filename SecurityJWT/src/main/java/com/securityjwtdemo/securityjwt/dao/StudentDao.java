package com.securityjwtdemo.securityjwt.dao;


import com.securityjwtdemo.securityjwt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StudentDao  extends JpaRepository<Student, Integer> {

    public Student findByUserName(String userName);

}
