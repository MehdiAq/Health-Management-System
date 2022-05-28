package MFD.HealthManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.util.Date;

import static MFD.HealthManagementSystem.constant.ErrorMessage.*;

@Entity
@Table(name="patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @NotNull(message = HEALTH_INSURANCE_NUMBER_IS_REQUIRED_ERROR_MESSAGE)
    @Column(name = "health_insurance_number")
    @Positive
    private Long id;

//    @NotEmpty(message = PASSWORD_REQUIRED_ERROR_MESSAGE)
//    private String password;

    @NotEmpty(message = FIRST_NAME_IS_REQUIRED_ERROR_MESSAGE)
    @Size(min=2, message = NAME_SIZE_ERROR_MESSAGE)
    private String first_name;

    @NotEmpty(message = LAST_NAME_IS_REQUIRED_ERROR_MESSAGE)
    @Size(min=2, message = NAME_SIZE_ERROR_MESSAGE)
    private String last_name;

    @NotEmpty(message = ADDRESS_IS_REQUIRED_ERROR_MESSAGE)
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="dd MMMM yyyy")
    @NotNull(message = CARD_EXPIRATION_IS_REQUIRED_ERROR_MESSAGE)
    private Date health_card_expiration_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="dd MMMM yyyy")
    @NotNull(message = BIRTH_DATE_IS_REQUIRED_ERROR_MESSAGE)
    private Date year_of_birth;

    @NotEmpty(message = EMAIL_IS_REQUIRED_ERROR_MESSAGE)
    @Pattern(regexp = "^[a-zA-Z\\d+_.-]+@[a-zA-Z\\d.-]+$", message = "please provide a valid email")
    private String email;

    private String cellphone;

    private String family_doctor;
}
