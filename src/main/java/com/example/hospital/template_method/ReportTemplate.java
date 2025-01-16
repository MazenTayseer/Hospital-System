package com.example.hospital.template_method;

public interface ReportTemplate {

    // Template method
    default String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append(printHeader());
        report.append(printTreatmentDetails());
        report.append(printFooter());
        return report.toString();
    }

    default String printHeader() {
        return "---- Treatment Report ----\n" +
               "Hospital: Ain Shams Charity Hospital\n" +
               "--------------------------\n";
    }

    String printTreatmentDetails();

    // Footer remains the same
    default String printFooter() {
        return "--------------------------\n" +
               "Report Generated Successfully!\n";
    }
}
