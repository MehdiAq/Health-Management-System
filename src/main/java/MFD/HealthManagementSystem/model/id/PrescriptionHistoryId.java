package MFD.HealthManagementSystem.model.id;

import MFD.HealthManagementSystem.model.*;

import java.io.*;
import java.sql.*;

public class PrescriptionHistoryId implements Serializable {

    private String medicationName;

    private Date dateOfPrescription;

    private Long prescriptionQty;

    public PrescriptionHistoryId() {
    }

    public PrescriptionHistoryId(String medicationName, Date dateOfPrescription, Long prescriptionQty) {
        this.medicationName = medicationName;
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