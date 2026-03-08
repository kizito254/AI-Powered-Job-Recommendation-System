package com.example.jobrecommendation.controller;

import com.example.jobrecommendation.dto.ResumeParseRequest;
import com.example.jobrecommendation.dto.ResumeParseResponse;
import com.example.jobrecommendation.service.ResumeParserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeParserService resumeParserService;

    public ResumeController(ResumeParserService resumeParserService) {
        this.resumeParserService = resumeParserService;
    }

    @PostMapping("/parse")
    public ResponseEntity<ResumeParseResponse> parseResume(@Valid @RequestBody ResumeParseRequest request) {
        return ResponseEntity.ok(resumeParserService.parse(request.resumeText()));
    }
}
