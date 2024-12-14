package attendance.model.attendancerecord;

import static attendance.controller.AttendanceController.NOW;

import java.time.LocalDateTime;
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

    public void modifyAttendance(String nickname, int dayOfMonth, LocalDateTime modificationTime) {

    }
}
