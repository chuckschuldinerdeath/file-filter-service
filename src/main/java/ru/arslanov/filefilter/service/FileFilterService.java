package ru.arslanov.filefilter.service;

import ru.arslanov.filefilter.enums.DataType;
import ru.arslanov.filefilter.exception.FileReadingException;
import ru.arslanov.filefilter.statistics.Statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileFilterService {

    private final IdentifyTypeService identifyTypeService;
    private final BufferedWriterFactory bufferedWriterFactory;
    private final StatisticFactory statisticFactory;

    public FileFilterService(BufferedWriterFactory bufferedWriterFactory,
                             IdentifyTypeService identifyTypeService,
                             StatisticFactory statisticFactory) {
        this.bufferedWriterFactory = bufferedWriterFactory;
        this.identifyTypeService = identifyTypeService;
        this.statisticFactory = statisticFactory;
    }

    public void filter(List<String> fileNames, boolean appendMode) {
        try {
            for (String fileName : fileNames) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        DataType dataType = identifyTypeService.identifyType(line);
                        BufferedWriter writer = bufferedWriterFactory.getBufferedWriter(dataType, appendMode);
                        writer.write(line);
                        writer.newLine();
                        statisticFactory.getStatisticHandler(dataType).addValue(line);
                    }
                } catch (IOException e) {
                    throw new FileReadingException("Произошла ошибка при чтении файла '%s'.".formatted(fileName));
                }
            }
        } finally {
            bufferedWriterFactory.closeAll();
        }
    }
}
