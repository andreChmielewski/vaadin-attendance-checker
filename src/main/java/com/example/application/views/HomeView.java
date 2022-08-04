package com.example.application.views;


import com.example.application.data.Student;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
public class HomeView extends HorizontalLayout {

    private Button button = new Button("Start");
    private H2 dateString = new H2("---");
    private Image img = new Image("images/empty-plant.png", "placeholder plant");
    private Grid<Student> grid = new Grid<>(Student.class, false);
    private ArrayList<Student> studentList = new ArrayList<Student>();

    public HomeView() {
        VerticalLayout leftSide = new VerticalLayout();
        VerticalLayout rightSide = new VerticalLayout();
        add(leftSide, rightSide);

        // Left side
        leftSide.add(button);
        leftSide.add(new H2("Present students"));
        studentList.add(new Student("Dave"));
        grid.setItems(studentList);
        grid.addColumn(Student::getName).setHeader("Name");
        leftSide.add(grid);
        leftSide.setSizeFull();
        leftSide.setSpacing(false);
        leftSide.setJustifyContentMode(JustifyContentMode.CENTER);
        leftSide.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        leftSide.getStyle().set("text-align", "center");

        // Right side
        rightSide.add(new H1("Astro Club Meeting"));
        rightSide.add(dateString);
        img.setWidth("250px");
        rightSide.add(img);
        rightSide.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        rightSide.setSizeFull();
        rightSide.setJustifyContentMode(JustifyContentMode.START);


        // Overall view
        setSizeFull();

        // Button
        button.addClickListener(clickEvent -> pressedButton());

    }

    private void pressedButton() {
//        // Button pressed
//        if BUTTON.text = “Start”
        String text = button.getText();
        if(text.equals("Start")){
//        // Start button pressed
//        BUTTON.text = “Stop”
            button.setText("Stop");
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
//        DATE = Date.now()
//        DATE_STRING = DATE.format(‘YYYY-MM-DD’)
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
//        MainView.date = DATE_STRING
            dateString.setText(date);

//        FORM_URL = createAttendanceForm(date)
            String formUrl = createAttendanceForm(date);

//        IMAGE_URL = getQrCode(FORM_URL)
            String qrCodeUrl = getQrCodeUrl(formUrl);

//        MainView.qrcode = Image(IMAGE_URL)
            img.setSrc(qrCodeUrl);

        }
//else if BUTTON.text = “Stop”
        else if(text.equals("Stop")){
//        // Stop button pressed
//        closeAttendanceForm()
            closeAttendanceForm();

//        MainView.qrcode = Image(null)
            img.setSrc("images/empty-plant.png");
//        saveStudentsToDatabase(STUDENT_LIST)
            saveStudentsToDatabase(studentList);
//        MainView.list = “”
            studentList.clear();
            grid.setItems(studentList);

//        BUTTON.text = “Start”
            button.setText("Start");
            button.removeThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
//        MainView.date = “---”
            dateString.setText("---");
//
//        end if
        }

    }

    private void saveStudentsToDatabase(ArrayList<Student> studentList) {
    }

    private void closeAttendanceForm() {
    }

    private String getQrCodeUrl(String formUrl) {
        String baseUrl = "https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=";
        return baseUrl + formUrl;
    }

    private String createAttendanceForm(String date) {
        return "http://webhamster.com";
    }

}
