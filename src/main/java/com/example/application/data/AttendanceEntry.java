package com.example.application.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AttendanceEntry {

    @Id
    @GeneratedValue
    private Integer id;

    private String date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> attendanceList = new ArrayList<Student>();

    public AttendanceEntry() {
    }

    public AttendanceEntry(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Student> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(ArrayList<Student> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public void addStudentToAttendance(Student student) {
        attendanceList.add(student);
    }
}
