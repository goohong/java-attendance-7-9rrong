package attendance.controller;

import attendance.model.AttendanceRecords;
import attendance.utils.AttendancesCsvReader;
import attendance.view.InputView;
import attendance.view.OutputView;

public class AttendanceController {

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


    }
}
