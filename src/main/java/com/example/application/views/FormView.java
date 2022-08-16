package com.example.application.views;

import com.example.application.data.Student;
import com.example.application.events.StudentRegEvent;
import com.example.application.services.StudentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.text.SimpleDateFormat;
import java.util.Date;

@PageTitle("Attendance Form")
@Route(registerAtStartup = false, layout = MainLayout.class)
public class FormView extends VerticalLayout {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    private TextField newStudentName = new TextField("Enter your name:");
    private ComboBox studentDropdown = new ComboBox("Select your name:");
    private Button button = new Button("Submit", event -> buttonPressed());

    private H3 message1 = new H3("First meeting?");
    private H3 message2 = new H3("Returning?");

    private StudentService studentService;

    public FormView(StudentService studentService) {
        this.studentService = studentService;
        H1 title = new H1("Astro Club Meeting");
        String pattern = "MMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateString = simpleDateFormat.format(new Date());
        H2 todaysDate = new H2(dateString);
        add(title, todaysDate);

        // Populate studentDropdown
        studentDropdown.setItems(studentService.getStudents());

        // Initial form configuration
        add(message1, newStudentName, message2, studentDropdown, button);

        // Formatting
        setSizeFull();
        setSpacing(false);
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

    }

    private void buttonPressed() {

        Student student;
//        if nameField.text == “”
        if(newStudentName.isEmpty()){
//          NAME = dropdown.text
//          IS_NEW = false
            student = (Student) studentDropdown.getValue();
        }
        else {
//        else
//          NAME = nameField.text
//          IS_NEW = true
            student = new Student(newStudentName.getValue());
        }
//        end if
//
//        studentList.add(STUDENT)
        StudentRegEvent studentRegEvent = new StudentRegEvent(this, student);
        applicationEventPublisher.publishEvent(studentRegEvent);


//        updateDisplay()
        message1.setText("Thank you, " + student.getName() + "!");
        message2.setText("Your attendance has been registered!");
        remove(newStudentName, studentDropdown, button);

//        System.out.println(student);
    }


}
