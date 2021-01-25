package com.northpole.present_tracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.northpole.present_tracker.domain.Child;


public interface ChildRepository extends JpaRepository<Child, Long> {
    // add custom queries here
}
