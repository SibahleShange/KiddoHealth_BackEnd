package za.ac.cput.Factory;

import za.ac.cput.Domain.Doctor;
import za.ac.cput.Domain.User;
import za.ac.cput.Util.Helper;



public class DoctorFactory {
    public static Doctor createDoctor(
     //long userId,
    String name,
    String surname,
    String email,
    String number,
    String username,
    String password,
     String specialization,
    String licenseNumber,
     User.Role role
){
 if(Helper.isNullOrEmpty(name)|| Helper.isNullOrEmpty(surname)|| Helper.isNullOrEmpty(specialization)||
         Helper.isNullOrEmpty(username)||role==null)
     return null;
 if(!Helper.isValidEmail(email))
     return null;
 if(!Helper.isValidPhoneNumber(number))
     return null;
 if(!Helper.isValidPassword(password))
     return null;
 if(!Helper.isValidDoctorLicense(licenseNumber))
     return null;
  return new Doctor.Builder().setName(name)
          .setSurname(surname)
          .setEmail(email)
          .setNumber(number)
          .setUsername(username)
          .setPassword(password)
          .setRole(role)
          .setSpecialization(specialization)
          .setLicenseNumber(licenseNumber)
          .build();

    }
}
