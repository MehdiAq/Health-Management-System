package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

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
    public List<Appointment> getAllAppointmentsByInsuranceNumber(@PathVariable(value="patientInsuranceNumber") Long healthInsuranceNumber){
        return appointmentRepository.findAllByPatient_HealthInsuranceNumber(healthInsuranceNumber);
    }

    @PostMapping("/patients/{patientInsuranceNumber}/{doctorId}/appointments")
    public Appointment createAppointment(@PathVariable(value = "patientInsuranceNumber") Long healthInsuranceNumber,@PathVariable(value = "doctorId") long doctorId, @Valid @RequestBody Appointment appointment){
        appointment.setPatient(patientRepository.findById(healthInsuranceNumber).orElseThrow(()-> new ResourceNotFoundException("Patient Health Insurance Number " + healthInsuranceNumber + " not found")));
        appointment.setDoctor(doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor with ID " + doctorId + " not found")));
        return appointmentRepository.save(appointment);
    }

    @PostMapping("/doctors/{doctor_Id}/createAvailability")
    public DoctorAvailability createDocAvail(@PathVariable(value = "doctor_Id") Long doctorId, @RequestBody DoctorAvailability doctorAvailability) {

        doctorAvailability.setDoctor(doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("")));
        return doctorAvailabilityRepository.save(doctorAvailability);
    }

    @PostMapping("/patients/{insuranceNumber}/prescription/")
    public PrescriptionHistory recordPrescription(@PathVariable(value = "insuranceNumber") long healthInsuranceNumber, @RequestBody PrescriptionHistory prescriptionHistory) {
        return patientRepository.findById(healthInsuranceNumber).map(patient -> {
            prescriptionHistory.setPatient(patient);
            return prescriptionHistoryRepository.save(prescriptionHistory);
        }).orElseThrow(()-> new ResourceNotFoundException("Patient Health Insurance Number " + healthInsuranceNumber + " not found"));
    }

    @PostMapping("/patients/{insuranceNumber}/{doctorId}/service")
    public MedicalServiceHistory recordService(@PathVariable(value = "insuranceNumber") long healthInsuranceNumber,@PathVariable(value = "doctorId") long doctorId, @RequestBody MedicalServiceHistory medicalServiceHistory) {
        medicalServiceHistory.setPatient(patientRepository.findById(healthInsuranceNumber).orElseThrow(()-> new ResourceNotFoundException("Patient Health Insurance Number " + healthInsuranceNumber + " not found")));
        medicalServiceHistory.setDoctor(doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor with ID " + doctorId + " not found")));
        return medicalServiceHistoryRepository.save(medicalServiceHistory);
    }
}