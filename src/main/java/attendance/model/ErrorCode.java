package attendance.model;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.format.TextStyle;
import java.util.Locale;

public enum ErrorCode {
    INVALID_INPUT("잘못된 형식을 입력하였습니다."),
    NICKNAME_NOT_FOUND("등록되지 않은 닉네임입니다."),
    ATTENDANCE_NOT_FOUND("수정할 기록을 찾을 수 없습니다."),
    DAY_NOT_CHECKING_ATTENDANCE(
            DateTimes.now().withMinute(1).getMonthValue() + "월 " + DateTimes.now().withMinute(1).getDayOfMonth() + "일 "
                    + DateTimes.now().withMinute(1).getDayOfWeek().getDisplayName(
                    TextStyle.FULL, Locale.KOREAN) + "은 등교일이 아닙니다."),
    FUTURE_NOT_MODIFIABLE("아직 수정할 수 없습니다."),
    TIME_NOT_OPERATION_TIME("캠퍼스 운영 시간에만 출석이 가능합니다."),
    ATTENDANCE_ALREADY_ADDED("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
