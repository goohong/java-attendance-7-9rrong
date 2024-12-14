package attendance;

import attendance.controller.AttendanceController;
import attendance.view.InputView;
import attendance.view.OutputView;

public class Application {

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        AttendanceController attendanceController = initializeComponents();
        attendanceController.run();
    }

    private AttendanceController initializeComponents() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        return new AttendanceController(inputView, outputView);
    }
}
