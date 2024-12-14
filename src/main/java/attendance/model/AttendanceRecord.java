package attendance.model;

import java.time.LocalDateTime;

public class AttendanceRecord {
    private final String nickname;
    private final LocalDateTime dateTime;

    private AttendanceRecord(String nickname, LocalDateTime dateTime) {
        this.nickname = nickname;
        this.dateTime = dateTime;
    }

    public static AttendanceRecord from(AttendanceRecordDTO dto) {
        return new AttendanceRecord(
                dto.nickname(),
                dto.dateTime()
        );
    }

}
