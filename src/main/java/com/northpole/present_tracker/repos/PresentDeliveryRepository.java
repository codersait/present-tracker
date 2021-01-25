package com.northpole.present_tracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.northpole.present_tracker.domain.PresentDelivery;


public interface PresentDeliveryRepository extends JpaRepository<PresentDelivery, Long> {
    // add custom queries here
}
