package com.example.stage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class UserController {

    @GetMapping("/api")
    public ResponseEntity<Map<String, Object>> getUserInfo(
            @RequestParam String slack_name,
            @RequestParam String track
    ) {
        Map<String, Object> response = new LinkedHashMap<>();

        // Get the current day of the week
        DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();

        // Get the current UTC time
        LocalDateTime currentUtcTime = LocalDateTime.now(ZoneOffset.UTC);

        // Generate UTC time within +/- 2 minutes of the current time
        long minutesToAdd = ThreadLocalRandom.current().nextLong(-2, 3);
        LocalDateTime generatedUtcTime = currentUtcTime.plusMinutes(minutesToAdd);

        // Ensure the generated time is within +/- 2 minutes
        if (generatedUtcTime.isBefore(currentUtcTime.minusMinutes(2))) {
            generatedUtcTime = currentUtcTime.minusMinutes(2);
        } else if (generatedUtcTime.isAfter(currentUtcTime.plusMinutes(2))) {
            generatedUtcTime = currentUtcTime.plusMinutes(2);
        }

        String githubFile = "https:github.come/nechcodes/HNGx Stage1/StageApplication.java";

        String githubRepoUrl = "https:github.come/nechcodes/HNGx Stage1";


        // Set the HTTP status code to 200 (OK)
        HttpStatus status = HttpStatus.OK;

        // Populate the response map
        response.put("slack_name", slack_name);
        response.put("current_day", dayOfWeek.toString());
        response.put("utc_time", generatedUtcTime.toString());
        response.put("track", track);
        response.put("github_file_url", githubFile);
        response.put("github_repo_url", githubRepoUrl);
        response.put("status_code", status.value());

        return new ResponseEntity<>(response, status);
    }
}