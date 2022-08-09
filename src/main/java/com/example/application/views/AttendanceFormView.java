package com.example.application.views;

import com.example.application.data.Student;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Route(value = "form", registerAtStartup = true)
public class AttendanceFormView extends VerticalLayout {
    private TextField name = new TextField("First and last name (How you'd like it to appear");
    private ComboBox dropdown = new ComboBox("Select your name from below");
    private Button button = new Button("Submit");

    AttendanceFormView(List<Student> students){
        H1 title = new H1("Astro Club Attendance");
        SimpleDateFormat pattern = new SimpleDateFormat("E MMM dd, YYYY");
        String date = pattern.format(new Date());
        H2 todaysDate = new H2(date);
        H3 firstMeeting = new H3("Is today your first meeting?");
        add(title, todaysDate,firstMeeting, name, dropdown);


//        FormLayout formLayout = new FormLayout();
//        formLayout.add(title, todaysDate,firstMeeting, name, dropdown);
//        add(formLayout);
        dropdown.setItems(students);
        button.addClickListener(event -> buttonPressed());


    }

    private void buttonPressed() {
        
    }


}
