package ci.digitalacademy.monEtab.services.impl;

import ci.digitalacademy.monEtab.services.*;
import ci.digitalacademy.monEtab.services.dto.StudentDTO;
import ci.digitalacademy.monEtab.services.dto.TeacherDTO;
import ci.digitalacademy.monEtab.services.dto.UserDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportPDFServiceImpl implements ReportPDFService {

    private final StudentService studentService;

    private final TeacherService teacherService;

    private final UserService userService;

    @Override
    public void generateReportPDFStudent(HttpServletResponse response) throws IOException {
        List<StudentDTO> studentDTOS = studentService.findAll();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=students_report.pdf");


        // Création du document PDF
        com.itextpdf.kernel.pdf.PdfWriter writer = new com.itextpdf.kernel.pdf.PdfWriter(response.getOutputStream());
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

        // Ajout du titre
        document.add(new com.itextpdf.layout.element.Paragraph("Student Report").setBold().setFontSize(20));

        // Création du tableau PDF
        com.itextpdf.layout.element.Table table = new com.itextpdf.layout.element.Table(5);
        table.addCell("Nom");
        table.addCell("Prénom");
        table.addCell("Téléphone");
        table.addCell("Date de Naissance");
        table.addCell("Matricule");

        // Remplissage des données
        for (StudentDTO student : studentDTOS) {
            table.addCell(student.getFirstName());
            table.addCell(student.getLastName());
            table.addCell(student.getPhoneNumber());
            table.addCell(student.getBirthday().toString());
            table.addCell(student.getMatricule());
        }

        document.add(table);
        document.close();
    }



    @Override
    public void generateReportPDFTeacher(HttpServletResponse response) throws IOException {
        List<TeacherDTO> teacherDTOS = teacherService.findAll();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=teachers_report.pdf");

        // Création du document PDF
        com.itextpdf.kernel.pdf.PdfWriter writer = new com.itextpdf.kernel.pdf.PdfWriter(response.getOutputStream());
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

        // Ajout du titre
        document.add(new com.itextpdf.layout.element.Paragraph("Teacher Report").setBold().setFontSize(20));

        // Création du tableau PDF
        com.itextpdf.layout.element.Table table = new com.itextpdf.layout.element.Table(6);
        table.addCell("Spécialité");
        table.addCell("Nom");
        table.addCell("Prénom");
        table.addCell("Téléphone");
        table.addCell("Date de Naissance");

        // Remplissage des données
        for (TeacherDTO teacher : teacherDTOS) {
            table.addCell(teacher.getSpecialty());
            table.addCell(teacher.getFirstName());
            table.addCell(teacher.getLastName());
            table.addCell(teacher.getPhoneNumber());
            table.addCell(teacher.getBirthday().toString());
        }

        document.add(table);
        document.close();
    }



    @Override
    public void generateReportPDFUser(HttpServletResponse response) throws IOException {
        List<UserDTO> userDTOS = userService.findAll();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=users_report.pdf");

        // Création du document PDF
        com.itextpdf.kernel.pdf.PdfWriter writer = new com.itextpdf.kernel.pdf.PdfWriter(response.getOutputStream());
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

        // Ajout du titre
        document.add(new com.itextpdf.layout.element.Paragraph("User Report").setBold().setFontSize(20));

        // Création du tableau PDF
        com.itextpdf.layout.element.Table table = new com.itextpdf.layout.element.Table(2);
        table.addCell("Pseudo");
        table.addCell("Date de Création");

        // Remplissage des données
        for (UserDTO user : userDTOS) {
            table.addCell(user.getPseudo());
            table.addCell(user.getCreatedDate().toString());
        }

        document.add(table);
        document.close();
    }

}
