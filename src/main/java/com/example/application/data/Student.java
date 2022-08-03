package com.example.application.data;

import javax.annotation.Nonnull;

public class Student {

    @Nonnull
    private String name;

    public Student(String name){
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

}
