package com.commercial.admin;

import com.commercial.admin.db.FeedbackRepository;
import com.commercial.admin.db.TaskRepository;
import com.commercial.admin.db.UserRepository;
import com.commercial.admin.db.entities.Feedback;
import com.commercial.admin.db.entities.Task;
import com.commercial.admin.db.entities.User;
import com.commercial.admin.model.FeedbackTable;
import com.commercial.admin.model.UserTable;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
@AllArgsConstructor
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;
    private final TaskRepository taskRepository;
    private final ExcelService excelService;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }


    @GetMapping("/users")
    public String users(Model model) {
        List<UserTable> result = userRepository
                .findAll()
                .stream()
                .map(user -> new UserTable(
                        user.getId(),
                        user.getPhone(),
                        user.getName(),
                        user.getSurname(),
                        user.getMiddleName(),
                        user.getEmail(),
                        user.getPlace(),
                        user.getDivision(),
                        user.getActiveGifts(),
                        user.getTicketNumber()
                ))
                .toList();

        model.addAttribute("userList", result);
        return "users";
    }

    @GetMapping("/feedbacks")
    public String feedbacks(Model model) {
        Map<Long, String> taskIdToQuestionString = taskRepository
                .findAll()
                .stream()
                .collect(Collectors.toMap(Task::getId, Task::getFeedbackQuestion));
        Map<Long, String> userIdToPhoneString = userRepository
                .findAll()
                .stream()
                .collect(Collectors.toMap(User::getId, User::getPhone));

        List<FeedbackTable> result = feedbackRepository
                .findAll()
                .stream()
                .map(feedback -> new FeedbackTable(
                        userIdToPhoneString.get(feedback.getUserId()),
                        taskIdToQuestionString.get(feedback.getTaskId()),
                        feedback.getResponse()
                ))
                .toList();
        model.addAttribute("feedbackList", result);
        return "feedbacks";
    }

    @GetMapping("/prizes")
    public void prizes(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = excelService.getPrizes();
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

    @GetMapping("/tables")
    public void tables(HttpServletResponse response) throws IOException {
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
