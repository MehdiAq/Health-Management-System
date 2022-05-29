package MFD.HealthManagementSystem.repository;

import MFD.HealthManagementSystem.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
//    List<Doctor> findDoctorsByFirstNameContainsOrLastNameContains(String name);
    List<Doctor> findDoctorsBySpecialty(String name);
}