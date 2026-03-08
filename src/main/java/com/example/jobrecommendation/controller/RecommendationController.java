package com.example.jobrecommendation.controller;

import com.example.jobrecommendation.dto.JobRecommendationResponse;
import com.example.jobrecommendation.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<JobRecommendationResponse>> getRecommendations(@PathVariable Long userId) {
        return ResponseEntity.ok(recommendationService.recommendJobs(userId));
    }
}
