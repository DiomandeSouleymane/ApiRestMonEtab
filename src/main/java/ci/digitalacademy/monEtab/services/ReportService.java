package ci.digitalacademy.monEtab.services;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface ReportService {

    void generateReportExcelStudent(HttpServletResponse response) throws IOException;
    void generateReportExcelTeacher(HttpServletResponse response) throws IOException;
    void generateReportExcelUser(HttpServletResponse response) throws IOException;
}
