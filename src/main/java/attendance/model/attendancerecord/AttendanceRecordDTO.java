package attendance.model.attendancerecord;

import java.time.LocalDateTime;

public record AttendanceRecordDTO(
        String nickname,
        LocalDateTime dateTime
) {
}
