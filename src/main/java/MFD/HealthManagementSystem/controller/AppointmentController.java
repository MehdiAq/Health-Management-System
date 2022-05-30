package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.repository.*;
import MFD.HealthManagementSystem.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@Controller
public class AppointmentController {

    private final DoctorRepository doctorRepository;

    private final AppointmentService appointmentService;
    
    private final PatientService patientService;

    public AppointmentController(DoctorRepository doctorRepository, AppointmentService appointmentService, PatientService patientService) {
        this.doctorRepository = doctorRepository;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    @GetMapping("/appointments/list")
    public String viewAppointments(Model model){
        List<Appointment> appointmentsList = appointmentService.getAppointmentList();
        model.addAttribute("appointments", appointmentsList);
        return "/index";
    }

    @GetMapping("/appointments/{doctorId}/list")
    public String viewDoctorAppointments(@PathVariable(value = "doctorId")Long id, Model model){
        List<Appointment> appointmentsList = appointmentService.getDoctorAppointments(id);
        model.addAttribute("appointments", appointmentsList);
        return "/index";
    }

    @GetMapping("/appointments/{insuranceNumber}/list")
    public String viewPatientAppointments(@PathVariable(value = "insuranceNumber")Long id, Model model){
        List<Appointment> appointmentsList = appointmentService.getPatientAppointments(id);
        model.addAttribute("appointments", appointmentsList);
        return "/index";
    }

//    @GetMapping("/appointments/{date}/list")
//    public String viewPatientAppointments(@PathVariable(value = "date") Date date, Model model){
//        List<Appointment> appointmentsList = appointmentService.getAppointmentListByDate(date);
//        model.addAttribute("appointments", appointmentsList);
//        return "appointments-list";
//    }

    @GetMapping("/newAppointments/{id}")
    public String addNewAppointment(@PathVariable(value = "id")Long id, Model model) throws RecordNotFoundException {
        Appointment appointment = new Appointment();
        Patient dbPatient = patientService.getPatientById(id);
        appointment.setPatient(dbPatient);
        List<Doctor> allDoctors = doctorRepository.findAll();
//        model.addAttribute("patient", dbPatient);
        model.addAttribute("allDoctors", allDoctors);
        model.addAttribute("appointment", appointment);
        return "new-appointment";
    }

    @PostMapping("/appointments/save/{patId}")
    public String saveNewAppointment(@ModelAttribute("appointment") Appointment saveAppointment, BindingResult result) throws RecordAlreadyExistsException{
        if (result.hasErrors()){
            return "new-appointment";
        }
        appointmentService.saveOrUpdateAppointment(saveAppointment);
        return "redirect:/patientDashboard/{patId}";
    }

    @PostMapping("/appointments/save/{patId}/{id}")
    public String saveUpdatedAppointment(@ModelAttribute("appointment") Appointment saveAppointment, BindingResult result) throws RecordAlreadyExistsException{
        if (result.hasErrors()){
            return "new-appointment";
        }
        appointmentService.saveOrUpdateAppointment(saveAppointment);
        return "redirect:/patientDashboard/{patId}";
    }

    @GetMapping("/appointments/update/{patId}/{id}")
    public String updateAppointment(@PathVariable(value = "id") Long id, Model model) throws RecordNotFoundException {
        Appointment dbAppointment = appointmentService.getAppointmentById(id);
        List<Doctor> allDoctors = doctorRepository.findAll();

        model.addAttribute("allDoctors", allDoctors);
        model.addAttribute("appointment", dbAppointment);
        return "update-appointment";
    }

    @GetMapping("/appointments/delete/{patId}/{id}")
    public String deleteAppointmentWithId(@PathVariable(value = "id") Long id){
        appointmentService.deleteAppointment(id);
        return "redirect:/patientDashboard/{id}";
    }
}