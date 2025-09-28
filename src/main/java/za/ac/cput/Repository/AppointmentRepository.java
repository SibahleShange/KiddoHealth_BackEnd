package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.Domain.Appointment;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find all appointments for a specific DOCTOR on a specific DATE
    @Query("SELECT a FROM Appointment a WHERE a.doctor.userId= :doctorId AND a.appointmentDate = :date")
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);

}
