package com.example.jobrecommendation.dto;

import java.util.Set;

public record JobRecommendationResponse(Long jobId, String title, String experienceLevel,
                                        Set<String> matchedSkills, double score) {
}
