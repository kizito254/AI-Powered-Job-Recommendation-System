package com.example.jobrecommendation.service;

import com.example.jobrecommendation.dto.ResumeParseResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ResumeParserService {

    private static final Set<String> KNOWN_SKILLS = Set.of(
            "java", "spring", "spring boot", "python", "sql", "mysql", "elasticsearch",
            "docker", "kubernetes", "aws", "machine learning", "nlp", "data analysis", "react"
    );

    public ResumeParseResponse parse(String resumeText) {
        String normalized = resumeText.toLowerCase(Locale.ROOT);

        Set<String> extracted = KNOWN_SKILLS.stream()
                .filter(normalized::contains)
                .collect(Collectors.toCollection(HashSet::new));

        extracted.addAll(Arrays.stream(normalized.split("[^a-z0-9+#.]"))
                .filter(token -> token.length() > 2 && KNOWN_SKILLS.contains(token))
                .collect(Collectors.toSet()));

        int years = estimateYearsOfExperience(normalized);
        return new ResumeParseResponse(extracted, years);
    }

    private int estimateYearsOfExperience(String normalizedText) {
        Pattern pattern = Pattern.compile("(\\d{1,2})\\+?\\s+years");
        Matcher matcher = pattern.matcher(normalizedText);
        int max = 0;
        while (matcher.find()) {
            max = Math.max(max, Integer.parseInt(matcher.group(1)));
        }
        return max;
    }
}
