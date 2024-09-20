package ci.digitalacademy.monEtab.controller;


import ci.digitalacademy.monEtab.services.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/reports")
@Slf4j
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public String showRapportPage() {
        log.debug("show Rapport Page");

        return "report/default";
    }

    @GetMapping("/excel")
    public void generateRapport(@RequestParam String query , HttpServletResponse response) throws IOException {
        log.debug("show Rapport Page");

        response.setContentType("application/octet-stream"); //octet-stream
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=Reports.xls"; //courses.xls

        response.setHeader(headerKey, headerValue);

        if(query.equals("student")){
            reportService.generateReportExcelStudent(response);
        }else if(query.equals("teacher")){
            reportService.generateReportExcelTeacher(response);
        }else if(query.equals("user")){
            reportService.generateReportExcelUser(response);
        }

    }
}
