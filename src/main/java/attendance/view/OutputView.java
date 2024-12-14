package attendance.view;

import attendance.model.AttendanceSummaryDTO;
import attendance.model.CrewState;
import java.util.List;

public class OutputView {

    public static final String ATTENDANCES_SYMBOL_SUMMARY_FORMAT = """
            출석: %d회
            지각: %d회
            결석: %d회""";
    private static final String ATTENDANCES_ANNOUNCE_HEADER_FORMAT = "이번 달 %s의 출석 기록입니다.\n";
    private static final String CREW_STATE_SUMMARY_FORMAT = "%s입니다.";


    public void printError(String message) {
        System.out.println(message);
    }

    public void printAddedAttendance(String message) {
        System.out.println(message);
    }

    public void printModifiedAttendance(String message) {
        System.out.println(message);
    }

    public void printAttendances(String nickname, List<String> messages, AttendanceSummaryDTO dto) {
        CrewState crewState = CrewState.fromAttendanceDTO(dto);

        System.out.println(String.format(ATTENDANCES_ANNOUNCE_HEADER_FORMAT, nickname));
        messages.forEach(System.out::println);
        System.out.println();
        System.out.println(dto);
        System.out.println();

        if (crewState != CrewState.NONE) {
            System.out.println(String.format(CREW_STATE_SUMMARY_FORMAT, crewState.getName()));
        }
    }
}
