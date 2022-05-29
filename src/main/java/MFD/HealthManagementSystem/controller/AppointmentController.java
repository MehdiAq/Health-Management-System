package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.*;
import MFD.HealthManagementSystem.model.*;
import MFD.HealthManagementSystem.service.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.sql.Date;
import java.util.*;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments/list")
    public String viewAppointments(Model model){
        List<Appointment> appointmentsList = appointmentService.getAppointmentList();
        model.addAttribute("appointments", appointmentsList);
        return "appointments-list";
    }

    @GetMapping("/appointments/{doctorId}/list")
    public String viewDoctorAppointments(@PathVariable(value = "doctorId")long id, Model model){
        List<Appointment> appointmentsList = appointmentService.getDoctorAppointments(id);
        model.addAttribute("appointments", appointmentsList);
        return "appointments-list";
    }

    @GetMapping("/appointments/{insuranceNumber}/list")
    public String viewPatientAppointments(@PathVariable(value = "insuranceNumber")long id, Model model){
        List<Appointment> appointmentsList = appointmentService.getPatientAppointments(id);
        model.addAttribute("appointments", appointmentsList);
        return "appointments-list";
    }

    @GetMapping("/appointments/{date}/list")
    public String viewPatientAppointments(@PathVariable(value = "date") Date date, Model model){
        List<Appointment> appointmentsList = appointmentService.getAppointmentListByDate(date);
        model.addAttribute("appointments", appointmentsList);
        return "appointments-list";
    }

    @GetMapping("/appointments/new")
    public String addNewAppointment(Model model){
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        return "new-appointment";
    }

    @PostMapping("/appointments/save")
    public String saveAppointment(@Valid @ModelAttribute("appointment") Appointment saveAppointment, BindingResult result) throws RecordAlreadyExistsException{
        if (result.hasErrors()){
            return "new-appointment";
        }
        appointmentService.saveOrUpdateAppointment(saveAppointment);
        return "redirect:/appointments/list";
    }

    @GetMapping("/appointments/update/{id}")
    public String updateAppointment(@PathVariable(value = "id") Long id, Model model) throws RecordNotFoundException {
        Appointment dbAppointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", dbAppointment);
        return "update-appointment";
    }

    @GetMapping("/appointments/delete/{id}")
    public String deleteAppointmentWithId(@PathVariable(value = "id") long id){
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments/list";
    }
}