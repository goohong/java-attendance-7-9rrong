package attendance.model;

public enum AttendanceType {
    NORMAL("(출석)"),
    LATE("(지각)"),
    ABSENT("(결석)");

    public static final int LATE_MAX_DELAY_MINUTE = 5;
    public static final int ABSENT_MAX_DELAY_MINUTE = 30;

    private final String attendanceSymbol;

    AttendanceType(String attendanceSymbol) {
        this.attendanceSymbol = attendanceSymbol;
    }

    public String getAttendanceSymbol() {
        return attendanceSymbol;
    }

    public static AttendanceType fromLateMinutes(int value) {
        if (value > LATE_MAX_DELAY_MINUTE && value <= ABSENT_MAX_DELAY_MINUTE) {
            return LATE;
        }
        if (value > ABSENT_MAX_DELAY_MINUTE) {
            return ABSENT;
        }
        return NORMAL;
    }
}
