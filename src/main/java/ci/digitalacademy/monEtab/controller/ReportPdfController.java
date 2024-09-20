package ci.digitalacademy.monEtab.controller;

import ci.digitalacademy.monEtab.services.ReportPDFService;
import ci.digitalacademy.monEtab.services.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/reports")
@Slf4j
@RequiredArgsConstructor

public class ReportPdfController {

    private final ReportPDFService reportPDFService;

    @GetMapping("/report/student/pdf")
    public void generateStudentReport(HttpServletResponse response) throws IOException {
        reportPDFService.generateReportPDFStudent(response);
    }

    @GetMapping("/report/teacher/pdf")
    public void generateTeacherReport(HttpServletResponse response) throws IOException {
        reportPDFService.generateReportPDFTeacher(response);
    }

    @GetMapping("/report/user/pdf")
    public void generateUserReport(HttpServletResponse response) throws IOException {
        reportPDFService.generateReportPDFUser(response);
    }


}
