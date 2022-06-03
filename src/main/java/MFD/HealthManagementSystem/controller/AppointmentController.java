package MFD.HealthManagementSystem.controller;

import MFD.HealthManagementSystem.exception.RecordAlreadyExistsException;
import MFD.HealthManagementSystem.exception.RecordNotFoundException;
import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.Doctor;
import MFD.HealthManagementSystem.model.MedicalService;
import MFD.HealthManagementSystem.model.Patient;
import MFD.HealthManagementSystem.repository.DoctorRepository;
import MFD.HealthManagementSystem.repository.MedicalServiceRepository;
import MFD.HealthManagementSystem.service.AppointmentService;
import MFD.HealthManagementSystem.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppointmentController {

    private final DoctorRepository doctorRepository;

    private final AppointmentService appointmentService;
    
    private final PatientService patientService;

    private final MedicalServiceRepository medicalServiceRepository;

    public AppointmentController(DoctorRepository doctorRepository, AppointmentService appointmentService, PatientService patientService, MedicalServiceRepository medicalServiceRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.medicalServiceRepository = medicalServiceRepository;
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

        model.addAttribute("allDoctors", allDoctors);
        model.addAttribute("appointment", appointment);
        return "new-appointment";
    }

    @PostMapping("/appointments/save/{patId}")
    public String saveNewAppointment(@ModelAttribute("appointment") Appointment saveAppointment, Model model, BindingResult result) throws RecordAlreadyExistsException{
        if (result.hasErrors()){
            return "new-appointment";
        }
        if(appointmentService.saveOrUpdateAppointment(saveAppointment) == null){
            String timeslotUnavailable = "That Timeslot is already booked with that Doctor";
            model.addAttribute("noTimeSlot", timeslotUnavailable);
           return "redirect:/newAppointments/{patId}";
        }
        return "redirect:/patientDashboard/{patId}";
    }

    @PostMapping("/appointments/save/{patId}/{id}")
    public String saveUpdatedAppointment(@ModelAttribute("medicalService") MedicalService medicalService, @ModelAttribute("appointment") Appointment saveAppointment, BindingResult result) throws RecordAlreadyExistsException{
        if (result.hasErrors()){
            return "new-appointment";
        }

        appointmentService.updateMedicalService(medicalService, saveAppointment);
        return "redirect:/patientDashboard/{patId}";
    }

    @GetMapping("/appointments/update/{patId}/{id}")
    public String updateAppointment(@PathVariable(value = "id") Long id, Model model) throws RecordNotFoundException {
        Appointment dbAppointment = appointmentService.getAppointmentById(id);
        List<Doctor> allDoctors = doctorRepository.findAll();
        MedicalService medicalService = medicalServiceRepository.findByPatient_IdAndServiceNameAndServiceDate(dbAppointment.getPatient().getId(), dbAppointment.getProcedure(), dbAppointment.getAppointmentDate());

        model.addAttribute("medicalService", medicalService);
        model.addAttribute("allDoctors", allDoctors);
        model.addAttribute("appointment", dbAppointment);
        return "update-appointment";
    }

    @GetMapping("/appointments/delete/{aptId}/{id}")
    public String deleteAppointmentWithId(@PathVariable(value = "aptId") Long aptId){
        appointmentService.deleteAppointment(aptId);
        return "redirect:/patientDashboard/{id}";
    }
}