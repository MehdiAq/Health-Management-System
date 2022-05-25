package MFD.HealthManagementSystem.model;


import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.*;
import java.sql.*;

@Entity
@Table(name="prescription_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionHistory implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    @JoinColumn(name = "health_insurance_number")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;

    @Id
    @Column(name = "medication_id")
    private Long medicationId;

    @Id
    @Column(name = "date_of_prescription")
    private Date dateOfPrescription;

    @Column(name = "qty_dispensed")
    private Long prescriptionQty;
}