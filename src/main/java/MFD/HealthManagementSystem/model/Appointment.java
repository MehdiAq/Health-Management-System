package MFD.HealthManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @NotNull(message = "please choose procedure")
    @Column(name = "procedure_name")
    private String procedure;

    @NotNull(message = "please choose doctor")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Doctor doctor;

    @NotNull(message = "please choose date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="dd MMMM yyyy")
    @Column(name = "date_of_appointment")
    private Date appointmentDate;

    @NotNull(message = "please choose time slot")
    @Column(name = "time_of_appointment")
    private String timeSlots;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "health_insurance_number")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;
}
