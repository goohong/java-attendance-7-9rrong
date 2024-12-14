package attendance.model;

import java.util.Arrays;
import java.util.Objects;

public enum FeatureSelection {
    CHECK_ATTENDANCE("1"),
    MODIFY_ATTENDANCE("2"),
    GET_CREW_ATTENDANCE("3"),
    GET_CREW_WITH_RISK_STATE("4"),
    QUIT("Q");

    private final String inputSymbol;

    FeatureSelection(String inputSymbol) {
        this.inputSymbol = inputSymbol;
    }

    public static FeatureSelection from(String input) {
        return Arrays.stream(FeatureSelection.values())
                .filter(featureSelection -> featureSelection.isInputSymbol(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage()));
    }

    private boolean isInputSymbol(String input) {
        return Objects.equals(inputSymbol, input);
    }
}
