package com.keiken.ReleafConnector.Repository;

import com.keiken.ReleafConnector.models.ReconstructionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReconstrunctionRepo extends JpaRepository<ReconstructionRequest,Long> {
    List<ReconstructionRequest> findByStatus(String status);
}
