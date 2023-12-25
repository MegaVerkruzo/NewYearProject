package com.commercial.admin;

import com.commercial.admin.db.AttemptRepository;
import com.commercial.admin.db.ConfigRepository;
import com.commercial.admin.db.FeedbackRepository;
import com.commercial.admin.db.TaskRepository;
import com.commercial.admin.db.UserRepository;
import com.commercial.admin.db.entities.Attempt;
import com.commercial.admin.db.entities.ConfigField;
import com.commercial.admin.db.entities.Feedback;
import com.commercial.admin.db.entities.Task;
import com.commercial.admin.db.entities.User;
import com.commercial.admin.model.FeedbackTable;
import com.commercial.admin.model.UserTable;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
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
    private final ConfigRepository configRepository;
    private final ExcelService excelService;
    private final AttemptRepository attemptRepository;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasksList", taskRepository.findAll());
        return "task/data";
    }

    @GetMapping(value = "/tasks/edit/{id}")
    public String showFormTask(@PathVariable Long id, Model model) {
        model.addAttribute(
                "task",
                taskRepository.findById(id).orElseThrow(IllegalArgumentException::new)
        );
        return "task/form";
    }

    @PostMapping(value = "/tasks/edit")
    public String checkTaskInfo(@Valid Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "task/form";
        }

        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/configs")
    public String configs(Model model) {
        List<ConfigField> result = configRepository.findAllBy();

        model.addAttribute("configList", result);
        return "config/data";
    }

    @GetMapping(value = "/configs/edit/{id}")
    public String showForm(@PathVariable String id, Model model) {
        model.addAttribute(
                "config",
                configRepository.findById(id).orElseThrow(IllegalArgumentException::new)
        );
        return "config/form";
    }

    @PostMapping(value = "/configs/edit")
    public String checkPersonInfo(@Valid ConfigField config, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "config/form";
        }

        configRepository.save(config);
        return "redirect:/configs";
    }

    @GetMapping("/users/{id}")
    public String users(@PathVariable Long id, Model model) {
        List<UserTable> singleUserList = List.of(
                userRepository
                        .findById(id)
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
                        )).orElseThrow(IllegalArgumentException::new));

        model.addAttribute("singleUserList", singleUserList);
        model.addAttribute("feedbackList", getActualFeedbacks(feedbackRepository.findFeedbackByUserId(id)));
        model.addAttribute("attemptList", attemptRepository.findAllByUserIdOrderByDate(id));
        return "user";
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

    @PostMapping("/users/delete/all")
    public String deleteUser() {
        attemptRepository.deleteAll();
        feedbackRepository.deleteAll();
        userRepository.deleteAll();

        return "redirect:/";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        attemptRepository.deleteAllByUserId(id);
        feedbackRepository.deleteAllByUserId(id);
        userRepository.deleteById(id);

        return "redirect:/users";
    }

    @GetMapping("/feedbacks")
    public String feedbacks(Model model) {
        model.addAttribute("feedbackList", getActualFeedbacks(feedbackRepository.findAll()));
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
        response.setHeader("Content-Disposition", "attachment; filename=prizes.xls");
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
        response.setHeader("Content-Disposition", "attachment; filename=tables.xls");
        workbook.write(response.getOutputStream());
    }

    private List<FeedbackTable> getActualFeedbacks(List<Feedback> list) {
        Map<Long, String> taskIdToQuestionString = taskRepository
                .findAll()
                .stream()
                .collect(Collectors.toMap(Task::getId, Task::getFeedbackQuestion));
        Map<Long, String> userIdToPhoneString = userRepository
                .findAll()
                .stream()
                .collect(Collectors.toMap(User::getId, User::getPhone));

        return list
                .stream()
                .map(feedback -> new FeedbackTable(
                        userIdToPhoneString.get(feedback.getUserId()),
                        taskIdToQuestionString.get(feedback.getTaskId()),
                        feedback.getResponse()
                ))
                .toList();
    }
}
