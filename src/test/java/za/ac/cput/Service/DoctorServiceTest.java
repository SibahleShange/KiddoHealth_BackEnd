package za.ac.cput.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.Domain.Doctor;
import za.ac.cput.Domain.User;
import za.ac.cput.Factory.DoctorFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class DoctorServiceTest {
    @Autowired
    private DoctorService doctorService;

    private static Doctor doctor =  DoctorFactory.createDoctor("Dr Nandi","Khuzwayo","nandi.khuzwayo@hospital.org","0827654321","drnandi"
            ,"Heart@2024","Cardiologist","DR123456", User.Role.DOCTOR);
    @Test
    void create() {
        Doctor createDoctor = doctorService.create(doctor);
        assertNotNull(createDoctor);
        System.out.println("Doctor created " + createDoctor);
    }

    @Test
    void read() {
        Doctor read = doctorService.read(doctor.getUserId());
        assertNotNull(read);
        System.out.println("Doctor read " + read);
    }

    @Test
    void update() {
        Doctor newDoctor = new Doctor.Builder().copy(doctor).setUsername("Dr Shange").build();
        Doctor updateDoctor = doctorService.update(newDoctor);
        assertNotNull(updateDoctor);
        System.out.println("Doctor updated " + updateDoctor);
    }

    @Test
    void getAll() {
        doctorService.getAll();
        for (Doctor doctor : doctorService.getAll()) {
            System.out.println("Get all Doctors " + doctor);
        }
    }
}