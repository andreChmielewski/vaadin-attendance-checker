package com.example.application.repositories;

import com.example.application.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {


}
