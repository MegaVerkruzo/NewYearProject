package com.commercial.admin;

import com.commercial.admin.db.UserRepository;
import com.commercial.admin.db.entities.User;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@org.springframework.stereotype.Controller
@AllArgsConstructor
@RequestMapping("admin")
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final UserRepository userRepository;
    private final ExcelService excelService;

    @GetMapping("/")
    public String method(Model model) {


        List<User> result = userRepository.findAll();

        model.addAttribute("userList", result);
        return "hello";
    }

    @GetMapping("/users")
    public void reportUsersExcel(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = excelService.getWorkBook();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            bos.close();
        }
        response.getOutputStream().write(bos.toByteArray());

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("XXXX.xlsx", StandardCharsets.UTF_8));
        workbook.write(response.getOutputStream());
    }
}
