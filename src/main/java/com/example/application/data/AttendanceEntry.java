package com.example.application.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class AttendanceEntry {

    @Id
    private String date;

    private ArrayList<Student> students;

    public AttendanceEntry(String date){
        this.date = date;
        students = new ArrayList<Student>();

    }


}
