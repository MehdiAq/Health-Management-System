package MFD.HealthManagementSystem.model;

import MFD.HealthManagementSystem.model.id.MedicalServiceId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
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
    @Temporal(TemporalType.DATE)
    @Column(name = "date_performed")
    private Date serviceDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Doctor doctor;
}