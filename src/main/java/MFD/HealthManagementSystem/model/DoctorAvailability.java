package MFD.HealthManagementSystem.model;

import MFD.HealthManagementSystem.model.id.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

@Entity
@IdClass(DoctorAvailabilityId.class)
@Table(name="doctor_availability")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAvailability {
    @ManyToOne
    @Id
    @JoinColumn(name = "doctor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Doctor doctor;

    @Id
    @Column(name = "date_available")
    private Date dateAvailable;

    @Id
    @Column(name = "time_slot")
    private Date timeSlot;

    @Column(name = "is_available")
    private boolean isAvailable;
}