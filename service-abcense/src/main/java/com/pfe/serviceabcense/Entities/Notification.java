package com.pfe.serviceabcense.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pfe.serviceabcense.Model.Employee;
import com.pfe.serviceabcense.enume.NotificationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long employeeId;
    private String message;
    private LocalDateTime date;
    private LocalDateTime readat;
        private LocalDateTime openat;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "demande")
    @JsonIgnoreProperties("notifications")
    private demandeAbssence demande;
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
    private long taskId;

}
