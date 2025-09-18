package za.ac.cput.Domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent extends User {
    private String address;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> children ;


    public Parent() {

    }
public Parent(Builder builder) {

        this.address = builder.address;
        this.userId = builder.userId;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.number = builder.number;
        this.username = builder.username;
        this.password = builder.password;
        this.role = builder.role;
}
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "address='" + address + '\'' +
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
        private String address;
        private long userId;
        private String name;
        private String surname;
        private String email;
        private String number;
        private String username;
        private String password;
        private Role role;


        public Builder setAddress(String address) {
            this.address = address;
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
public Builder copy(Parent parent) {
            this.address = parent.address;
            this.userId = parent.userId;
            this.name = parent.name;
            this.surname = parent.surname;
            this.email = parent.email;
            this.number = parent.number;
            this.username = parent.username;
            this.password = parent.password;
            this.role = parent.role;
            return this;
}
public Parent build() {
            return new Parent(this);
}

    }
}