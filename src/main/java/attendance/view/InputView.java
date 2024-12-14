package attendance.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String FEATURE_SELECTION_PROMPT = """
            오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
            1. 출석 확인
            2. 출석 수정
            3. 크루별 출석 기록 확인
            4. 제적 위험자 확인
            Q. 종료""";

    private static final String NICKNAME_PROMPT = "닉네임을 입력해 주세요.";
    private static final String DATETIME_PROMPT = "등교 시간을 입력해 주세요.";

    public String readSelection() {
        System.out.println(FEATURE_SELECTION_PROMPT);

        return Console.readLine();
    }

    public String readNickname() {
        System.out.println(FEATURE_SELECTION_PROMPT);

        return Console.readLine();
    }

    public String readDate() {
        System.out.println(FEATURE_SELECTION_PROMPT);

        return Console.readLine();
    }

}