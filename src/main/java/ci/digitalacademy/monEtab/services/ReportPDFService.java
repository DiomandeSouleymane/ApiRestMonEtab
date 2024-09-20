package ci.digitalacademy.monEtab.services;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ReportPDFService {

    void generateReportPDFStudent(HttpServletResponse response) throws IOException;
    void generateReportPDFTeacher(HttpServletResponse response) throws IOException;
    void generateReportPDFUser(HttpServletResponse response) throws IOException;
}
