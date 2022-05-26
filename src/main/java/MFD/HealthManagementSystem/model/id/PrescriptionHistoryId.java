package MFD.HealthManagementSystem.model.id;

import MFD.HealthManagementSystem.model.*;

import java.io.*;
import java.sql.*;

public class PrescriptionHistoryId implements Serializable {
    private Patient patient;

    private Long medicationId;

    private Date dateOfPrescription;

    private Long prescriptionQty;

    public PrescriptionHistoryId() {
    }

    public PrescriptionHistoryId(Patient patient, Long medicationId, Date dateOfPrescription, Long prescriptionQty) {
        this.patient = patient;
        this.medicationId = medicationId;
        this.dateOfPrescription = dateOfPrescription;
        this.prescriptionQty = prescriptionQty;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}