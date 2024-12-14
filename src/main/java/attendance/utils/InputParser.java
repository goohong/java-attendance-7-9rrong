package attendance.utils;

import static attendance.controller.AttendanceController.NOW;
import static attendance.utils.AttendancesCsvReader.DATE_TIME_FORMAT;

import attendance.model.ErrorCode;
import attendance.model.FeatureSelection;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputParser {

    public static final DateTimeFormatter HOUR_MINUTE_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static FeatureSelection parseFeatureSelection(String input) {
        return FeatureSelection.from(input);
    }

    public static LocalDateTime fromDateTime(String input) {
        try {
            return LocalDateTime.parse(input, DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage());
        }
    }

    public static LocalDateTime fromHourMinute(String input) {
        try {
            LocalTime inputHourMinute = LocalTime.parse(input, HOUR_MINUTE_FORMATTER);
            return NOW.withHour(inputHourMinute.getHour()).withMinute(inputHourMinute.getMinute());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage());
        }
    }

    public static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage());
        }
    }
}
