package com.example.application.services;

import com.example.application.data.Student;
import com.example.application.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepo;

    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudents(){
        return studentRepo.findAll();
    }

    public List<Student> addStudents(List<Student> studentsList){
        return studentRepo.saveAll(studentsList);
    }
}
