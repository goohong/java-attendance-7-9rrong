package attendance.model.attendancerecord;

import attendance.model.AttendanceSummaryDTO;
import attendance.model.AttendanceType;
import attendance.model.CrewState;
import attendance.model.ErrorCode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class AttendanceRecords {
    public static final int TOTAL_DAYS_TO_ATTEND = 21;
    private final List<AttendanceRecord> attendanceRecords;

    private AttendanceRecords(List<AttendanceRecord> attendanceRecords) {
        this.attendanceRecords = new ArrayList<>(attendanceRecords);
    }

    public static AttendanceRecords from(List<AttendanceRecordDTO> dtos) {
        return new AttendanceRecords(dtos.stream().map(AttendanceRecord::from).toList());
    }

    public String addAttendance(String nickname, LocalDateTime dateTime) {
        AttendanceRecord attendanceRecordToAdd = new AttendanceRecord(nickname, dateTime);
        attendanceRecords.add(attendanceRecordToAdd);
        return attendanceRecordToAdd.getNowAttendanceSummary();
    }

    public boolean isExistingNickname(String nickname) {
        return attendanceRecords.stream().anyMatch(attendanceRecord -> attendanceRecord.isNickname(nickname));
    }

    public String modifyAttendance(String nickname, int dayOfMonth, LocalTime modificationTime) {
        AttendanceRecord attendanceRecordToModify = findRecordByNicknameAndDayValue(nickname, dayOfMonth);
        String summaryBeforeModify = attendanceRecordToModify.getNowAttendanceSummary();

        attendanceRecordToModify.modifyRecord(modificationTime);
        String summaryAfterModify = attendanceRecordToModify.getAttendanceSummaryAfterModify();

        return summaryBeforeModify + summaryAfterModify;
    }

    public List<String> getAttendance(String nickname) {
        return attendanceRecords.stream()
                .filter(attendanceRecord -> attendanceRecord.isNickname(nickname))
                .sorted(Comparator.comparing(AttendanceRecord::getDateTime))
                .map(AttendanceRecord::getAttendanceSummary)
                .toList();
    }

    public List<String> getNowAttendance(String nickname) {
        return attendanceRecords.stream()
                .filter(attendanceRecord -> attendanceRecord.isNickname(nickname))
                .map(AttendanceRecord::getNowAttendanceSummary)
                .toList();
    }

    public AttendanceSummaryDTO getAttendanceSummaryDTO(String nickname) {
        List<AttendanceRecord> attendanceRecordsByNickname = attendanceRecords.stream()
                .filter(attendanceRecord -> attendanceRecord.isNickname(nickname))
                .toList();

        return generateAttendanceSummaryDTO(attendanceRecordsByNickname);
    }

    private AttendanceSummaryDTO generateAttendanceSummaryDTO(List<AttendanceRecord> attendanceRecords) {
        int attend = 0;
        int late = 0;
        int absent = 0;

        for (AttendanceRecord attendanceRecord : attendanceRecords) {
            AttendanceType attendanceType = attendanceRecord.getAttendanceType();
            if (attendanceType == AttendanceType.NORMAL) {
                attend += 1;
            }
            if (attendanceType == AttendanceType.LATE) {
                late += 1;
            }
            if (attendanceType == AttendanceType.ABSENT) {
                absent += 1;
            }
        }

        absent += TOTAL_DAYS_TO_ATTEND - (attend + late + absent);

        return new AttendanceSummaryDTO(attend, late, absent);
    }

    private AttendanceRecord findRecordByNicknameAndDayValue(String nickname, int dayValue) {
        return attendanceRecords.stream()
                .filter(attendanceRecord -> attendanceRecord.isNickname(nickname))
                .filter(attendanceRecord -> attendanceRecord.isDayValue(dayValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.ATTENDANCE_NOT_FOUND.getMessage()));
    }

    public void getAllCrewsWithRiskState() {
        attendanceRecords.stream()
                .map(attendanceRecord -> {
                    Map<AttendanceRecord, CrewState> attendMap = Map.of(
                            attendanceRecord,
                            CrewState.fromAttendanceDTO(this.getAttendanceSummaryDTO(attendanceRecord.getNickname()))
                    );

                    return attendMap;
                }).filter(attendMap -> attendMap.get(1).isInRisk())
                .map(attendMap -> attendMap.get(0));

    }
}
