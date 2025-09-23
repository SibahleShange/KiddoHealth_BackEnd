package za.ac.cput.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Appointment;
import za.ac.cput.Domain.AppointmentStatus;
import za.ac.cput.Domain.Doctor;
import za.ac.cput.Domain.DoctorDashboard;
import za.ac.cput.Repository.AppointmentRepository;
import za.ac.cput.Repository.DoctorRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService implements IDoctorService {
  
 private  DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;
 
@Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor create(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }

    @Override
    public Doctor read(Long id) {
        return this.doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }
    @Override
    public List<Doctor> getAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Doctor findByEmail(String email) {
        return this.doctorRepository.findByEmailIgnoreCase(email).orElse(null);
    }

    @Override
    public Doctor updatePassword(Long userId, String newPassword) {
        // Fetch doctor directly from repository
        Doctor doctor = doctorRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Update password
        doctor.setPassword(newPassword);

        // Save changes directly
        return doctorRepository.save(doctor);
    }

    @Override
    public DoctorDashboard getDashboardSummary(Long doctorId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        DoctorDashboard dashboard = new DoctorDashboard();

        // Today's appointment count
        dashboard.setTotalAppointmentsToday(
                appointmentRepository.countByDoctorIdAndAppointmentDate(doctorId, today)
        );

        // Upcoming appointments (scheduled)
        dashboard.setUpcomingAppointments(
                appointmentRepository.countByDoctorIdAndStatus(doctorId, AppointmentStatus.SCHEDULED)
        );

        // Completed this week
        List<Appointment> weeklyAppointments = appointmentRepository
                .findByDoctorIdAndAppointmentDateBetween(doctorId, startOfWeek, endOfWeek);

        Long completedThisWeek = weeklyAppointments.stream()
                .filter(app -> app.getStatus() == AppointmentStatus.COMPLETED)
                .count();
        dashboard.setCompletedAppointmentsThisWeek(completedThisWeek);

        // Today's appointments list
        dashboard.setTodaysAppointments(
                appointmentRepository.findByDoctorIdAndAppointmentDateOrderByTimeSlotAsc(doctorId, today)
        );

        // Status distribution
        Map<AppointmentStatus, Long> statusMap = new HashMap<>();
        for (AppointmentStatus status : AppointmentStatus.values()) {
            Long count = appointmentRepository.countByDoctorIdAndStatus(doctorId, status);
            statusMap.put(status, count);
        }
        dashboard.setAppointmentsByStatus(statusMap);

        return dashboard;
    }

    @Override
    public List<Appointment> getTodaysAppointments(Long doctorId) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateOrderByTimeSlotAsc(
                doctorId, LocalDate.now());
    }
}
