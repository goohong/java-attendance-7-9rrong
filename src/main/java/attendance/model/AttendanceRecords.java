package attendance.model;

import java.util.List;

public class AttendanceRecords {
    private final List<AttendanceRecord> attendanceRecords;

    private AttendanceRecords(List<AttendanceRecord> attendanceRecords) {
        this.attendanceRecords = attendanceRecords;
    }

    public static AttendanceRecords from(List<AttendanceRecordDTO> dtos) {
        return new AttendanceRecords(dtos.stream()
                .map(AttendanceRecord::from)
                .toList());
    }
}
