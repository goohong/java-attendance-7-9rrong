package attendance.model;

import static attendance.controller.AttendanceController.NOW;
import static attendance.utils.InputParser.HOUR_MINUTE_FORMATTER;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public enum EducationDateTime {
    MONDAY(LocalTime.parse("13:00", HOUR_MINUTE_FORMATTER), LocalTime.parse("18:00", HOUR_MINUTE_FORMATTER)),
    EXCEPT_MONDAY(LocalTime.parse("10:00", HOUR_MINUTE_FORMATTER),
            LocalTime.parse("18:00", HOUR_MINUTE_FORMATTER));

    public static final List<Integer> EXTRA_HOLIDAY = List.of(25);

    private static final LocalTime CAMPUS_OPEN_TIME = LocalTime.parse("08:00", HOUR_MINUTE_FORMATTER);
    private static final LocalTime CAMPUS_CLOSE_TIME = LocalTime.parse("23:00", HOUR_MINUTE_FORMATTER);

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
        checkIsAttendingDay(attendanceTime);
        return EXCEPT_MONDAY;
    }

    public static void checkIsAttendingDay(LocalDateTime attendanceTime) {
        if (attendanceTime.withMinute(1).getDayOfWeek() == DayOfWeek.SATURDAY
                || attendanceTime.getDayOfWeek() == DayOfWeek.SUNDAY
                || EXTRA_HOLIDAY.contains(attendanceTime.getDayOfMonth())) {
            throw new IllegalArgumentException(ErrorCode.DAY_NOT_CHECKING_ATTENDANCE.getMessage());
        }
    }

    private static void checkCampusOperationTime(LocalDateTime attendanceTime) {
        if (attendanceTime.toLocalTime().isAfter(CAMPUS_CLOSE_TIME) || attendanceTime.toLocalTime()
                .isBefore(CAMPUS_OPEN_TIME)) {
            throw new IllegalArgumentException(ErrorCode.TIME_NOT_OPERATION_TIME.getMessage());
        }
    }

    public AttendanceType getNowAttendanceType(LocalDateTime attendanceTime) {

        checkCampusOperationTime(attendanceTime);

        if (attendanceTime.isAfter(endTime)) {
            return AttendanceType.ABSENT;
        }

        int delayedMinute = (int) Duration.between(startTime, attendanceTime).toMinutes();
        return AttendanceType.fromLateMinutes(delayedMinute);
    }

    public AttendanceType getAtendanceType(LocalDateTime attendanceTime) {

        checkCampusOperationTime(attendanceTime);

        if (attendanceTime.toLocalTime().isAfter(endTime.toLocalTime())) {
            return AttendanceType.ABSENT;
        }

        int delayedMinute = (int) Duration.between(getAppropriateEducationTime(attendanceTime).startTime.toLocalTime(),
                attendanceTime.toLocalTime()).toMinutes();
        return AttendanceType.fromLateMinutes(delayedMinute);
    }
}
