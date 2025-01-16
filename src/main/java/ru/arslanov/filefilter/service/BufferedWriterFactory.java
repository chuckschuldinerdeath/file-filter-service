package ru.arslanov.filefilter.service;

import ru.arslanov.filefilter.enums.DataType;
import ru.arslanov.filefilter.exception.OutPutStreamClosingException;
import ru.arslanov.filefilter.exception.FileCreationException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BufferedWriterFactory {

    private final OutputFileFactory outputFileFactory;
    private final Map<DataType, BufferedWriter> writers;

    public BufferedWriterFactory(OutputFileFactory outputFileFactory) {
        this.outputFileFactory = outputFileFactory;
        this.writers = new HashMap<>();
    }

    public BufferedWriter getBufferedWriter(DataType dataType, boolean appendMode) {
        if (!writers.containsKey(dataType)) {
            String outputFile = outputFileFactory.getFileNameFromDataType(dataType);
            try {
                writers.put(dataType, new BufferedWriter(new FileWriter(outputFile, appendMode)));
            } catch (IOException e) {
                throw new FileCreationException("Произошла ошибка при создании файла '%s'.".formatted(outputFile));
            }
        }

        return writers.get(dataType);
    }

    public void closeAll() {
        for (Map.Entry<DataType, BufferedWriter> entry : writers.entrySet()) {
            try {
                entry.getValue().close();
            } catch (IOException e) {
                throw new OutPutStreamClosingException("Произошла ошибка при закрытии потока.");
            }
        }
    }
}
