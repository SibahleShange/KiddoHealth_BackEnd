package za.ac.cput.Factory;

import za.ac.cput.Domain.Child;
import za.ac.cput.Domain.MedicalRecord;
import za.ac.cput.Domain.Parent;
import za.ac.cput.Util.Helper;

import java.time.LocalDate;

public class ChildFactory {
    public static Child createChild(
    String identityNumber,
    String name,
    String surname,
    String gender,

//    int age,
    Parent parent,
    MedicalRecord medicalRecord
    ) {
        if(Helper.isNullOrEmpty(name)||Helper.isNullOrEmpty(surname)|| parent==null || medicalRecord==null)
            return null;

        if (!Helper.isValidIdentityNumber(identityNumber))
            return null;
        

        LocalDate dateOfBirth = Helper.getDateOfBirth(identityNumber);
        if(dateOfBirth ==null){
            System.out.println("Invalid date of birth.");
            return null;
        }
        
        int age = Helper.calculateAgeFromDOB(dateOfBirth);
        if (age > 10) {
            System.out.println("The child is too old. Only children aged 10 and below are accepted.");
            return null;
        }


        return new Child.Builder()
                    .setIdentityNumber(identityNumber)
                    .setName(name)
                    .setSurname(surname)
                    .setAge(age)
                    .setGender(gender)
                    .setParent(parent)
                    .setMedicalRecord(medicalRecord)
                    .setDateOfBirth(dateOfBirth)
                    .build();


    }
}
