package za.ac.cput.Service;

import za.ac.cput.Domain.Appointment;
import za.ac.cput.Domain.Doctor;
import za.ac.cput.Domain.DoctorDashboard;
import za.ac.cput.Domain.Parent;

import java.util.List;

public interface IDoctorService extends IService<Doctor, Long> {
    List<Doctor> getAll();
   Doctor findByEmail(String email);
    Doctor updatePassword(Long userId, String newPassword);

    DoctorDashboard getDashboardSummary(Long doctorId);
    List<Appointment> getTodaysAppointments(Long doctorId);
}
