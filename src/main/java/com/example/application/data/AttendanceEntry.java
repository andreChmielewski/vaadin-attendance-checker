package com.example.application.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class AttendanceEntry {

    @Id
    private String date;
    private ArrayList<Student> attendanceList;

    public AttendanceEntry() {
    }

    public AttendanceEntry(String date){
        this.date = date;
        attendanceList = new ArrayList<Student>();
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
}
