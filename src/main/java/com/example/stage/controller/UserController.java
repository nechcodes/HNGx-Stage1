package com.example.stage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

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

                // Create a random number generator
                Random random = new Random();

                // Generate a random number between -2 and 2
                int randomMinutes = random.nextInt(5) - 2;

                // Get the current UTC time
                Instant currentUTC = Instant.now().truncatedTo(ChronoUnit.MINUTES);

                // Add the random number of minutes
                Instant adjustedUTC = currentUTC.plus(randomMinutes, ChronoUnit.MINUTES);

        //Set the GitHub file url
        String githubFile = "https://github.com/nechcodes/HNGx-Stage1/blob/master/src/main/java/com/example/stage/StageApplication.java";

        //Set the GitHub repository url
        String githubRepoUrl = "https://github.com/nechcodes/HNGx-Stage1/tree/master";


        // Set the HTTP status code to 200 (OK)
        HttpStatus status = HttpStatus.OK;

        // Populate the response map
        response.put("slack_name", slack_name);
        response.put("current_day", dayOfWeek.toString());
        response.put("utc_time", adjustedUTC.toString());
        response.put("track", track);
        response.put("github_file_url", githubFile);
        response.put("github_repo_url", githubRepoUrl);
        response.put("status_code", status.value());

        return new ResponseEntity<>(response, status);
    }
}