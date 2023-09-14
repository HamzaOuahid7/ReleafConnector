package com.keiken.ReleafConnector.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reconstruction_requests")
public class ReconstructionRequest {
    @Id
    private Long reconstructionId;
    private List<Blob> damagePhotos;
    private String damageDescription;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "citizen_id", referencedColumnName = "citizenId")
    private Citizen citizen;
    private String status;
    @OneToMany(mappedBy = "reconstructionRequest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Donation> donations;

}
