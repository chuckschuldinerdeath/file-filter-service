package ru.arslanov.filefilter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import ru.arslanov.filefilter.config.CommandLineConfig;
import ru.arslanov.filefilter.enums.StatisticMode;
import ru.arslanov.filefilter.service.BufferedWriterFactory;
import ru.arslanov.filefilter.service.FileFilterService;
import ru.arslanov.filefilter.service.IdentifyTypeService;
import ru.arslanov.filefilter.service.OutputFileFactory;
import ru.arslanov.filefilter.service.StatisticFactory;
import ru.arslanov.filefilter.service.StatisticPrinter;
import ru.arslanov.filefilter.util.OptionUtil;

public class FileFilterApplication {

    public static void main(String[] args) {
        Options options = new CommandLineConfig().buildOptions();

        try {
            String outPutDirectory = "";
            String fileNamePrefix = "";
            boolean appendModeIsEnable = false;
            StatisticMode statisticMode = null;

            CommandLine commandLine = new DefaultParser().parse(options, args);

            if (commandLine.hasOption(OptionUtil.FILENAME_PREFIX_FLAG)) {
                fileNamePrefix = commandLine.getOptionValue(OptionUtil.FILENAME_PREFIX_FLAG);
            }

            if (commandLine.hasOption(OptionUtil.OUTPUT_DIRECTORY_FLAG)) {
                outPutDirectory = commandLine.getOptionValue(OptionUtil.OUTPUT_DIRECTORY_FLAG) + "/";
            }

            if (commandLine.hasOption(OptionUtil.SHORT_STATISTICS_FLAG) && commandLine.hasOption(OptionUtil.FULL_STATISTICS_FLAG)) {
                throw new ParseException("Нельзя одновременно передавать оба режима вывода статистики.");
            }

            if (commandLine.hasOption(OptionUtil.SHORT_STATISTICS_FLAG)) {
                statisticMode = StatisticMode.SHORT;
            }

            if (commandLine.hasOption(OptionUtil.FULL_STATISTICS_FLAG)) {
                statisticMode = StatisticMode.FULL;
            }

            if (statisticMode == null) {
                throw new ParseException("Укажите один из вариантов вывода статистики.");
            }

            if (commandLine.hasOption(OptionUtil.APPEND_MODE_FLAG)) {
                appendModeIsEnable = true;
            }


            IdentifyTypeService identifyTypeService = new IdentifyTypeService();
            OutputFileFactory outputFileFactory = new OutputFileFactory(outPutDirectory, fileNamePrefix);
            BufferedWriterFactory bufferedWriterFactory = new BufferedWriterFactory(outputFileFactory);
            StatisticFactory statisticFactory = new StatisticFactory(statisticMode);
            FileFilterService fileFilterService = new FileFilterService(bufferedWriterFactory, identifyTypeService, statisticFactory);
            StatisticPrinter statisticPrinter = new StatisticPrinter();

            fileFilterService.filter(commandLine.getArgList(), appendModeIsEnable);

            statisticPrinter.print(statisticFactory.getStatistics());

        } catch (ParseException e) {
            new HelpFormatter().printHelp("Доступные опции:", options);
        }
    }
}