package com.example.application.views;


import com.example.application.data.Student;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
public class HomeView extends HorizontalLayout {

    private Button button = new Button("Start");
    private String dateString = "---";
    private Image img = new Image("images/empty-plant.png", "placeholder plant");
    private Grid<Student> grid = new Grid<>(Student.class, false);

    public HomeView() {
        VerticalLayout leftSide = new VerticalLayout();
        VerticalLayout rightSide = new VerticalLayout();
        add(leftSide, rightSide);

        // Left side
        leftSide.add(button);
        leftSide.add(new H2("Present students"));
        grid.setItems(new Student("Dave"));
        grid.addColumn(Student::getName).setHeader("Name");
        leftSide.add(grid);
        leftSide.setDefaultHorizontalComponentAlignment(Alignment.CENTER);


        // Right side
        rightSide.add(new H1("Astro Club Meeting"));
        rightSide.add(dateString);
        img.setWidth("200px");
        rightSide.add(img);
        rightSide.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

//        setSpacing(false);
//        setSizeFull();
//        setJustifyContentMode(JustifyContentMode.CENTER);
//        setDefaultVerticalComponentAlignment(Alignment.CENTER);
//        getStyle().set("text-align", "center");
    }

}
