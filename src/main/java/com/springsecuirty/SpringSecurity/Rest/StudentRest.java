package com.springsecuirty.SpringSecurity.Rest;


import com.springsecuirty.SpringSecurity.ExceptionHandling.StudentNotFoundException;
import com.springsecuirty.SpringSecurity.Pojos.Student;
import com.springsecuirty.SpringSecurity.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentRest {
    private StudentService studentService;

    @Autowired
    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentService.findAll();
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        student.setStudentId(0);
        return studentService.save(student);
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student){
        return studentService.save(student);
    }

    @GetMapping("/students/{studentId}")
    public Student findById(@PathVariable int studentId) throws StudentNotFoundException {
        Student student=studentService.findById(studentId);
        if (student==null){
            throw new StudentNotFoundException("Not Found Student with Id: "+studentId);
        }
        return student;
    }

    @DeleteMapping("/students/{studentId}")
    public  void deleteStudent(@PathVariable int studentId) throws StudentNotFoundException {
        Student student=studentService.findById(studentId);
        if(student==null){
            throw new StudentNotFoundException("Not Found Student with Id: "+studentId);
        }
        studentService.deleteById(studentId);
    }
}
