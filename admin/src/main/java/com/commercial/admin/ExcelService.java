package com.commercial.admin;

import com.commercial.admin.db.AttemptRepository;
import com.commercial.admin.db.FeedbackRepository;
import com.commercial.admin.db.UserRepository;
import com.commercial.admin.db.entities.Attempt;
import com.commercial.admin.db.entities.Feedback;
import com.commercial.admin.db.entities.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class ExcelService {
    private final Logger logger = LoggerFactory.getLogger(ExcelService.class);
    private final UserRepository userRepository;
    private final AttemptRepository attemptRepository;
    private final FeedbackRepository feedbackRepository;

    public XSSFWorkbook getWorkBook() {
        XSSFWorkbook workbook = defaultWorkBook();
        updateSheet(attemptRepository.findAll(), workbook.createSheet("attempts"), ExcelService::attemptToCells);
        updateSheet(feedbackRepository.findAll(), workbook.createSheet("feedbacks"), ExcelService::feedbackToCells);
        updateSheet(userRepository.findAll(), workbook.createSheet("users"), ExcelService::userToCells);
        return workbook;
    }

    public XSSFWorkbook getPrizes() {
        XSSFWorkbook workbook = defaultWorkBook();
        List<User> list = userRepository.findAll();
        updateSheet(ticketNumbers(list, 1), workbook.createSheet("1 level"), List::of);
        updateSheet(ticketNumbers(list, 2), workbook.createSheet("2 level"), List::of);
        updateSheet(ticketNumbers(list, 3), workbook.createSheet("3 level"), List::of);
        updateSheet(ticketNumbers(list, 4), workbook.createSheet("4 level"), List::of);
        updateSheet(ticketNumbers(list, 5), workbook.createSheet("5 level"), List::of);
        return workbook;
    }

    private XSSFWorkbook defaultWorkBook() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        return workbook;
    }

    private <T> void updateSheet(List<T> list, XSSFSheet sheet, Function<T, List<String>> daoToCells) {
        for (int i = 0; i < list.size(); ++i) {
            Row row = sheet.createRow(i);
            List<String> stringColumns = daoToCells.apply(list.get(i));
            for (int j = 0; j < stringColumns.size(); ++j) {
                row.createCell(j).setCellValue(stringColumns.get(j));
            }
        }
    }

    private static List<String> ticketNumbers(List<User> list, int activeGifts) {
        return list.stream()
                .filter(user -> user.getActiveGifts() >= activeGifts)
                .map(User::getTicketNumber)
                .map(Object::toString)
                .toList();
    }

    private static List<String> attemptToCells(Attempt elem) {
        return List.of(
                elem.getId().toString(),
                elem.getWord(),
                elem.getDate().toString()
        );
    }

    private static List<String> feedbackToCells(Feedback elem) {
        return List.of(
                elem.getId().toString(),
                elem.getUserId().toString(),
                elem.getTaskId().toString(),
                elem.getResponse() == null ? "" : elem.getResponse()
        );
    }

    private static List<String> userToCells(User elem) {
        return List.of(
                elem.getId().toString(),
                elem.getPhone(),
                elem.getUsername() == null ? "" : elem.getUsername(),
                elem.getName(),
                elem.getSurname(),
                elem.getMiddleName(),
                elem.getEmail(),
                elem.getPlace(),
                elem.getDivision(),
                Integer.toString(elem.getActiveGifts()),
                Integer.toString(elem.getTicketNumber())
        );
    }

}
