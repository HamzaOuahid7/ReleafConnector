package com.keiken.ReleafConnector.Controller;
import com.keiken.ReleafConnector.Repository.ReconstrunctionRepo;
import com.keiken.ReleafConnector.models.ReconstructionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reconstruction-requests")
public class RequestController {

    @Autowired
    private ReconstrunctionRepo reconstructionRequestRepository;

    @PostMapping
    public ReconstructionRequest createRequest(@RequestBody ReconstructionRequest request) {
        return reconstructionRequestRepository.save(request);
    }

    @GetMapping
    public List<ReconstructionRequest> getAllRequests() {
        return reconstructionRequestRepository.findAll();
    }

    @GetMapping("/status/{status}")
    public List<ReconstructionRequest> getAllRequestsWithStatus(@PathVariable String status) {
        return reconstructionRequestRepository.findByStatus(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReconstructionRequest> getRequestById(@PathVariable Long id) {
        return reconstructionRequestRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<ReconstructionRequest> updateStatus(@PathVariable Long id, @PathVariable String status) {
        return reconstructionRequestRepository.findById(id)
                .map(request -> {
                    request.setStatus(status);
                    reconstructionRequestRepository.save(request);
                    return ResponseEntity.ok(request);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
