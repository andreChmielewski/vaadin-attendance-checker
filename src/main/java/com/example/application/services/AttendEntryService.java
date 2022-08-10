package com.example.application.services;

import com.example.application.data.AttendanceEntry;
import com.example.application.data.Student;
import com.example.application.repositories.AttendanceEntryRepository;
import com.example.application.repositories.StudentRepository;
import com.example.application.views.HomeView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AttendEntryService {

    private AttendanceEntryRepository entryRepository;
    private StudentRepository studentRepository;
    private ArrayList<Student> attendanceList;
    private HomeView homeView;

    public AttendEntryService(AttendanceEntryRepository entryRepository, StudentRepository studentRepository) {
        this.entryRepository = entryRepository;
        this.studentRepository = studentRepository;
        attendanceList = new ArrayList<Student>();

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


    public ArrayList<Student> getStudents() {
        return (ArrayList<Student>) studentRepository.findAll();
    }

    public void addStudentToGrid(Student student) {
        System.out.println("AttendEntryService received");
        attendanceList.add(student);
        homeView.updateGrid();


    }

    public void setHomeView(HomeView homeView) {
        this.homeView = homeView;
    }

    public ArrayList<Student> getAttendanceList() {
        return attendanceList;
    }
}
