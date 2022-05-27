package MFD.HealthManagementSystem.model;

import MFD.HealthManagementSystem.model.id.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.*;
import java.sql.*;

@Entity
@IdClass(MedicalServiceHistoryId.class)
@Table(name="medical_service_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalServiceHistory {
    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    @JoinColumn(name = "health_insurance_number")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;

    @Id
    @Column(name = "service_name")
    private String serviceName;

    @Id
    @Column(name = "date_performed")
    private Date serviceDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Doctor doctor;
}