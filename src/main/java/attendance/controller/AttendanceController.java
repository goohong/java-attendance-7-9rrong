package attendance.controller;

import attendance.model.AttendanceRecords;
import attendance.model.ErrorCode;
import attendance.model.FeatureSelection;
import attendance.utils.AttendancesCsvReader;
import attendance.utils.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;

public class AttendanceController {

    public static final LocalDateTime NOW = DateTimes.now();

    private final InputView inputView;
    private final OutputView outputView;
    private final AttendancesCsvReader attendancesCsvReader;

    public AttendanceController(InputView inputView, OutputView outputView, AttendancesCsvReader attendancesCsvReader) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.attendancesCsvReader = attendancesCsvReader;
    }

    public void run() {
        AttendanceRecords attendanceRecords = AttendanceRecords.from(attendancesCsvReader.loadCsvFileToDTO());

        FeatureSelection featureSelection = InputParser.parseFeatureSelection(inputView.readSelection());

        if (featureSelection == FeatureSelection.CHECK_ATTENDANCE) {
            String nickname = inputView.readNickname();
            LocalDateTime attendanceTime = InputParser.toLocalDateTime(inputView.readDate());

            addAttendance(attendanceRecords, nickname, attendanceTime);
        }

    }

    private void addAttendance(AttendanceRecords attendanceRecords, String nickname, LocalDateTime attendanceTime) {
        if (attendanceRecords.isExistingNickname(nickname)) {
            outputView.printError(ErrorCode.ATTENDANCE_ALREADY_ADDED.getMessage());
        }
        attendanceRecords.addAttendance(nickname, attendanceTime);
    }
}
