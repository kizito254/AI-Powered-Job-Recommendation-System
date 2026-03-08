package com.example.jobrecommendation.config;

import com.example.jobrecommendation.model.Job;
import com.example.jobrecommendation.model.UserProfile;
import com.example.jobrecommendation.repository.JobRepository;
import com.example.jobrecommendation.repository.UserProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seed(JobRepository jobRepository, UserProfileRepository userProfileRepository) {
        return args -> {
            if (jobRepository.count() == 0) {
                Job backend = new Job();
                backend.setTitle("Java Backend Engineer");
                backend.setDescription("Build APIs with Spring Boot and MySQL.");
                backend.setExperienceLevel("Mid");
                backend.setRequiredSkills(Set.of("java", "spring boot", "mysql", "sql"));

                Job ml = new Job();
                ml.setTitle("Machine Learning Engineer");
                ml.setDescription("Develop recommendation models and NLP pipelines.");
                ml.setExperienceLevel("Mid-Senior");
                ml.setRequiredSkills(Set.of("python", "machine learning", "nlp", "elasticsearch"));

                jobRepository.save(backend);
                jobRepository.save(ml);
            }

            if (userProfileRepository.count() == 0) {
                UserProfile profile = new UserProfile();
                profile.setName("Demo User");
                profile.setYearsOfExperience(4);
                profile.setSkills(Set.of("java", "spring boot", "sql", "elasticsearch"));
                profile.setInterests(Set.of("backend", "machine learning"));
                userProfileRepository.save(profile);
            }
        };
    }
}
