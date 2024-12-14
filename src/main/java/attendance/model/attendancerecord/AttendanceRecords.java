package attendance.model.attendancerecord;

import attendance.model.AttendanceSummaryDTO;
import attendance.model.AttendanceType;
import attendance.model.ErrorCode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRecords {
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
        return attendanceRecordToAdd.getAttendanceSummary();
    }

    public boolean isExistingNickname(String nickname) {
        return attendanceRecords.stream().anyMatch(attendanceRecord -> attendanceRecord.isNickname(nickname));
    }

    public String modifyAttendance(String nickname, int dayOfMonth, LocalTime modificationTime) {
        AttendanceRecord attendanceRecordToModify = findRecordByNicknameAndDayValue(nickname, dayOfMonth);
        String summaryBeforeModify = attendanceRecordToModify.getAttendanceSummary();

        attendanceRecordToModify.modifyRecord(modificationTime);
        String summaryAfterModify = attendanceRecordToModify.getAttendanceSummaryAfterModify();

        return summaryBeforeModify + summaryAfterModify;
    }

    public List<String> getAttendance(String nickname) {
        return attendanceRecords.stream()
                .filter(attendanceRecord -> attendanceRecord.isNickname(nickname))
                .map(AttendanceRecord::getAttendanceSummary)
                .toList();
    }

    public AttendanceSummaryDTO getAttendanceSummaryDTO(String nickname) {
        List<AttendanceRecord> attendanceRecordsByNickname = attendanceRecords.stream()
                .filter(attendanceRecord -> attendanceRecord.isNickname(nickname))
                .toList();

        return generateAttendanceSummaryDTO();
    }

    private AttendanceSummaryDTO generateAttendanceSummaryDTO() {
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

        return new AttendanceSummaryDTO(attend, late, absent);
    }

    private AttendanceRecord findRecordByNicknameAndDayValue(String nickname, int dayValue) {
        return attendanceRecords.stream()
                .filter(attendanceRecord -> attendanceRecord.isNickname(nickname))
                .filter(attendanceRecord -> attendanceRecord.isDayValue(dayValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.ATTENDANCE_NOT_FOUND.getMessage()));
    }
}
