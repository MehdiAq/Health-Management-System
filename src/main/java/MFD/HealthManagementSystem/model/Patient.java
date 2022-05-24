package MFD.HealthManagementSystem.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name="patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_insurance_number")
    private Long healthInsuranceNumber;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "health_card_expiration_date")
    private String cardExpiration;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "birth_date")
    private Date birthDate;

    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "family_doctor")
    private String familyDoctor;

//    @OneToMany
//    @JoinColumn(name = "health_insurance_number", nullable = false)
//    private List<Appointment> appointments;
}