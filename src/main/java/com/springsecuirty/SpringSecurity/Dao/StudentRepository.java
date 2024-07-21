package com.springsecuirty.SpringSecurity.Dao;


import com.springsecuirty.SpringSecurity.Pojos.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student,Integer> {
}
