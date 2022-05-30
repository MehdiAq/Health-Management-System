package MFD.HealthManagementSystem.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.*;

import static MFD.HealthManagementSystem.constant.ErrorMessage.*;

@Entity
@Table(name="patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = HEALTH_INSURANCE_NUMBER_IS_REQUIRED_ERROR_MESSAGE)
    @Column(name = "health_insurance_number")
    @Positive
    private Long id;

    @NotNull
    @Column(name = "first_name")
    @NotEmpty(message = FIRST_NAME_IS_REQUIRED_ERROR_MESSAGE)
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    @NotEmpty(message = LAST_NAME_IS_REQUIRED_ERROR_MESSAGE)
    private String lastName;

    @NotNull
    @Column(name = "health_card_expiration_date")
    @NotEmpty(message = CARD_EXPIRATION_IS_REQUIRED_ERROR_MESSAGE)
    private String cardExpiration;

    @NotNull
    @Column(name = "address")
    @NotEmpty(message = ADDRESS_IS_REQUIRED_ERROR_MESSAGE)
    private String address;

    @NotNull(message = BIRTH_DATE_IS_REQUIRED_ERROR_MESSAGE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "email")
    @NotEmpty(message = EMAIL_IS_REQUIRED_ERROR_MESSAGE)
    @Email(regexp = "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$", message = VALID_EMAIL_IS_REQUIRED_ERROR_MESSAGE)
    private String email;

    @Column(name = "phone_number")
    @NotNull(message = PHONE_NUMBER_IS_REQUIRED_ERROR_MESSAGE)
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$", message = INVALID_PHONE_NUMBER_ERROR_MESSAGE)
    private String phoneNumber;

    @Column(name = "family_doctor")
    @NotEmpty(message = FAMILY_DOCTOR_IS_REQUIRED_ERROR_MESSAGE)
    private String familyDoctor;

}