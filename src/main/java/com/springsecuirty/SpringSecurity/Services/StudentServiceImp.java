package com.springsecuirty.SpringSecurity.Services;


import com.springsecuirty.SpringSecurity.Dao.StudentRepository;
import com.springsecuirty.SpringSecurity.Pojos.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class StudentServiceImp implements  StudentService{
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        Student student=null;
        Optional<Student> byId = studentRepository.findById(id);
        if(byId.isPresent()){
            student=byId.get();
        }
        return student;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }
}
