package za.ac.cput.Domain;


import java.util.List;
import java.util.Map;

public class DoctorDashboard {
    private Long totalAppointmentsToday;
    private Long upcomingAppointments;
    private Long completedAppointmentsThisWeek;
    private List<Appointment> todaysAppointments;
    private Map<AppointmentStatus, Long> appointmentsByStatus;

    // Constructor
    public DoctorDashboard() {}

    // Getters and setters
    public Long getTotalAppointmentsToday() { return totalAppointmentsToday; }
    public void setTotalAppointmentsToday(Long totalAppointmentsToday) {
        this.totalAppointmentsToday = totalAppointmentsToday;
    }

    public Long getUpcomingAppointments() { return upcomingAppointments; }
    public void setUpcomingAppointments(Long upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }

    public Long getCompletedAppointmentsThisWeek() { return completedAppointmentsThisWeek; }
    public void setCompletedAppointmentsThisWeek(Long completedAppointmentsThisWeek) {
        this.completedAppointmentsThisWeek = completedAppointmentsThisWeek;
    }

    public List<Appointment> getTodaysAppointments() { return todaysAppointments; }
    public void setTodaysAppointments(List<Appointment> todaysAppointments) {
        this.todaysAppointments = todaysAppointments;
    }

    public Map<AppointmentStatus, Long> getAppointmentsByStatus() { return appointmentsByStatus; }
    public void setAppointmentsByStatus(Map<AppointmentStatus, Long> appointmentsByStatus) {
        this.appointmentsByStatus = appointmentsByStatus;
    }
}
