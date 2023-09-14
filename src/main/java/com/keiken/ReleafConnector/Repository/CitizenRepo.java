package com.keiken.ReleafConnector.Repository;

import com.keiken.ReleafConnector.models.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepo extends JpaRepository<Citizen,Long> {
}
