
package MFD.HealthManagementSystem.model;

import MFD.HealthManagementSystem.model.id.DoctorAvailabilityId;
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
public class DoctorAvailability{

    @EmbeddedId
    private DoctorAvailabilityId id;

    @ManyToOne
    @MapsId("doctor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Doctor doctor;

    @Column(name = "is_available")
    private boolean isAvailable;
}