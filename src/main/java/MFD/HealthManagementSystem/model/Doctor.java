package MFD.HealthManagementSystem.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="doctors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "specialty")
    private String specialty;
}