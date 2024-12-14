package attendance.view;

import static attendance.controller.AttendanceController.NOW;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OutputView {

    public static final DateTimeFormatter HOUR_MINUTE_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final String ADDED_ATTENDANCE_ANNOUNCE_FORMAT = "%d월 %d일 화요일 %s (출석)";

    public void printError(String message) {
        System.out.println(message);
    }

    public void printAddedAttendance(LocalDateTime attendanceDateTime) {
        System.out.println(String.format(ADDED_ATTENDANCE_ANNOUNCE_FORMAT, NOW.getMonthValue(), NOW.getDayOfMonth(),
                NOW.getDayOfWeek(), attendanceDateTime.format(HOUR_MINUTE_FORMATTER)));
    }
}
