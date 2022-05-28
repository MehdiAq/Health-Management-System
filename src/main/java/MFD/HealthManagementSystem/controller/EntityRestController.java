package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.ResourceNotFoundException;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
public class EntityRestController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorAvailabilityRepository doctorAvailabilityRepository;

    @PostMapping("/patient")
    public Patient createPatient(@Valid @RequestBody Patient patient){
        return patientRepository.save(patient);
    }

    @PostMapping("/doctor")
    public Doctor createDoctor(@Valid @RequestBody Doctor doctor){
        return doctorRepository.save(doctor);
    }

    @PostMapping("/patients/{patientId}/{doctorId}/appointments")
    public Appointment createAppointment(@PathVariable(value = "patientId") Long healthInsuranceNumber,@PathVariable(value = "doctorId") long doctorId, @Valid @RequestBody Appointment appointment){
//        return patientRepository.findById(healthInsuranceNumber).map(patient -> {
//            appointment.setPatient(patient);
//            return appointmentRepository.save(appointment);
//        }).orElseThrow(()-> new ResourceNotFoundException("Patient Health Insurance Number " + healthInsuranceNumber + " not found"));
        appointment.setPatient(patientRepository.findById(healthInsuranceNumber).orElseThrow(()-> new ResourceNotFoundException("Patient Health Insurance Number " + healthInsuranceNumber + " not found")));
        appointment.setDoctor(doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor with ID " + doctorId + " not found")));
        return appointmentRepository.save(appointment);
    }

    @PostMapping("/doctorAvailability")
    public DoctorAvailability createDoctorAvailability(@Valid @RequestBody DoctorAvailability doctorAvailability){
        return doctorAvailabilityRepository.save(doctorAvailability);
    }

}
