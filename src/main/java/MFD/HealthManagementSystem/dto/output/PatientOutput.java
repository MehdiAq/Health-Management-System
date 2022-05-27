package MFD.HealthManagementSystem.dto.output;

import lombok.*;

import javax.validation.constraints.Positive;
import java.util.Date;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class PatientOutput {

    @Positive
    private Long health_insurance_number;
    private String first_name;
    private String last_name;
    private String address;
    private Date health_card_expiration_date;
    private Date year_of_birth;
    private String email;
    private String cellphone;
    private String family_doctor;
}
