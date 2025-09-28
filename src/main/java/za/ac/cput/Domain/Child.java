package za.ac.cput.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
      private long childId;
      private String identityNumber;
      private String name;
      private String surname;
      private int age;
      private String gender;
      private LocalDate dateOfBirth;
      private String profilePictureUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_user_id")
    @JsonIgnoreProperties({"children"})
    private Parent parent;

    @OneToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_record_id")
      private MedicalRecord medicalRecord;

       public Child() {
     }
      public Child(Builder builder) {
        this.childId = builder.childId;
        this.identityNumber = builder.identityNumber;
        this.name = builder.name;
        this.surname = builder.surname;
        this.age = builder.age;
        this.gender = builder.gender;
        this.dateOfBirth = builder.dateOfBirth;
        this.parent = builder.parent;
        this.medicalRecord = builder.medicalRecord;
        this.profilePictureUrl = builder.profilePictureUrl;

}
    public long getChildId() {
        return childId;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Parent getParent() {
        return parent;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    @Override
    public String toString() {
        return "Child{" +
                "childId=" + childId +
                ", identityNumber=" + identityNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", parent=" + parent +
                ", medicalRecord=" + medicalRecord +
                '}';
    }

    public static class Builder {
        private long childId;
        private String identityNumber;
        private String name;
        private String surname;
        private int age;
        private String gender;
        private LocalDate dateOfBirth;
        private Parent parent;
        private MedicalRecord medicalRecord;
        private String profilePictureUrl;


        public Builder setChildId(long childId) {
            this.childId = childId;
            return this;
        }

        public Builder setIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setParent(Parent parent) {
            this.parent = parent;
            return this;
        }

        public Builder setMedicalRecord(MedicalRecord medicalRecord) {
            this.medicalRecord = medicalRecord;
            return this;
        }
        public Builder setProfilePictureUrl(String profilePictureUrl) {
            this.profilePictureUrl = profilePictureUrl;
            return this;
        }

        public Builder copy(Child child) {
            this.childId = child.getChildId();
            this.identityNumber = child.getIdentityNumber();
            this.name = child.getName();
            this.surname = child.getSurname();
            this.age = child.getAge();
            this.gender = child.getGender();
            this.dateOfBirth = child.getDateOfBirth();
            this.parent = child.getParent();
            this.medicalRecord = child.getMedicalRecord();
            this.profilePictureUrl = child.getProfilePictureUrl();
            return this;
        }
        public Child build() {
            return new Child(this);
        }
    }
}
