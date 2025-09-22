package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.Domain.Appointment;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find all appointments for a specific DOCTOR on a specific DATE
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);

}
