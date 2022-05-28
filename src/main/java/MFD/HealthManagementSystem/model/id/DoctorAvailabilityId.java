package MFD.HealthManagementSystem.model.id;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class DoctorAvailabilityId implements Serializable {

    private Long doctor_id;
    @JsonFormat(pattern="dd MMMM yyyy")
    private Date date_available;
    @JsonFormat(pattern="HH:mm")
    private Date time_slot;
}
