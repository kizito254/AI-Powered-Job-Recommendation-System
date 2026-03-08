package com.example.jobrecommendation.service;

import com.example.jobrecommendation.dto.JobRecommendationResponse;
import com.example.jobrecommendation.model.Job;
import com.example.jobrecommendation.model.UserProfile;
import com.example.jobrecommendation.repository.JobRepository;
import com.example.jobrecommendation.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final JobRepository jobRepository;
    private final UserProfileRepository userProfileRepository;

    public RecommendationService(JobRepository jobRepository, UserProfileRepository userProfileRepository) {
        this.jobRepository = jobRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public List<JobRecommendationResponse> recommendJobs(Long userId) {
        UserProfile user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found: " + userId));

        return jobRepository.findAll().stream()
                .map(job -> mapToRecommendation(job, user))
                .filter(recommendation -> recommendation.score() > 0)
                .sorted(Comparator.comparingDouble(JobRecommendationResponse::score).reversed())
                .toList();
    }

    private JobRecommendationResponse mapToRecommendation(Job job, UserProfile user) {
        Set<String> matchedSkills = job.getRequiredSkills().stream()
                .filter(skill -> user.getSkills().contains(skill))
                .collect(Collectors.toSet());

        double skillScore = job.getRequiredSkills().isEmpty()
                ? 0
                : (double) matchedSkills.size() / job.getRequiredSkills().size();

        double interestBoost = user.getInterests().stream()
                .anyMatch(interest -> job.getTitle().toLowerCase().contains(interest.toLowerCase())) ? 0.15 : 0.0;

        double score = Math.min(skillScore + interestBoost, 1.0);

        return new JobRecommendationResponse(job.getId(), job.getTitle(), job.getExperienceLevel(), matchedSkills, score);
    }
}
