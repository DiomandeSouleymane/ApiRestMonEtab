package ci.digitalacademy.monEtab.services.impl;


import ci.digitalacademy.monEtab.services.ReportService;
import ci.digitalacademy.monEtab.services.StudentService;
import ci.digitalacademy.monEtab.services.TeacherService;
import ci.digitalacademy.monEtab.services.UserService;
import ci.digitalacademy.monEtab.services.dto.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service

public class ReportServiceImpl implements ReportService {

    private final StudentService studentService;

    private final TeacherService teacherService;

    private final UserService userService;


    @Override
    public void generateReportExcelStudent(HttpServletResponse response) throws IOException {

            List<ExcelStudentDTO> excelStudentDTOS = new ArrayList<>();
            List<StudentDTO> studentDTOS = studentService.findAll();

            studentDTOS.forEach(studentDTO -> {
                ExcelStudentDTO excelStudentDTO = new ExcelStudentDTO();
                excelStudentDTO.setFirstName(studentDTO.getFirstName());
                excelStudentDTO.setLastName(studentDTO.getLastName());
                excelStudentDTO.setNumbers(studentDTO.getPhoneNumber());
                excelStudentDTO.setBirthday(studentDTO.getBirthday());
                excelStudentDTO.setMatricule(studentDTO.getMatricule());

                // Ajout du student à la liste
                excelStudentDTOS.add(excelStudentDTO);
            });

            // Création du workbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Student Report");

            // Création de la première ligne d'en-têtes
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("NOM");
            row.createCell(1).setCellValue("PRENOM");
            row.createCell(2).setCellValue("TELEPHONE");
            row.createCell(3).setCellValue("DATE DE NAISSANCE");
            row.createCell(4).setCellValue("MATRICULE");

            // Ajout des données
            int dataRowIndex = 1;
            for (ExcelStudentDTO student : excelStudentDTOS) {
                HSSFRow dataRow = sheet.createRow(dataRowIndex);
                dataRow.createCell(0).setCellValue(student.getFirstName());
                dataRow.createCell(1).setCellValue(student.getLastName());
                dataRow.createCell(2).setCellValue(student.getNumbers());
                dataRow.createCell(3).setCellValue(student.getBirthday().toString());
                dataRow.createCell(4).setCellValue(student.getMatricule());

                dataRowIndex++;
            }

            // Écriture et fermeture du fichier
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        }


    @Override
    public void generateReportExcelTeacher(HttpServletResponse response) throws IOException {

        List<ExcelTeacherDTO> excelTeacherDTOS = new ArrayList<>();
        List<TeacherDTO> teacherDTOS = teacherService.findAll();

        teacherDTOS.forEach(teacherDTO -> {
            ExcelTeacherDTO excelTeacherDTO = new ExcelTeacherDTO();
            excelTeacherDTO.setFirstName(teacherDTO.getFirstName());
            excelTeacherDTO.setLastName(teacherDTO.getLastName());
            excelTeacherDTO.setNumbers(teacherDTO.getPhoneNumber());
            excelTeacherDTO.setBirthday(teacherDTO.getBirthday());
            excelTeacherDTO.setSpecialty(teacherDTO.getSpecialty());
            excelTeacherDTO.setAvailable(teacherDTO.isAvailable());

            // Ajout du teacher à la liste
            excelTeacherDTOS.add(excelTeacherDTO);
        });

        // Création du workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Teacher Report");

        // Création de la première ligne d'en-têtes
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("SPECIALITE");
        row.createCell(1).setCellValue("NOM");
        row.createCell(2).setCellValue("PRENOM");
        row.createCell(3).setCellValue("TELEPHONE");
        row.createCell(4).setCellValue("DATE DE NAISSANCE");
        row.createCell(5).setCellValue("VACANT");

        // Ajout des données
        int dataRowIndex = 1;
        for (ExcelTeacherDTO teacher : excelTeacherDTOS) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(teacher.getSpecialty());
            dataRow.createCell(1).setCellValue(teacher.getFirstName());
            dataRow.createCell(2).setCellValue(teacher.getLastName());
            dataRow.createCell(3).setCellValue(teacher.getNumbers());
            dataRow.createCell(4).setCellValue(teacher.getBirthday().toString());
            dataRow.createCell(5).setCellValue(teacher.getAvailable());

            dataRowIndex++;
        }

        // Écriture et fermeture du fichier
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    @Override
    public void generateReportExcelUser(HttpServletResponse response) throws IOException {

        List<ExcelUserDTO> excelUserDTOS = new ArrayList<>();
        List<UserDTO> userDTOS = userService.findAll();

        userDTOS.forEach(userDTO -> {
            ExcelUserDTO excelUserDTO = new ExcelUserDTO();
            excelUserDTO.setPseudo(userDTO.getPseudo());
            excelUserDTO.setCreatedDate(userDTO.getCreatedDate());

            // Ajout de l'utilisateur à la liste
            excelUserDTOS.add(excelUserDTO);
        });

        // Création du workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("User Report");

        // Création de la première ligne d'en-têtes
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("PSEUDO");
        row.createCell(1).setCellValue("DATE DE CREATION");

        // Ajout des données
        int dataRowIndex = 1;
        for (ExcelUserDTO user : excelUserDTOS) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(user.getPseudo());
            dataRow.createCell(1).setCellValue(user.getCreatedDate().toString());

            dataRowIndex++;
        }

        // Écriture et fermeture du fichier
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
