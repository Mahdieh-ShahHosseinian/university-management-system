package com.example.sm.service;

import com.example.sm.model.Professor;
import com.example.sm.model.Student;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ExcelService {

    private ProfessorService professorService;
    private StudentService studentService;

    public void exportToExcel() {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Users");
            insertHeader(workbook, sheet);
            insertData(sheet);
            for (int i = 0; i < 4; i++) sheet.autoSizeColumn(i);

            File file = new File("src/main/resources/export.xlsx");
            FileOutputStream outputStream = new FileOutputStream(file, false);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            log.error("Error during export Excel file", e);
            e.printStackTrace();
        }
    }

    private void insertHeader(Workbook workbook, Sheet sheet) {

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Row headRow = sheet.createRow(0);

        Cell cell = headRow.createCell(0);
        cell.setCellValue("Firstname");
        cell.setCellStyle(headerCellStyle);

        cell = headRow.createCell(1);
        cell.setCellValue("Lastname");
        cell.setCellStyle(headerCellStyle);

        cell = headRow.createCell(2);
        cell.setCellValue("Role");
        cell.setCellStyle(headerCellStyle);

        cell = headRow.createCell(3);
        cell.setCellValue("National-ID");
        cell.setCellStyle(headerCellStyle);
    }

    private void insertData(Sheet sheet) {

        List<Professor> professors = professorService.getAll();
        List<Student> students = studentService.getAll();

        int i = 1;
        for (Professor professor : professors) {
            Row dataRow = sheet.createRow(i++);
            dataRow.createCell(0).setCellValue(professor.getFirstName());
            dataRow.createCell(1).setCellValue(professor.getLastName());
            dataRow.createCell(2).setCellValue("PROFESSOR");
            dataRow.createCell(3).setCellValue(professor.getNationalId());
        }

        for (Student student : students) {
            Row dataRow = sheet.createRow(i++);
            dataRow.createCell(0).setCellValue(student.getFirstName());
            dataRow.createCell(1).setCellValue(student.getLastName());
            dataRow.createCell(2).setCellValue("STUDENT");
            dataRow.createCell(3).setCellValue(student.getNationalId());
        }
    }
}
