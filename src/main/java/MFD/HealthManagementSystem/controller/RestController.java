package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorAvailabilityRepository doctorAvailabilityRepository;

    @Autowired
    private MedicalServiceHistoryRepository medicalServiceHistoryRepository;

    @Autowired
    private PrescriptionHistoryRepository prescriptionHistoryRepository;

    private final ObjectMapper mapper = new ObjectMapper();


    @PostMapping("/patients")
    public Patient createPatient(@Valid @RequestBody Patient patient){
        return patientRepository.save(patient);
    }

    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody Doctor doctor){
        return doctorRepository.save(doctor);
    }

    @GetMapping("/patients/{patientInsuranceNumber}/appointments")
    public Page<Appointment> getAllAppointmentsByInsuranceNumber(@PathVariable(value="patientInsuranceNumber") Long healthInsuranceNumber, Pageable pageable){
        return appointmentRepository.findByPatient_HealthInsuranceNumber(healthInsuranceNumber, pageable);
    }

    @PostMapping("/patients/{patientInsuranceNumber}/appointments")
    public Appointment createAppointment(@PathVariable(value = "patientInsuranceNumber") Long healthInsuranceNumber, @Valid @RequestBody Appointment appointment){
        return patientRepository.findById(healthInsuranceNumber).map(patient -> {
            appointment.setPatient(patient);
            return appointmentRepository.save(appointment);
        }).orElseThrow(()-> new ResourceNotFoundException("Patient Health Insurance Number " + healthInsuranceNumber + " not found"));
    }

    @PostMapping("/doctors/{doctor_Id}/createAvailability")
    public DoctorAvailability createDocAvail(@PathVariable(value = "doctor_Id") Long doctorId, @RequestBody DoctorAvailability doctorAvailability) {

        doctorAvailability = mapper.convertValue(doctorAvailability, DoctorAvailability.class);
        doctorAvailability.setDoctor(doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("")));
//        Doctor doctor = mapper.convertValue(doctorAvailability, Doctor.class);
        return doctorAvailabilityRepository.save(doctorAvailability);
    }
//    doctorAvailability = mapper.convertValue(doctorAvailability, DoctorAvailability.class);
//        return doctorRepository.findById(doctorId).map(doctor -> {
//        doctorAvailability.setDoctor(doctor);
//        return doctorAvailabilityRepository.save(doctorAvailability);
//    }).orElseThrow(()-> new ResourceNotFoundException("No Doctor with the ID: " + doctorId));
//}


}