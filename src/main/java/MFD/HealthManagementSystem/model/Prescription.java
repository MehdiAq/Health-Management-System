package MFD.HealthManagementSystem.model;


import MFD.HealthManagementSystem.model.id.PrescriptionId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_prescription")
    private Date dateOfPrescription;

    @Column(name = "qty_dispensed")
    private Long prescriptionQty;
}