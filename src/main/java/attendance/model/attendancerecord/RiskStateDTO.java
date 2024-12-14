package attendance.model.attendancerecord;

import attendance.model.CrewState;

public record RiskStateDTO(
        AttendanceRecord attendanceRecord,
        CrewState crewState
) {

}
