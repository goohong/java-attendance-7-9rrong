package attendance.model;

import static attendance.view.OutputView.HOUR_MINUTE_FORMATTER;

import java.time.Duration;
import java.time.LocalDateTime;

public enum EducationDateTime {
    MONDAY(LocalDateTime.parse("13:00", HOUR_MINUTE_FORMATTER), LocalDateTime.parse("18:00", HOUR_MINUTE_FORMATTER)),
    EXCEPT_MONDAY(LocalDateTime.parse("10:00", HOUR_MINUTE_FORMATTER),
            LocalDateTime.parse("18:00", HOUR_MINUTE_FORMATTER));

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    EducationDateTime(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public AttendanceType getAttendanceType(LocalDateTime attendanceTime) {
        if (attendanceTime.isAfter(endTime) || attendanceTime.isBefore(startTime)) {
            return AttendanceType.ABSENT;
        }

        int delayedMinute = (int) Duration.between(startTime, attendanceTime).toMinutes();
        return AttendanceType.fromLateMinutes(delayedMinute);
    }
}
