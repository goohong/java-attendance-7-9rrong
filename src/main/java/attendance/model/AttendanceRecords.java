package attendance.model;

import static attendance.controller.AttendanceController.NOW;

import java.time.LocalDateTime;
import java.util.List;

public class AttendanceRecords {
    private final List<AttendanceRecord> attendanceRecords;

    private AttendanceRecords(List<AttendanceRecord> attendanceRecords) {
        this.attendanceRecords = attendanceRecords;
    }

    public static AttendanceRecords from(List<AttendanceRecordDTO> dtos) {
        return new AttendanceRecords(dtos.stream().map(AttendanceRecord::from).toList());
    }

    public void addAttendance(String nickname, LocalDateTime dateTime) {
        attendanceRecords.add(new AttendanceRecord(nickname, NOW));
    }

    public boolean isExistingNickname(String nickname) {
        return attendanceRecords.stream().anyMatch(attendanceRecord -> attendanceRecord.isNickname(nickname));
    }
}
