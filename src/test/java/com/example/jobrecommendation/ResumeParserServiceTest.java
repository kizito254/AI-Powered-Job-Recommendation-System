package com.example.jobrecommendation;

import com.example.jobrecommendation.dto.ResumeParseResponse;
import com.example.jobrecommendation.service.ResumeParserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResumeParserServiceTest {

    private final ResumeParserService service = new ResumeParserService();

    @Test
    void shouldExtractSkillsAndExperience() {
        ResumeParseResponse response = service.parse("Java and Spring Boot engineer with 5 years experience in MySQL");

        assertTrue(response.extractedSkills().contains("java"));
        assertTrue(response.extractedSkills().contains("spring boot"));
        assertTrue(response.extractedSkills().contains("mysql"));
        assertEquals(5, response.estimatedYearsOfExperience());
    }
}
