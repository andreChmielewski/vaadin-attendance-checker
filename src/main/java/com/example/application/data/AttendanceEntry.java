package com.example.application.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.LinkedList;

@Entity
public class AttendanceEntry {

    @Id
    private String date;

    private LinkedList<Student> students;

    public AttendanceEntry(String date){
        this.date = date;

    }


}
