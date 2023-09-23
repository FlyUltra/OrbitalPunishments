package cz.flyultra.utils;

import cz.flyultra.utils.colorapi.ColorAPI;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String colorize(String message) {
        return ColorAPI.colorize(message);
    }

    public static long convertDate(String arg) throws NumberFormatException {
        try {
            Long.parseLong(arg.replace("d", "").replace("h", "").replace("m", "").replace("s", ""));
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }

        String timeStr = arg;
        long timeLong = 0L;

        if (timeStr.endsWith("d")) {
            timeStr = timeStr.replace("d", "");
            timeLong = Long.parseLong(timeStr) * 86400000;

            return timeLong;
        }

        if (timeStr.endsWith("h")) {
            timeStr = timeStr.replace("h", "");
            timeLong = (Long.parseLong(timeStr) * 3600000);

            return timeLong;
        }

        if (timeStr.endsWith("m")) {
            timeStr = timeStr.replace("m", "");
            timeLong = Long.parseLong(timeStr) * 60000;

            return timeLong;
        }

        if (timeStr.endsWith("s")) {
            timeStr = timeStr.replace("s", "");
            timeLong = Long.parseLong(timeStr) * 1000;

            return timeLong;
        }

        return timeLong;
    }

    public static String formatDate(long millis) {
        String format = "";
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        long remainingMinutes = minutes % 60;
        long remainingHours = hours % 24;
        long remainingSeconds = seconds % 60;

        if (days != 0) {
            format = format + (days +"d ");
        }

        if (remainingHours != 0) {
            format = format + (remainingHours +"h ");
        }

        if (remainingMinutes != 0) {
            format = format + (remainingMinutes +"m ");
        }

        if (remainingSeconds != 0) {
            format = format + (remainingSeconds +"s");
        }

        int lastIndex = format.lastIndexOf(' ');

        if (lastIndex >= 0) {

            if (!format.contains("s")) {
                format = format.substring(0, lastIndex) + format.substring(lastIndex + 1);
            }

        }

        return format;
    }
}
