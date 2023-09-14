package com.keiken.ReleafConnector.Repository;

import com.keiken.ReleafConnector.models.ReconstructionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReconstrunctionRepo extends JpaRepository<ReconstructionRequest,Long> {
    List<ReconstructionRequest> findByStatus(String status);
   // ReconstructionRequest findReconstructionById(Long id);

}
