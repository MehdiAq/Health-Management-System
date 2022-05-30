package MFD.HealthManagementSystem.model.id;

import MFD.HealthManagementSystem.model.*;

import java.io.*;
import java.util.*;

public class MedicalServiceId implements Serializable {


    private String serviceName;

    private Date serviceDate;



    public MedicalServiceId() {
    }

    public MedicalServiceId(String serviceName, Date serviceDate) {
        this.serviceName = serviceName;
        this.serviceDate = serviceDate;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}