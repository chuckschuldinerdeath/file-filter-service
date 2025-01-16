package ru.arslanov.filefilter.service;

import ru.arslanov.filefilter.enums.DataType;

import java.util.regex.Pattern;

public class IdentifyTypeService {

    private static final Pattern INTEGER_PATTERN = Pattern.compile("-?\\d+");
    private static final Pattern FLOAT_PATTERN = Pattern.compile("-?\\d+\\.\\d+([eE][-+]?\\d+)?");

    public DataType identifyType(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Строка не может быть пустой или NULL.");
        }

        if (INTEGER_PATTERN.matcher(input).matches()) {
            return DataType.INTEGER;
        }

        if (FLOAT_PATTERN.matcher(input).matches()) {
            return DataType.FLOAT;
        }

        return DataType.STRING;
    }
}
