package attendance.model;

import java.time.LocalDateTime;

public record AttendanceRecordDTO(
        String nickname,
        LocalDateTime dateTime
) {
}
