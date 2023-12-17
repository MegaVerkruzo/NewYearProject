package com.commercial.backend.service;

import com.commercial.backend.db.entities.Attempt;
import com.commercial.backend.db.entities.Task;
import com.commercial.backend.model.json.JsonUser;
import com.commercial.backend.security.exception.NotRegisteredException;
import com.commercial.backend.security.exception.NotValidException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class CommonService {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final ConfigService configService;

    public OffsetDateTime getTaskStartTime(Task task) {
        return getTaskStartTime(configService.getStartDate(), configService.getDelta(), task);
    }

    public OffsetDateTime getTasksEndTime() {
        return getTaskEndTime(configService.getStartDate(), configService.getDelta(), configService.getTasksCount());
    }

    public Boolean isAttemptInTask(Task task, Attempt attempt) {
        return getTaskStartTime(task).isBefore(attempt.getDate()) && attempt.getDate().isBefore(
                getTaskStartTime(task).plusMinutes(configService.getDelta())
        );
    }

    public static OffsetDateTime getTaskStartTime(OffsetDateTime startDate, Long delta, Task task) {
        return startDate.plusMinutes(delta * (task.getId() - 1));
    }

    public static OffsetDateTime getTaskEndTime(OffsetDateTime startDate, Long delta, Long taskId) {
        return startDate.plusMinutes(delta * taskId);
    }

    public static Long parseId(String token) throws NotRegisteredException, NotValidException {
        return parseSomething(token, JsonUser::getId);
    }

    public static String parseUsername(String token) throws NotRegisteredException, NotValidException {
        return parseSomething(token, JsonUser::getUsername);
    }

    public static <T> T parseSomething(
            String token,
            Function<JsonUser, T> func
    ) throws NotRegisteredException, NotValidException {
        if (token == null) {
            throw new NotValidException();
        }
        // :TODO add exception handling
        String userJson = URLDecoder.decode(token, StandardCharsets.UTF_8).split("&")[1].split("=")[1];
        T result;
        try {
            result = func.apply(mapper.readValue(userJson, JsonUser.class));
        } catch (JsonProcessingException e) {
            throw new NotValidException();
        }
        return result;
    }

    public static String getWordInUTF8(String word) {
        return new String(word.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }


    public static String getActivePrizes(int activeGifts) {
        return switch (activeGifts) {
            case 0 -> "Вы пока не учавствуете в розыгрыше";
            case 1 -> "Ура, вы участник розыгрыша подарков 1-го уровня.";
            case 2 -> "Поздравляю! Вы участник розыгрыша подарков 2-го уровня.";
            case 3 -> "И вы снова нас поражаете! Вы участник розыгрыша подарков 3-го уровня.";
            case 4 -> "Вы молодец и участник розыгрыша подарков 4-го уровня.";
            case 5 -> "Поздравляем вас! Вы учавствует в розыгрышах всех уровней :)";
            default -> "";
        };
    }

    public static String getNonActivePrizes(int activeGifts) {
        return switch (activeGifts) {
            case 0 -> "Для участия в розыгрыше подарков нужно угадать хотя бы одно слово.";
            case 1 -> "Чтобы участвовать в розыгрше 2-го и более уровней, нужно отгадать больше слов.";
            case 2 -> "Чтобы участвовать в розыгрше 3-го и более уровней, нужно отгадать больше слов.";
            case 3 -> "Чтобы участвовать в розыгрше 4-го и 5-го уровней, нужно отгадать больше слов.";
            case 4 -> "Чтобы участвовать во всех розыгрышах, нужно ответить ещё на одно слово.";
            case 5 -> "Поздравляем вас! Вы учавствует в розыгрышах всех уровней :)";
            default -> "";
        };
    }
}
