package za.ac.cput.Domain;

import jakarta.persistence.Entity;

@Entity
public class Doctor extends User {
    private String specialization;
    private String licenseNumber;

    public Doctor() {
        super();
    }

    public Doctor(Builder builder) {
        this.specialization = builder.specialization;
        this.licenseNumber = builder.licenseNumber;
       this.userId = builder.userId;
       this.name = builder.name;
       this.surname = builder.surname;
       this.email = builder.email;
       this.number = builder.number;
       this.username = builder.username;
       this.password = builder.password;
       this.role = builder.role;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "specialization='" + specialization + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public static class Builder {
        private String specialization;
        private String licenseNumber;
        private long userId;
        private String name;
        private String surname;
        private String email;
        private String number;
        private String username;
        private String password;
        private Role role;


        public Builder setSpecialization(String specialization) {
            this.specialization = specialization;
            return this;
        }

        public Builder setLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }
        public Builder copy(Doctor doctor) {
            this.specialization = doctor.specialization;
            this.licenseNumber = doctor.licenseNumber;
            this.userId = doctor.userId;
            this.name = doctor.name;
            this.surname = doctor.surname;
            this.email = doctor.email;
            this.number = doctor.number;
            this.username = doctor.username;
            this.password = doctor.password;
            this.role = doctor.role;
            return this;
        }

        public Doctor build() {
            return new Doctor(this);
        }
    }
}
