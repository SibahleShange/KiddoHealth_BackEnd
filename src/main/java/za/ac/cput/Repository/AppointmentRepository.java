package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.Domain.Appointment;
import za.ac.cput.Domain.AppointmentStatus;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find all appointments for a specific DOCTOR on a specific DATE
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);

    // NEW METHODS FOR DASHBOARD:

    // Count today's appointments
    Long countByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);

    // Count appointments by status
    Long countByDoctorIdAndStatus(Long doctorId, AppointmentStatus status);

    // Get appointments within date range
    List<Appointment> findByDoctorIdAndAppointmentDateBetween(
            Long doctorId, LocalDate startDate, LocalDate endDate);

    // Get today's appointments with details
    List<Appointment> findByDoctorIdAndAppointmentDateOrderByTimeSlotAsc(
            Long doctorId, LocalDate date);

}
