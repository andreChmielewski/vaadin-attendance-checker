package com.example.application.services;

import com.example.application.data.AttendanceEntry;
import com.example.application.repositories.AttendanceEntryRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendanceEntryService {

    private AttendanceEntryRepository attendEntryRepo;

    public AttendanceEntryService(AttendanceEntryRepository attendEntryRepo) {
        this.attendEntryRepo = attendEntryRepo;
    }

    public AttendanceEntry getEntryByDate(String date) {
        return attendEntryRepo.findByDate(date);
    }

    public AttendanceEntry saveEntry(AttendanceEntry entry) {
        return attendEntryRepo.save(entry);
    }
}
