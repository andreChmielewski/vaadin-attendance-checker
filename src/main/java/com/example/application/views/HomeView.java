package com.example.application.views;


import com.example.application.data.Student;
import com.example.application.services.AttendEntryService;
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
import java.util.Random;

@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
public class HomeView extends HorizontalLayout {

    private final AttendEntryService attendEntryService;
    private Button button = new Button("Start");
    private H2 dateString = new H2("---");
    private Image img = new Image("images/empty-plant.png", "placeholder plant");
    private Grid<Student> grid = new Grid<>(Student.class, false);
    private ArrayList<Student> studentList = new ArrayList<Student>();

    public HomeView(AttendEntryService attendEntryService) {
        this.attendEntryService = attendEntryService;
        // Add two side-by-side vertical layouts (columns) to hold other UI components
        VerticalLayout leftSide = new VerticalLayout();
        VerticalLayout rightSide = new VerticalLayout();
        add(leftSide, rightSide);

        // Left side components
        leftSide.add(button);
        leftSide.add(new H2("Present students"));

        // Grid bound to Student class takes Student objects
        studentList.add(new Student("Dave"));
        grid.setItems(studentList);
        grid.addColumn(Student::getName).setHeader("Name");
        leftSide.add(grid);

        // Formatting of left side
        leftSide.setSizeFull();
        leftSide.setSpacing(false);
        leftSide.setJustifyContentMode(JustifyContentMode.CENTER);
        leftSide.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        leftSide.getStyle().set("text-align", "center");

        // Right side components and formatting
        rightSide.add(new H1("Astro Club Meeting"));
        rightSide.add(dateString);
        img.setWidth("250px");
        rightSide.add(img);
        rightSide.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        rightSide.setSizeFull();
        rightSide.setJustifyContentMode(JustifyContentMode.START);

        // Overall view
        setSizeFull();

        // Adding listener to button component
        button.addClickListener(clickEvent -> pressedButton());

    }

    private void pressedButton() {
//        // Button pressed
//        if BUTTON.text = “Start”
        String text = button.getText();
        if(text.equals("Start")){
//        // Start button pressed

            // Modify Start button to different color and with different text
//        BUTTON.text = “Stop”
            button.setText("Stop");
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

            // Today's date is formatted to make readable on HomeView
//        DATE = Date.now()
//        DATE_STRING = DATE.format(‘YYYY-MM-DD’)
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date today = new Date();
            String date = simpleDateFormat.format(today);
//        HomeView.date = DATE_STRING
            dateString.setText(date);

            // Creating Attendance Form for today's meeting
            // method returns URL for form, which will be
            // incorporated into QR code
//        FORM_URL = createAttendanceForm(date)
            String formUrl = createAttendanceForm(today);

            // Generating QR Code image source link
//        IMAGE_URL = getQrCode(FORM_URL)
            String qrCodeUrl = getQrCodeUrl(formUrl);

//        HomeView.qrcode = Image(IMAGE_URL)
            img.setSrc(qrCodeUrl);

        }
//else if BUTTON.text = “Stop”
        else if(text.equals("Stop")){
//        // Stop button pressed
//        closeAttendanceForm()
            closeAttendanceForm();
//        saveStudentsToDatabase(STUDENT_LIST)
            saveStudentsToDatabase(studentList);
//        HomeView.list = “”
            studentList.clear();
            grid.setItems(studentList);

            // Resetting the components back to their original state
            // Components can be modified with built-in methods
//            HomeView.qrcode = Image(null)
            img.setSrc("images/empty-plant.png");
//        BUTTON.text = “Start”
            button.setText("Start");
            button.removeThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
//        HomeView.date = “---”
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

    private String createAttendanceForm(Date today) {
        ArrayList<Student> students = (ArrayList<Student>) attendEntryService.getStudents();
        AttendanceFormView formView = new AttendanceFormView(students);
        Random rand = new Random();

//        // set route
//        RouteConfiguration.forApplicationScope()
//                .setRoute(
//                    "form",
//                    AttendanceFormView.class
//                );
            return "form";
//        return RouteConfiguration.forApplicationScope().getUrl(AttendanceFormView.class);
    }

}
