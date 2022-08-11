package com.example.application.services;

import com.example.application.data.Student;
import org.springframework.context.ApplicationEvent;

public class StudentRegistrationEvent extends ApplicationEvent {

    private Student student;

    public StudentRegistrationEvent(Object source, Student student) {
        super(source);
        this.student = student;
        System.out.println("Student Reg event created");
    }

    public Student getStudent() {
        return student;
    }


}
