package attendance.model;

public enum AttendanceType {
    NORMAL,
    LATE,
    ABSENT;

    public static final int LATE_MAX_DELAY_MINUTE = 5;
    public static final int ABSENT_MAX_DELAY_MINUTE = 30;

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
