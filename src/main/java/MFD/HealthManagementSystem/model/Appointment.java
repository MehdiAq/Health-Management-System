package MFD.HealthManagementSystem.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="appointments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @Column(name = "appointment_id")
    private Long appointmentId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "date_of_appointment")
    @JsonFormat(pattern="dd MMMM yyyy")
    private Date appointmentDate;

    @Column(name = "time_of_appointment")
    @JsonFormat(pattern="HH:mm")
    private LocalTime appointmentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_insurance_number")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;
}
