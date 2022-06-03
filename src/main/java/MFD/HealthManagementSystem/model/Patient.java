package MFD.HealthManagementSystem.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;

import static MFD.HealthManagementSystem.constant.ErrorMessage.ADDRESS_IS_REQUIRED_ERROR_MESSAGE;
import static MFD.HealthManagementSystem.constant.ErrorMessage.BIRTH_DATE_IS_REQUIRED_ERROR_MESSAGE;
import static MFD.HealthManagementSystem.constant.ErrorMessage.CARD_EXPIRATION_IS_REQUIRED_ERROR_MESSAGE;
import static MFD.HealthManagementSystem.constant.ErrorMessage.EMAIL_IS_REQUIRED_ERROR_MESSAGE;
import static MFD.HealthManagementSystem.constant.ErrorMessage.FAMILY_DOCTOR_IS_REQUIRED_ERROR_MESSAGE;
import static MFD.HealthManagementSystem.constant.ErrorMessage.FIRST_NAME_IS_REQUIRED_ERROR_MESSAGE;
import static MFD.HealthManagementSystem.constant.ErrorMessage.HEALTH_INSURANCE_NUMBER_IS_REQUIRED_ERROR_MESSAGE;
import static MFD.HealthManagementSystem.constant.ErrorMessage.INVALID_PHONE_NUMBER_ERROR_MESSAGE;
import static MFD.HealthManagementSystem.constant.ErrorMessage.LAST_NAME_IS_REQUIRED_ERROR_MESSAGE;
import static MFD.HealthManagementSystem.constant.ErrorMessage.PHONE_NUMBER_IS_REQUIRED_ERROR_MESSAGE;
import static MFD.HealthManagementSystem.constant.ErrorMessage.VALID_EMAIL_IS_REQUIRED_ERROR_MESSAGE;

@Entity
@Table(name="patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    @Id
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
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