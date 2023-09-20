package com.devchallenges.imageuploader.repository;

import com.devchallenges.imageuploader.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByImageKey(String key);

    boolean existsByImageKey(String key);
}
