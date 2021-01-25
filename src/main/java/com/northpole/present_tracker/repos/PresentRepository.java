package com.northpole.present_tracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.northpole.present_tracker.domain.Present;


public interface PresentRepository extends JpaRepository<Present, Long> {
    // add custom queries here
}
