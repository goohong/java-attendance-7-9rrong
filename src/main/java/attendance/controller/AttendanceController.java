package attendance.controller;

import static attendance.utils.InputParser.HOUR_MINUTE_FORMATTER;

import attendance.model.ErrorCode;
import attendance.model.FeatureSelection;
import attendance.model.attendancerecord.AttendanceRecords;
import attendance.utils.AttendancesCsvReader;
import attendance.utils.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AttendanceController {

    public static final LocalDateTime NOW = DateTimes.now();

    private final InputView inputView;
    private final OutputView outputView;
    private final AttendancesCsvReader attendancesCsvReader;
    private final AttendanceRecords attendanceRecords;

    public AttendanceController(InputView inputView, OutputView outputView, AttendancesCsvReader attendancesCsvReader,
                                AttendanceRecords attendanceRecords) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.attendancesCsvReader = attendancesCsvReader;
        this.attendanceRecords = attendanceRecords;
    }

    public void run() {
        startMenu();
    }

    private void startMenu() {
        FeatureSelection featureSelection = InputParser.parseFeatureSelection(inputView.readSelection());

        if (featureSelection == FeatureSelection.CHECK_ATTENDANCE) {
            String nickname = inputView.readNickname();
            LocalDateTime attendanceTime = InputParser.fromHourMinute(inputView.readDate());

            addAttendance(nickname, attendanceTime);
        }

        if (featureSelection == FeatureSelection.MODIFY_ATTENDANCE) {
            String nickname = inputView.readNicknameForModification();
            int dayOfMonth = InputParser.toInt(inputView.readDayOfMonth());
            LocalTime modificationTime = LocalTime.parse(inputView.readDate(), HOUR_MINUTE_FORMATTER);

            modifyAttendance(nickname, dayOfMonth, modificationTime);

        }
    }

    private void modifyAttendance(String nickname, int dayOfMonth, LocalTime modificationTime) {
        if (!attendanceRecords.isExistingNickname(nickname)) {
            outputView.printError(ErrorCode.NICKNAME_NOT_FOUND.getMessage());
        }
        if (attendanceRecords.isExistingNickname(nickname)) {
            outputView.printModifiedAttendance(
                    attendanceRecords.modifyAttendance(nickname, dayOfMonth, modificationTime));
        }
    }

    private void addAttendance(String nickname, LocalDateTime attendanceTime) {
        if (attendanceRecords.isExistingNickname(nickname)) {
            outputView.printError(ErrorCode.ATTENDANCE_ALREADY_ADDED.getMessage());
        }
        if (!attendanceRecords.isExistingNickname(nickname)) {
            outputView.printAddedAttendance(attendanceRecords.addAttendance(nickname, attendanceTime));
        }
    }
}
