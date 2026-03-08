package com.example.jobrecommendation.dto;

import java.util.Set;

public record ResumeParseResponse(Set<String> extractedSkills, int estimatedYearsOfExperience) {
}
