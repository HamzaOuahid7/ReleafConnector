package com.keiken.ReleafConnector.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keiken.ReleafConnector.Repository.ReconstrunctionRepo;
import com.keiken.ReleafConnector.models.ReconstructionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private ReconstrunctionRepo reconstructionRequestRepository;

    @PostMapping("/add")
    public ReconstructionRequest createRequest(@RequestBody ReconstructionRequest request) {
        return reconstructionRequestRepository.save(request);
    }

    @GetMapping
    public List<ReconstructionRequest> getAllRequests() {
        return reconstructionRequestRepository.findAll();
    }

    @GetMapping("/valid")
    public List<ReconstructionRequest> getAllRequestsWithStatus(@RequestBody String status) {
        return reconstructionRequestRepository.findByStatus(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReconstructionRequest> getRequestById(@PathVariable Long id) {
        return reconstructionRequestRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody String status) {
        return reconstructionRequestRepository.findById(id)
                .map(request -> {
                    request.setStatus(status);
                    reconstructionRequestRepository.save(request);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/earthquakes")
    public String getEarthquakes() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2023-09-08&endtime=2023-09-09", String.class);
        String earthquakesData = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(earthquakesData);
            JsonNode features = root.path("features");
            for (JsonNode feature : features) {
                JsonNode idNode = feature.path("id");
                if (idNode.isTextual() && idNode.asText().equals("us7000kufc")) {
                    // Found the desired object
                    // You can process it or return it as per your requirement
                    return feature.toString();
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Object with the ID "us7000kufc" not found
        return "Object not found";
    }


}
