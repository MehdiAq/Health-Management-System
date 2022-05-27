package MFD.HealthManagementSystem.model.id;

import java.io.Serializable;
import java.sql.Date;

public class PrescriptionHistoryId implements Serializable {

    private Long patientId;
    private Long medicationId;
    private Date dateOfPrescription;
}
