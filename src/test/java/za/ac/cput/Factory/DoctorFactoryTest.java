package za.ac.cput.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.Domain.Doctor;
import za.ac.cput.Domain.User;

import static org.junit.jupiter.api.Assertions.*;

class DoctorFactoryTest {
    private static Doctor doctor =  DoctorFactory.createDoctor("Dr Nandi","Khuzwayo","nandi.khuzwayo@hospital.org","0827654321","drnandi"
    ,"Heart@2024","Cardiologist","DR123456", User.Role.DOCTOR);

    @Test
    void testCreateDoctor() {
        assertNotNull(doctor);
        System.out.println(doctor.toString());
    }

}