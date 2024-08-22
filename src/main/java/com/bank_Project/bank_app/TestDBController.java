package com.bank_Project.bank_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.bank_Project.bank_app.repository.userRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestDBController {

    @Autowired
    private userRepository userRepository;

    @GetMapping("/check")
    public ResponseEntity<String> checkConnection() {
        // Perform a simple database operation
        long count = userRepository.count();
        return ResponseEntity.ok("Database connection is working. Record count: " + count);
    }
}
