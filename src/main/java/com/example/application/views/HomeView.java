package com.example.application.views;


import com.example.application.data.AttendanceEntry;
import com.example.application.data.Student;
import com.example.application.events.StudentRegEvent;
import com.example.application.services.AttendanceEntryService;
import com.example.application.services.StudentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
public class HomeView extends HorizontalLayout implements ApplicationListener<StudentRegEvent> {

    private Button button = new Button("Start");
    private H2 dateString = new H2("---");
    private Image img = new Image("images/empty-plant.png", "placeholder plant");
    private Anchor link = new Anchor("#", img);
    private Grid<Student> grid = new Grid<>(Student.class, false);
    private AttendanceEntry attendanceEntry;
    private StudentService studentService;
    private AttendanceEntryService attendService;


    public HomeView(AttendanceEntryService attendService, StudentService studentService, ConfigurableApplicationContext applicationContext) {
        this.studentService = studentService;
        this.attendService = attendService;
        VerticalLayout leftSide = new VerticalLayout();
        VerticalLayout rightSide = new VerticalLayout();
        add(leftSide, rightSide);

        // Left side
        leftSide.add(button);
        leftSide.add(new H2("Present students"));
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
        rightSide.add(link);
        rightSide.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        rightSide.setSizeFull();
        rightSide.setJustifyContentMode(JustifyContentMode.START);


        // Overall view
        setSizeFull();

        // Button
        button.addClickListener(clickEvent -> pressedButton());

        // Add FormView event listener
        applicationContext.addApplicationListener(this);

        // Refresh button for debugging purposes
        leftSide.add(new Button("Refresh", e -> {grid.getDataProvider().refreshAll();}));

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

            // Get or create attendance entry for today
            attendanceEntry = getAttendanceEntry(date);
            updateGrid();

//        FORM_URL = createAttendanceForm(date)
            String formUrl = openAttendanceForm();

//        IMAGE_URL = getQrCode(FORM_URL)
            String qrCodeUrl = getQrCodeUrl(formUrl);

//        MainView.qrcode = Image(IMAGE_URL)
            link.setHref(formUrl);
            link.setTarget("_blank");
            img.setSrc(qrCodeUrl);

        }
//else if BUTTON.text = “Stop”
        else if(text.equals("Stop")){
//        // Stop button pressed
//        closeAttendanceForm()
            closeAttendanceForm();

//        MainView.qrcode = Image(null)
            img.setSrc("images/empty-plant.png");
            link.setHref("#");
            link.setTarget("_self");
//        saveStudentsToDatabase(STUDENT_LIST)
            saveEntryToDatabase(attendanceEntry);
//        MainView.list = “”
            clearGrid();


//        BUTTON.text = “Start”
            button.setText("Start");
            button.removeThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
//        MainView.date = “---”
            dateString.setText("---");
//
//        end if
        }

    }

    private void clearGrid() {
        grid.setItems();
    }

    private AttendanceEntry getAttendanceEntry(String date) {
        AttendanceEntry entry = attendService.getEntryByDate(date);
        if(entry == null){
            System.out.println("New entry created");
            return new AttendanceEntry(date);
        }
        System.out.println("Previous entry accessed");
        return entry;
    }

    private AttendanceEntry saveEntryToDatabase(AttendanceEntry entry) {
        studentService.addStudents(entry.getAttendanceList());
        return attendService.saveEntry(entry);
    }

    private void closeAttendanceForm() {
        RouteConfiguration.forApplicationScope().removeRoute(FormView.class);
    }

    private String getQrCodeUrl(String formUrl) {
        String baseUrl = "https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=";
        return baseUrl + formUrl;
    }

    private String openAttendanceForm() {
//        FORM_PATH = Random(0,10000) // generate a random number
        Random rand = new Random();
        int num = rand.nextInt(10000);
        String formPath = "form" + num;
//        FORM.setRoutePath(FORM_PATH)
        RouteConfiguration.forApplicationScope().setRoute(formPath, FormView.class);
//        return FORM_PATH
        String baseUrl = ((VaadinServletRequest) VaadinService.getCurrentRequest()).getServerName();
        if(baseUrl.equals("localhost")){
            baseUrl += ":8080";
        }
        return "http://" + baseUrl + "/" + formPath; // for production
    }

    @Override
    public void onApplicationEvent(StudentRegEvent event) {
        attendanceEntry.addStudentToAttendance(event.getStudent());
        updateGrid();
    }

    private void updateGrid() {
        grid.setItems(attendanceEntry.getAttendanceList());
        grid.getDataProvider().refreshAll();
    }
}
