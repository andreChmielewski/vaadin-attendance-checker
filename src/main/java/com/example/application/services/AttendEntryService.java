package com.example.application.services;

import com.example.application.data.AttendanceEntry;
import com.example.application.data.Student;
import com.example.application.repositories.AttendanceEntryRepository;
import com.example.application.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendEntryService {

    private AttendanceEntryRepository entryRepository;
    private StudentRepository studentRepository;

    public AttendEntryService(AttendanceEntryRepository entryRepository, StudentRepository studentRepository) {
        this.entryRepository = entryRepository;
        this.studentRepository = studentRepository;
    }

    public void saveAttendEntry(AttendanceEntry entry){
        entryRepository.save(entry);
    }

    public AttendanceEntry getEntryFor(String date){
        Optional<AttendanceEntry> entry = entryRepository.findById(date);
        if(entry.isPresent()){
            return entry.get();
        }
        return new AttendanceEntry(date);
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
