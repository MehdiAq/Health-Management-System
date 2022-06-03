package MFD.HealthManagementSystem;


import MFD.HealthManagementSystem.model.Appointment;
import MFD.HealthManagementSystem.model.Doctor;
import MFD.HealthManagementSystem.model.Patient;
import MFD.HealthManagementSystem.repository.AppointmentRepository;
import MFD.HealthManagementSystem.repository.DoctorRepository;
import MFD.HealthManagementSystem.repository.MedicalServiceRepository;
import MFD.HealthManagementSystem.repository.PatientRepository;
import MFD.HealthManagementSystem.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class HealthManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private MedicalServiceRepository medicalServiceRepository;
	@Autowired
	private PrescriptionRepository prescriptionRepository;

	public static void main(String[] args) {
		SpringApplication.run(HealthManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		CommandLineRunner worked on a fresh creation of the tables, and worked each time the tables were manually
//		dropped from MySQL Workbench, but it does not want to work multiple times in a row --- See Postman collection
//		for table population.

//		patientRepository.deleteAllInBatch();
//		appointmentRepository.deleteAllInBatch();
//		doctorRepository.deleteAllInBatch();
//		medicalServiceRepository.deleteAllInBatch();
//		prescriptionRepository.deleteAllInBatch();
//
//		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//
//		patientRepository.save(Patient.builder().id(12345L).firstName("Reilly").lastName("Foskett").cardExpiration("06/2024").address("123 Address Cr.").birthDate(formatter.parse("1994-06-02")).email("reilly@gmail.com").phoneNumber("123-654-7890").familyDoctor("Dr. Medical").build());
//
//		patientRepository.save(Patient.builder().id(67890L).firstName("Mehdi").lastName("Aqdim").cardExpiration("03/2025").address("548 Cool St.").birthDate(formatter.parse("1988-03-02")).email("MQ@email.com").phoneNumber("987 654 3215").familyDoctor("Dr. Lavigne").build());
//
//		patientRepository.save(Patient.builder().id(56542L).firstName("David").lastName("Krushnisky").cardExpiration("11/2022").address("6683 Drury Ln.").birthDate(formatter.parse("1992-11-14")).email("DKrushin@outlook.com").phoneNumber("(564) 892 3176").familyDoctor("Dr. Psalms").build());
//
//		doctorRepository.save(Doctor.builder().firstName("Gregory").lastName("House").address("245 Long Rd.").cellphone("463 264 6842").specialty("Diagnostics").build());
//
//		appointmentRepository.save(Appointment.builder().patient(patientRepository.getReferenceById(12345L)).doctor(doctorRepository.getReferenceById(1L)).appointmentDate(formatter.parse("2022-06-22")).timeSlot("14:00-15:00").procedure("Heart Surgery").build());

	}
}