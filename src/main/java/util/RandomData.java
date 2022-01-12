package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomData {
    private static Random random = new Random();
    private static int i = 0;
    private static final String DICT_CHAR = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String DICT_NUM = "1234567890";
    private static final long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
    private static final long maxDay = LocalDate.of(2001, 12, 31).toEpochDay();

    public static String getRandomStringChar(int count) {
        return getRandomString(count, DICT_CHAR);
    }

    public static String getRandomStringNum(int count) {
        return getRandomString(count, DICT_NUM);
    }

    public static String getRandomString(int count, String dict) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < count; i++) {
            word.append(dict.charAt(random.nextInt(dict.length())));
        }
        return word.toString();
    }

    public static String getRandomDate() {
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static String getInnWithNotDuplicate() {
        return String.valueOf((int) ++i);
    }
}