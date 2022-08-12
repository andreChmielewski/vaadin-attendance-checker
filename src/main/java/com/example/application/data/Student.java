package com.example.application.data;

import javax.annotation.Nonnull;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Embeddable
public class Student {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Nonnull
    private String name;

    private boolean isNew;

    public Student(String name){
        this.name = name;
        this.isNew = false;
    }

    public Student(String name, boolean isNew){
        this.name = name;
        this.isNew = isNew;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
