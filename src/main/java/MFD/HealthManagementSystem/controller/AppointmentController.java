package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.ResourceNotFoundException;
import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.repository.AppointmentRepository;
import MFD.HealthManagementSystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.*;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

//    @GetMapping("/patients/{patientInsuranceNumber}/appointments")
//    public Page<Appointment> getAllAppointmentsByInsuranceNumber(@PathVariable(value="patientInsuranceNumber") Long healthInsuranceNumber, Pageable pageable){
//        return appointmentRepository.findByPatient_HealthInsuranceNumber(healthInsuranceNumber, pageable);
//    }
//
//    @PostMapping("/patients/{patientInsuranceNumber}/appointments")
//    public Appointment createAppointment(@PathVariable(value = "patientInsuranceNumber") Long healthInsuranceNumber, @Valid @RequestBody Appointment appointment){
//        return patientRepository.findById(healthInsuranceNumber).map(patient -> {
//            appointment.setPatient(patient);
//            return appointmentRepository.save(appointment);
//        }).orElseThrow(()-> new ResourceNotFoundException("Patient Health Insurance Number " + healthInsuranceNumber + " not found"));
//    }


}