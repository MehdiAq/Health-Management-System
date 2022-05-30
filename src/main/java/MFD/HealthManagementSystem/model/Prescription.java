package MFD.HealthManagementSystem.model;


import MFD.HealthManagementSystem.model.id.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@IdClass(PrescriptionId.class)
@Table(name="prescription_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    @ManyToOne
    @Id
    @JoinColumn(name = "health_insurance_number")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;

    @Id
    @Column(name = "medication_name")
    private String medicationName;

    @Id
    @Column(name = "date_of_prescription")
    private Date dateOfPrescription;

    @Column(name = "qty_dispensed")
    private Long prescriptionQty;
}