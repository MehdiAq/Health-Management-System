package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicalServiceRepository medicalServiceRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

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
        return appointmentRepository.findAllByPatient_Id(healthInsuranceNumber);
    }

    @PostMapping("/patients/{patientInsuranceNumber}/{doctorId}/appointments")
    public Appointment createAppointment(@PathVariable(value = "patientInsuranceNumber") Long healthInsuranceNumber,@PathVariable(value = "doctorId") Long doctorId, @Valid @RequestBody Appointment appointment){
        appointment.setPatient(patientRepository.findById(healthInsuranceNumber).orElseThrow(()-> new ResourceNotFoundException("Patient Health Insurance Number " + healthInsuranceNumber + " not found")));
        appointment.setDoctor(doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor with ID " + doctorId + " not found")));
        return appointmentRepository.save(appointment);
    }

//    @PostMapping("/doctors/{doctor_Id}/createAvailability")
//    public DoctorAvailability createDocAvail(@PathVariable(value = "doctor_Id") Long doctorId, @RequestBody DoctorAvailability doctorAvailability) {
//
//        doctorAvailability.setDoctor(doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("")));
//        return doctorAvailabilityRepository.save(doctorAvailability);
//    }

    @PostMapping("/patients/{insuranceNumber}/prescription/")
    public Prescription recordPrescription(@PathVariable(value = "insuranceNumber") Long healthInsuranceNumber, @RequestBody Prescription prescription) {
        return patientRepository.findById(healthInsuranceNumber).map(patient -> {
            prescription.setPatient(patient);
            return prescriptionRepository.save(prescription);
        }).orElseThrow(()-> new ResourceNotFoundException("Patient Health Insurance Number " + healthInsuranceNumber + " not found"));
    }

    @PostMapping("/patients/{insuranceNumber}/{doctorId}/service")
    public MedicalService recordService(@PathVariable(value = "insuranceNumber") Long healthInsuranceNumber, @PathVariable(value = "doctorId") Long doctorId, @RequestBody MedicalService medicalService) {
        medicalService.setPatient(patientRepository.findById(healthInsuranceNumber).orElseThrow(()-> new ResourceNotFoundException("Patient Health Insurance Number " + healthInsuranceNumber + " not found")));
        medicalService.setDoctor(doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor with ID " + doctorId + " not found")));
        return medicalServiceRepository.save(medicalService);
    }
}