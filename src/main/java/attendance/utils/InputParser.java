package attendance.utils;

import static attendance.utils.AttendancesCsvReader.DATE_TIME_FORMAT;

import attendance.model.FeatureSelection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InputParser {

    public static FeatureSelection parseFeatureSelection(String input) {
        return FeatureSelection.from(input);
    }

    public static LocalDateTime toLocalDateTime(String input) {
        return LocalDateTime.parse(input, DATE_TIME_FORMAT);
    }


}
