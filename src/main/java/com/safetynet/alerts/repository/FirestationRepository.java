package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Firestation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirestationRepository extends JpaRepository<Firestation, Long> {
}
