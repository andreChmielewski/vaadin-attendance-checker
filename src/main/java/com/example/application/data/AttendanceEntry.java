package com.example.application.data;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class AttendanceEntry {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    private String date;

//    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> attendanceList = new ArrayList<Student>();

    public AttendanceEntry() {
    }

    public AttendanceEntry(String date){
        this.date = date;
    }

    public List<Student> getAttendanceList() {
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
