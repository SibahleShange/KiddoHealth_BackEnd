package za.ac.cput.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalRecordID;
    private String recordType;
    private LocalDate recordDate;
    private String progressReport;

    public MedicalRecord() {
    }

    public MedicalRecord(Builder builder) {
        this.medicalRecordID = builder.medicalRecordID;
        this.recordType = builder.recordType;
        this.recordDate = builder.recordDate;
        this.progressReport = builder.progressReport;

    }

    public Long getMedicalRecordID() {
        return medicalRecordID;
    }

    public String getRecordType() {
        return recordType;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public String getProgressReport() {
        return progressReport;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "medicalRecordID='" + medicalRecordID + '\'' +
                ", recordType='" + recordType + '\'' +
                ", recordDate='" + recordDate + '\'' +
                ", progressRport='" + progressReport + '\'' +
                '}';
    }

    public static class Builder {
        private Long medicalRecordID;
        private String recordType;
        private LocalDate recordDate;
        private String progressReport;

        public Builder setMedicalRecordID(Long medicalRecordID) {
            this.medicalRecordID = medicalRecordID;
            return this;
        }

        public Builder setRecordType(String recordType) {
            this.recordType = recordType;
            return this;
        }

        public Builder setRecordDate(LocalDate recordDate) {
            this.recordDate = recordDate;
            return this;
        }

        public Builder setProgressRport(String progressRport) {
            this.progressReport = progressRport;
            return this;
        }

        public Builder copy(MedicalRecord medicalRecord) {
            this.medicalRecordID = medicalRecord.getMedicalRecordID();
            this.recordType = medicalRecord.getRecordType();
            this.recordDate = medicalRecord.getRecordDate();
            this.progressReport = medicalRecord.getProgressReport();
            return this;
        }
        public MedicalRecord build() {
            return new MedicalRecord(this);
        }
    }
}
