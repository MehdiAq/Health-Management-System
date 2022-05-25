package MFD.HealthManagementSystem.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @Column(name = "appointment_id")
    private String id;

    @NotNull
    @Column(name = "date_of_appointment")
    private Date appointment_date;

    @NotNull
    @Column(name = "time_of_appointment")
    private Date appointment_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_insurance_number")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;
}
