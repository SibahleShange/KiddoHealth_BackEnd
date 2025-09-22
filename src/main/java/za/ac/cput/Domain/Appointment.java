package za.ac.cput.Domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    private LocalDate appointmentDate;
    private LocalTime timeSlot;
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;


    public Appointment() {
    }

    public Appointment(Builder builder) {
        this.appointmentId = builder.appointmentId;
        this.parent = builder.parent;
        this.doctor = builder.doctor;
        this.child = builder.child;
        this.appointmentDate = builder.appointmentDate;
        this.timeSlot = builder.timeSlot;
        this.note = builder.note;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }


    public Long getAppointmentId() {
        return appointmentId;
    }

    public Parent getParent() {
        return parent;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public Child getChild() {
        return child;
    }
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
    public LocalTime getTimeSlot() {
        return timeSlot;
    }
    public String getNote() {
        return note;
    }
    public AppointmentStatus getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", parent=" + parent +
                ", doctor=" + doctor +
                ", child=" + child +
                ", appointmentDate=" + appointmentDate +
                ", timeSlot=" + timeSlot +
                ", note='" + note + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';

    }

    public static class Builder {
        private Long appointmentId;
        private Parent parent;
        private Doctor doctor;
        private Child child;
        private LocalDate appointmentDate;
        private LocalTime timeSlot;
        private String note;
        private AppointmentStatus status = AppointmentStatus.SCHEDULED;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder setAppointmentId(Long appointmentId) {
            this.appointmentId = appointmentId;
            return this;
        }

        public Builder setParent(Parent parent) {
            this.parent = parent;
            return this;
        }

        public Builder setDoctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public Builder setChild(Child child) {
            this.child = child;
            return this;
        }

        public Builder setAppointmentDate(LocalDate appointmentDate) {
            this.appointmentDate = appointmentDate;
            return this;
        }

        public Builder setTimeSlot(LocalTime timeSlot) {
            this.timeSlot = timeSlot;
            return this;
        }

        public Builder setNote(String note) {
            this.note = note;
            return this;
        }

        public Builder setStatus(AppointmentStatus status) {
            this.status = status;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }

        public Builder copy(Appointment appointment) {
            this.appointmentId = appointment.appointmentId;
            this.parent = appointment.parent;
            this.doctor = appointment.doctor;
            this.child = appointment.child;
            this.appointmentDate = appointment.appointmentDate;
            this.timeSlot = appointment.timeSlot;
            this.note = appointment.note;
            this.status = appointment.status;
            this.createdAt = appointment.createdAt;
            this.updatedAt = appointment.updatedAt;
            return this;
        }
    }

}
