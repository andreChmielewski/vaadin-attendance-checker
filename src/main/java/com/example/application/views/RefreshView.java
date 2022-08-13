package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Refresh")
@Route(value = "refresh", layout = MainLayout.class)
public class RefreshView extends VerticalLayout {

    private final List<String> data = new ArrayList<>();

    public RefreshView() {
        Grid<String> grid = new Grid<>();
        grid.addColumn(s -> s);
        grid.setItems(data);

        Button button = new Button("Add data", event -> {
            data.add(System.currentTimeMillis() + " ms");
            grid.getDataProvider().refreshAll();
        });

        add(grid, button);
    }
}
