package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.Domain.Doctor;
import za.ac.cput.Domain.Parent;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Doctor findByEmail(String email);
 //   Doctor updatePassword(Long userId, String newPassword);
    Optional<Doctor> findByEmailIgnoreCase(String email);
}

