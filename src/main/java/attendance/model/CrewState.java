package attendance.model;

public enum CrewState {
    NONE(""),
    WARNING("경고 대상자"),
    INTERVIEW("면담 대상자"),
    EXPEL("제적 대상자");

    private static final int WARNING_STANDARD = 2;
    private static final int INTERVIEW_STANDARD = 3;
    private static final int EXPEL_STANDARD = 5;
    private final String name;

    CrewState(String name) {
        this.name = name;
    }

    public static CrewState fromAttendanceDTO(AttendanceSummaryDTO attendanceSummaryDTO) {
        int late = attendanceSummaryDTO.late();
        int absent = attendanceSummaryDTO.absent();
        int totalAbsent = absent + (late / 3);

        if (totalAbsent == WARNING_STANDARD) {
            return WARNING;
        }
        if (totalAbsent == INTERVIEW_STANDARD) {
            return INTERVIEW;
        }
        if (totalAbsent > EXPEL_STANDARD) {
            return EXPEL;
        }
        return NONE;
    }

    public String getName() {
        return name;
    }
}
