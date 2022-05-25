package MFD.HealthManagementSystem.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

import static MFD.HealthManagementSystem.constant.ErrorMessage.*;

@Entity
@Table(name = "patient")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class Patient {

    @Id
    @Positive
    @Column(name = "health_insurance_number")
    private Long id;

    @NotEmpty(message = FIRST_NAME_IS_REQUIRED_ERROR_MESSAGE)
    @Size(min=2, message = NAME_SIZE_ERROR_MESSAGE)
    private String first_name;

    @NotEmpty(message = LAST_NAME_IS_REQUIRED_ERROR_MESSAGE)
    @Size(min=2, message = NAME_SIZE_ERROR_MESSAGE)
    private String last_name;

    @NotNull
    @Column(name = "address")
    @NotEmpty(message = ADDRESS_IS_REQUIRED_ERROR_MESSAGE)
    private String address;

    @NotNull
    //@NotEmpty(message = CARD_EXPIRATION_IS_REQUIRED_ERROR_MESSAGE)
    private Date health_card_expiration_date;

    //@NotEmpty(message = BIRTH_DATE_IS_REQUIRED_ERROR_MESSAGE)
    private Date year_of_birth;

    //@NotEmpty(message = EMAIL_IS_REQUIRED_ERROR_MESSAGE)
    //@Pattern(regexp = "^[a-zA-Z\\d+_.-]+@[a-zA-Z\\d.-]+$", message = "please provide a valid email")
    private String email;

    private String cellphone;

    private String family_doctor;
}
