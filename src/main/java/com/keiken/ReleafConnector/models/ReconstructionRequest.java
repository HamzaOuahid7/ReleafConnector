package com.keiken.ReleafConnector.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reconstruction_requests")
public class ReconstructionRequest {
    @Id
    private Long reconstructionId;
    private String damageDescription;
    private String citizenFullName;
    private String cin;
    private Date birthday;
    private String address;
    private String status;
    @OneToMany(mappedBy = "reconstructionRequest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Donation> donations;

}
