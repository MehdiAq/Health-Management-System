package MFD.HealthManagementSystem.model.id;

import MFD.HealthManagementSystem.model.Patient;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

public class MedicalServiceHistoryId implements Serializable {

    private Long patientId;
    private String serviceName;
    private Date date;
}
