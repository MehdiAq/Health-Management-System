package MFD.HealthManagementSystem.model.id;

import MFD.HealthManagementSystem.model.*;

import java.io.*;
import java.sql.*;

public class MedicalServiceHistoryId implements Serializable {
    private Patient patient;

    private String serviceName;

    private Date date;

    private Doctor doctor;

    public MedicalServiceHistoryId() {
    }

    public MedicalServiceHistoryId(Patient patient, String serviceName, Date date, Doctor doctor) {
        this.patient = patient;
        this.serviceName = serviceName;
        this.date = date;
        this.doctor = doctor;
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