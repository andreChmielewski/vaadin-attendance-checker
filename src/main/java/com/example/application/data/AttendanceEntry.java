package com.example.application.data;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.UUID;

@Entity
public class AttendanceEntry {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    private String date;

    private ArrayList<Student> attendanceList = new ArrayList<Student>();;

    public AttendanceEntry() {
    }

    public AttendanceEntry(String date){
        this.date = date;
    }

    public ArrayList<Student> getAttendanceList() {
        return attendanceList;
    }


    public void clearList() {
        attendanceList.clear();
    }

    public void addToAttendanceList(Student student) {
        attendanceList.add(student);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
