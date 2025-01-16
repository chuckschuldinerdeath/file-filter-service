package ru.arslanov.filefilter.service;

import ru.arslanov.filefilter.enums.DataType;
import ru.arslanov.filefilter.exception.FileCreationException;

import java.io.File;

public class OutputFileFactory {

    public static final String INTEGERS_OUTPUT_FILE = "integers.txt";
    public static final String FLOATS_OUTPUT_FILE = "floats.txt";
    public static final String STRINGS_OUTPUT_FILE = "strings.txt";
    private final String outputDir;
    private final String fileNamePrefix;

    public OutputFileFactory(String outputDir, String fileNamePrefix) {
        this.outputDir = outputDir;
        this.fileNamePrefix = fileNamePrefix;
        createDirectory(outputDir);
    }

    public String getFileNameFromDataType(DataType dataType) {
        return switch (dataType) {
            case INTEGER -> outputDir + fileNamePrefix + INTEGERS_OUTPUT_FILE;
            case FLOAT -> outputDir + fileNamePrefix + FLOATS_OUTPUT_FILE;
            case STRING -> outputDir + fileNamePrefix + STRINGS_OUTPUT_FILE;
        };
    }

    private void createDirectory(String directory) {
        if (!directory.isEmpty() && !directory.isBlank()) {
            File dir = new File(directory);
            if (!dir.exists() && !dir.mkdirs()) {
                throw new FileCreationException("Не удалось создать директорию '%s'.".formatted(dir.getAbsolutePath()));
            }
        }
    }

}
