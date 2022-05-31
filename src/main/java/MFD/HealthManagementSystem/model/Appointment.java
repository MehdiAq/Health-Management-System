package MFD.HealthManagementSystem.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="appointments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Appointment {

    @Id
    @Column(name = "appointment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Doctor doctor;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "dd MMMM yyyy")
//    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_appointment")
    private Date appointmentDate;

    @Column(name = "time_of_appointment")
    private String timeSlot;

    @Column(name = "procedure_name")
    private String procedure;

    @ManyToOne
    @JoinColumn(name = "health_insurance_number")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;

}