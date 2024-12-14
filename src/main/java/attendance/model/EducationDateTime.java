package attendance.model;

import static attendance.controller.AttendanceController.NOW;
import static attendance.utils.InputParser.HOUR_MINUTE_FORMATTER;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public enum EducationDateTime {
    MONDAY(LocalTime.parse("13:00", HOUR_MINUTE_FORMATTER), LocalTime.parse("18:00", HOUR_MINUTE_FORMATTER)),
    EXCEPT_MONDAY(LocalTime.parse("10:00", HOUR_MINUTE_FORMATTER),
            LocalTime.parse("18:00", HOUR_MINUTE_FORMATTER));

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    EducationDateTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = NOW.withHour(startTime.getHour()).withMinute(startTime.getMinute());
        this.endTime = NOW.withHour(endTime.getHour()).withMinute(endTime.getMinute());
    }

    public static EducationDateTime getAppropriateEducationTime(LocalDateTime attendanceTime) {
        if (attendanceTime.getDayOfWeek() == DayOfWeek.MONDAY) {
            return MONDAY;
        }
        return EXCEPT_MONDAY;
    }

    public AttendanceType getAttendanceType(LocalDateTime attendanceTime) {

        if (attendanceTime.isAfter(endTime) || attendanceTime.isBefore(startTime)) {
            return AttendanceType.ABSENT;
        }

        int delayedMinute = (int) Duration.between(startTime, attendanceTime).toMinutes();
        return AttendanceType.fromLateMinutes(delayedMinute);
    }
}
