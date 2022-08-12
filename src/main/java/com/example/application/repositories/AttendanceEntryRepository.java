package com.example.application.repositories;

import com.example.application.data.AttendanceEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceEntryRepository extends JpaRepository<AttendanceEntry, String> {

    AttendanceEntry findByDate(String date);

}
