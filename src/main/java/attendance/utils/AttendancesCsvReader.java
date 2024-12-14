package attendance.utils;

import attendance.model.AttendanceRecordDTO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class AttendancesCsvReader {
    private final String filePath;

    private int index;

    public AttendancesCsvReader(String filePath) {
        this.filePath = filePath;
    }

    public List<AttendanceRecordDTO> loadCsvFileToDTO() {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filePath)),
                        StandardCharsets.UTF_8));

        return bufferedReader.lines()
                .skip(1)
                .map(this::parseLineToDTO)
                .toList();
    }

    private AttendanceRecordDTO parseLineToDTO(String line) {
        String[] lineContents = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        return new AttendanceRecordDTO(lineContents[0],
                LocalDateTime.parse(
                        lineContents[1],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                )
        );
    }
}
