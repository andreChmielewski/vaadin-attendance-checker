package com.example.application.views;

import com.example.application.data.Student;
import com.example.application.services.AttendEntryService;
import com.example.application.services.StudentRegistrationEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.context.ApplicationEventPublisher;

import java.text.SimpleDateFormat;
import java.util.Date;

@Route(registerAtStartup = false)
public class AttendanceFormView extends VerticalLayout {


    private final ApplicationEventPublisher applicationEventPublisher;

    private TextField name = new TextField("First and last name (How you'd like it to appear");
    private ComboBox dropdown = new ComboBox("Select your name from below");
    private Button button = new Button("Submit");
    private AttendEntryService attendEntryService;

    private H3 message1, message2;



    AttendanceFormView(AttendEntryService attendEntryService, ApplicationEventPublisher applicationEventPublisher){
        this.applicationEventPublisher = applicationEventPublisher;
        this.attendEntryService = attendEntryService;
        H1 title = new H1("Astro Club Attendance");
        SimpleDateFormat pattern = new SimpleDateFormat("E MMM dd, YYYY");
        String date = pattern.format(new Date());
        H2 todaysDate = new H2(date);
        add(title, todaysDate);
        configureAttendanceForm();


        // Formatting
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);


    }

    private void configureAttendanceForm() {
        message1 = new H3("Is today your first meeting?");
        message2 = new H3("Are you a returning student?");
        dropdown.setItems(attendEntryService.getStudents());
        add(message1, name, message2, dropdown);

        button.addClickListener(event -> buttonPressed());
        add(button);
    }

    private void buttonPressed() {
        String studentName;
        Student student;
        if(name.isEmpty()){
            studentName = dropdown.getValue().toString();
            student = new Student(studentName);
        }
        else {
            studentName = name.getValue().toString();
            student = attendEntryService.addStudent(new Student(studentName));

        }
        System.out.println(studentName + " registered");
        applicationEventPublisher.publishEvent(new StudentRegistrationEvent(this, student));
        closeForm(studentName);

    }

    private void closeForm(String studentName) {
        remove(name, message2, dropdown, button);
        message1.setText("Thank you for submitting your attendance, " + studentName);


    }


}
