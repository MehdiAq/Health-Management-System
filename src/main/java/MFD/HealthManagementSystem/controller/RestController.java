package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.ResourceNotFoundException;
import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.Doctor;
import MFD.HealthManagementSystem.model.MedicalService;
import MFD.HealthManagementSystem.model.Patient;
import MFD.HealthManagementSystem.model.Prescription;
import MFD.HealthManagementSystem.repository.AppointmentRepository;
import MFD.HealthManagementSystem.repository.DoctorRepository;
import MFD.HealthManagementSystem.repository.MedicalServiceRepository;
import MFD.HealthManagementSystem.repository.PatientRepository;
import MFD.HealthManagementSystem.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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