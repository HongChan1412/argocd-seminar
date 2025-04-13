package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
@RequestMapping("/health")
public class DbHealthController {

    private final DataSource dataSource;

    public DbHealthController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/db")
    public ResponseEntity<String> checkDbConnection() {
        try (Connection conn = dataSource.getConnection()) {
            if (conn.isValid(1)) {
                return ResponseEntity.ok("✅ Database connection is OK");
            } else {
                return ResponseEntity.status(500).body("❌ Database connection failed (not valid)");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("❌ Database connection failed: " + e.getMessage());
        }
    }
}
