package com.example.application.events;

import com.example.application.data.Student;
import org.springframework.context.ApplicationEvent;

public class StudentRegEvent extends ApplicationEvent {

    private final Student student;

    public StudentRegEvent(Object source, Student student) {
        super(source);
        this.student = student;
        System.out.println("StudentReg Event created with " + student);
    }
    public Student getStudent() {
        return student;
    }
}
