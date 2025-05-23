package attendance.model.attendancerecord;

import static attendance.controller.AttendanceController.NOW;
import static attendance.utils.InputParser.HOUR_MINUTE_FORMATTER;

import attendance.model.AttendanceType;
import attendance.model.EducationDateTime;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class AttendanceRecord {
    private static final String ATTENDANCE_ANNOUNCE_FORMAT = "%d월 %d일 %s %s %s";
    private static final String MODIFIED_ATTENDANCE_ANNOUNCE_FORMAT = " -> %s %s 수정 완료!";

    private final String nickname;
    private LocalDateTime dateTime;

    public AttendanceRecord(String nickname, LocalDateTime dateTime) {
        this.nickname = nickname;
        this.dateTime = dateTime;
    }

    public static AttendanceRecord from(AttendanceRecordDTO dto) {
        return new AttendanceRecord(
                dto.nickname(),
                dto.dateTime()
        );
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isNickname(String value) {
        return nickname.equals(value);
    }

    public boolean isDayValue(int value) {
        return dateTime.getDayOfMonth() == value;
    }

    public String getNowAttendanceSummary() {
        return String.format(ATTENDANCE_ANNOUNCE_FORMAT,
                NOW.getMonthValue(),
                NOW.getDayOfMonth(),
                NOW.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN),
                dateTime.format(HOUR_MINUTE_FORMATTER),
                EducationDateTime.getAppropriateEducationTime(dateTime)
                        .getNowAttendanceType(dateTime)
                        .getAttendanceSymbol());
    }

    public String getAttendanceSummary() {
        return String.format(ATTENDANCE_ANNOUNCE_FORMAT,
                dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN),
                dateTime.format(HOUR_MINUTE_FORMATTER),
                EducationDateTime.getAppropriateEducationTime(dateTime)
                        .getNowAttendanceType(dateTime)
                        .getAttendanceSymbol());
    }

    public AttendanceType getAttendanceType() {
        return EducationDateTime.getAppropriateEducationTime(dateTime).getNowAttendanceType(dateTime);
    }

    public String getAttendanceSummaryAfterModify() {
        return String.format(MODIFIED_ATTENDANCE_ANNOUNCE_FORMAT,
                dateTime.format(HOUR_MINUTE_FORMATTER),
                EducationDateTime.getAppropriateEducationTime(dateTime)
                        .getNowAttendanceType(dateTime)
                        .getAttendanceSymbol());
    }

    public void modifyRecord(LocalTime modificationTime) {
        dateTime = dateTime.withHour(modificationTime.getHour()).withMinute(modificationTime.getMinute());
    }
}
