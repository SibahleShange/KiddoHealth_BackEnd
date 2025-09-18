package za.ac.cput.Factory;

import za.ac.cput.Domain.MedicalRecord;
import za.ac.cput.Util.Helper;

import java.time.LocalDate;

public class MedicalRecordFactory {
    public static MedicalRecord createMedicalRecord(
            String recordType,
            LocalDate recordDate,
            String progressReport
    ) {
        if (Helper.isNullOrEmpty(recordType) || Helper.isNullOrEmpty(progressReport) || recordDate == null)
            return null;

        return new MedicalRecord.Builder()
                .setRecordType(recordType)
                .setRecordDate(recordDate)
                .setProgressRport(progressReport)
                .build();
    }
}