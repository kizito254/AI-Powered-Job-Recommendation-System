package com.example.jobrecommendation.repository;

import com.example.jobrecommendation.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
