package com.example.application.services;

import com.example.application.data.AttendanceEntry;
import com.example.application.data.Student;
import com.example.application.repositories.AttendanceEntryRepository;
import com.example.application.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendEntryService {

    private AttendanceEntryRepository entryRepository;
    private StudentRepository studentRepository;

    public AttendEntryService(AttendanceEntryRepository entryRepository, StudentRepository studentRepository) {
        this.entryRepository = entryRepository;
        this.studentRepository = studentRepository;

    }

    public AttendanceEntry saveAttendEntry(AttendanceEntry entry){
        return entryRepository.save(entry);
    }

    public AttendanceEntry getEntryFor(String date){

        AttendanceEntry entry = entryRepository.findByDate(date);
        if(entry != null){
            return entry;
        }
        return new AttendanceEntry(date);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    public Student addStudent(Student student){
        return studentRepository.save(student);
    }






}
