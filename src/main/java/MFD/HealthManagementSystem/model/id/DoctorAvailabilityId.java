package MFD.HealthManagementSystem.model.id;

import MFD.HealthManagementSystem.model.*;

import java.io.*;
import java.util.Date;

public class DoctorAvailabilityId implements Serializable {
    private Doctor doctor;

    private Date dateAvailable;

    private Date timeSlot;

    private boolean isAvailable;

    public DoctorAvailabilityId() {
    }

    public DoctorAvailabilityId(Doctor doctor, Date dateAvailable, Date timeSlot, boolean isAvailable) {
        this.doctor = doctor;
        this.dateAvailable = dateAvailable;
        this.timeSlot = timeSlot;
        this.isAvailable = isAvailable;
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