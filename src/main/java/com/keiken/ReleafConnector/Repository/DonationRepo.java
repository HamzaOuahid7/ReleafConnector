package com.keiken.ReleafConnector.Repository;

import com.keiken.ReleafConnector.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepo extends JpaRepository<Donation,Long> {
}
