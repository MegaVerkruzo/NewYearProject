package com.commercial.backend;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.*;

public class Common {
    // :APPROVED
    public static Map<String, Object> pairToMap(String a, Object b) {
        Map<String, Object> map = new HashMap<>();
        map.put(a, b);
        return map;
    }


    public static OffsetDateTime getDeltaUp(OffsetDateTime offsetDateTime) {
        return offsetDateTime.plusMinutes(1);
    }

    public static OffsetDateTime getDeltaDown(OffsetDateTime offsetDateTime) {
        return offsetDateTime.minusMinutes(1);
    }

    public static String getWordInUTF8(String word) {
        return new String(word.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }
}
