package MFD.HealthManagementSystem.model;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.*;
import java.util.*;

@Entity
@Table(name="doctor_availability")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAvailability implements Serializable {
    @Id
    @Column(name = "date_available")
    private Date dateAvailable;

    @ManyToOne
    @Id
    @JoinColumn(name = "doctor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Doctor doctor;

    @Id
    @Column(name = "time_slot")
    private Date timeSlot;

    @Column(name = "is_available")
    private boolean isAvailable;
}