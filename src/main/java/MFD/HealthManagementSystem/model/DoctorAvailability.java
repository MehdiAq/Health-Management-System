//package MFD.HealthManagementSystem.model;
//
//import MFD.HealthManagementSystem.model.id.*;
//import com.fasterxml.jackson.annotation.*;
//import lombok.*;
//import org.hibernate.annotations.*;
//import org.springframework.format.annotation.*;
//
//import javax.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import java.io.*;
//import java.util.*;
//
//@Entity
//@IdClass(DoctorAvailabilityId.class)
//@Table(name="doctor_availability")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class DoctorAvailability implements Serializable {
//
//    @Id
//    @Column(name = "date_available")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "dd-MM-yyyy")
//    private Date dateAvailable;
//
//    @Id
//    @Column(name = "time_slot")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "dd-MM-yyyy")
//    private Date timeSlot;
//
//    @Column(name = "is_available")
//    private boolean isAvailable;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @Id
//    @JoinColumn(name = "doctor_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Doctor doctor;
//}