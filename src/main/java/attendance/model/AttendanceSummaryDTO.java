package attendance.model;

import static attendance.view.OutputView.ATTENDANCES_SYMBOL_SUMMARY_FORMAT;

public record AttendanceSummaryDTO(
        int attended,
        int late,
        int absent
) {
    @Override
    public String toString() {
        return String.format(ATTENDANCES_SYMBOL_SUMMARY_FORMAT, attended, late, absent);
    }
}
