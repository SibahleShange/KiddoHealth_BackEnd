package za.ac.cput.Util;

import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.time.Period;

public class Helper {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
//checking email
    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(email)) {
            System.out.println(email + " is a valid email address.");
            return true;
        } else {
            System.out.println(email + " is not a valid email address.");
            return false;
        }
    }
//cheking identity number
    public static boolean isValidIdentityNumber(String identityNumber) {
        if (identityNumber != null && identityNumber.matches("\\d{13}")) {
            System.out.println("The identity number is valid.");
            return  true;
        } else {
            System.out.println("The identity number is invalid.");
            return false;
        }


    }
    //getting date of birth from identity number
    public static LocalDate getDateOfBirth(String identityNumber) {
        if(!isValidIdentityNumber(identityNumber)){
            System.out.println("Cannot extract date of birth: Invalid identity number.");
            return null;
        }
        int year = Integer.parseInt(identityNumber.substring(0, 2));
        int month = Integer.parseInt(identityNumber.substring(2, 4));
        int day= Integer.parseInt(identityNumber.substring(4, 6));

        if (month < 1 || month > 12) {
            System.out.println("Invalid month in identity number: " + month);
            return null;
        }
        if (day < 1 || day > 31) {
            System.out.println("Invalid day in identity number: " + day);
            return null;
        }

        int currentYear = LocalDate.now().getYear() % 100;
        if (year <= currentYear) {
            year += 2000;
        } else {
            year += 1900;
        }

        return LocalDate.of(year, month, day);
    }

    //calculate age from date of birth
    public static int calculateAgeFromDOB(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    //validate number
    public static boolean isValidPhoneNumber(String number) {
        return number != null && number.matches("0\\d{9}");
    }

//age validation
    public static boolean isValidAge(int age) {
        if (age < 0 || age > 200) {
            System.out.println("Invalid age range. Age must be between 0 and 200.");
            return false;
        } else if (age > 10) {
            System.out.println("The child is way too old. We only deal with children aged 10 and below.");
            return false;
        }
        return true;
    }


    // Validate password

    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        return hasNumber && hasSpecialChar;
    }

    //validate doctor's license number  ,Example DR1234, dr987654, Dr0001
    public static boolean isValidDoctorLicense(String licenseNumber) {
        return licenseNumber != null && licenseNumber.matches("(?i)^DR\\d{4,6}$");
    }






}
