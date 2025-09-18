package za.ac.cput.Factory;

import za.ac.cput.Domain.Parent;
import za.ac.cput.Domain.User;
import za.ac.cput.Util.Helper;

public class ParentFactory {
    public static Parent createParent(
            String name,
            String surname,
            String email,
            String number,
            String username,
            String password,
            String address,
            User.Role role

    )
    {
        if(Helper.isNullOrEmpty(name)|| Helper.isNullOrEmpty(surname)|| Helper.isNullOrEmpty(username)|| Helper.isNullOrEmpty(address) ||role==null)
            return null;
        if(!Helper.isValidEmail(email))
            return null;
        if(!Helper.isValidPhoneNumber(number))
            return null;
        if(!Helper.isValidPassword(password))
            return null;
        return new Parent.Builder().setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setNumber(number)
                .setUsername(username)
                .setPassword(password)
                .setRole(role)
                .setAddress(address)
                .build();
    }
}
