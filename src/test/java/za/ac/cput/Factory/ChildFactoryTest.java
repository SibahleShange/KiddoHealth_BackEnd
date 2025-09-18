package za.ac.cput.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.Domain.Child;
import za.ac.cput.Domain.MedicalRecord;
import za.ac.cput.Domain.Parent;
import za.ac.cput.Domain.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ChildFactoryTest {
    private static MedicalRecord medicalRecord = MedicalRecordFactory.createMedicalRecord("Follow-up", LocalDate.of(2025, 7, 27),"Patient is responding well to treatment.");
    private static Parent parent = ParentFactory.createParent("Thabo","Mthembu","thabo.mthembu@example.com","0821234567","thaboParent",
            "Strong@123","123 Mandela Street, Soweto", User.Role.PATIENT);
    private static Child child = ChildFactory.createChild("2001015062087","Sipho","Mthembu", "Female",parent,medicalRecord);


    @Test
    public void testChild(){
        assertNotNull(child);
        System.out.println( child.toString());
    }


}