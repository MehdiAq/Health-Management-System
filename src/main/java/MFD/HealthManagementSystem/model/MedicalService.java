package MFD.HealthManagementSystem.model;

import MFD.HealthManagementSystem.model.id.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@IdClass(MedicalServiceId.class)
@Table(name="medical_service_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalService {
    @ManyToOne
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

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Doctor doctor;
}