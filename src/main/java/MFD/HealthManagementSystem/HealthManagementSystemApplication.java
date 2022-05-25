package MFD.HealthManagementSystem;

import MFD.HealthManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealthManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(HealthManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		patientRepository.deleteAllInBatch();
//		appointmentRepository.deleteAllInBatch();
	}
}