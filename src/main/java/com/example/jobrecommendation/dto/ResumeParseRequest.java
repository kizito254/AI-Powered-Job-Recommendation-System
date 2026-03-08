package com.example.jobrecommendation.dto;

import jakarta.validation.constraints.NotBlank;

public record ResumeParseRequest(@NotBlank String resumeText) {
}
