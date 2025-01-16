package ru.arslanov.filefilter.config;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import ru.arslanov.filefilter.util.OptionUtil;

public class CommandLineConfig {

    public Options buildOptions() {
        return new Options()
                .addOption(
                        Option.builder()
                                .option(OptionUtil.OUTPUT_DIRECTORY_FLAG)
                                .longOpt("output")
                                .hasArg()
                                .desc("Задает путь для результатов.")
                                .build()
                )
                .addOption(
                        Option.builder()
                                .option(OptionUtil.FILENAME_PREFIX_FLAG)
                                .longOpt("prefix")
                                .hasArg()
                                .desc("Задает префикс имен выходных файлов.")
                                .build()
                )
                .addOption(
                        Option.builder()
                                .option(OptionUtil.SHORT_STATISTICS_FLAG)
                                .longOpt("short")
                                .hasArg(false)
                                .desc("Включает вывод краткой статистики.")
                                .build()
                ).addOption(
                        Option.builder()
                                .option(OptionUtil.FULL_STATISTICS_FLAG)
                                .longOpt("full")
                                .hasArg(false)
                                .desc("Включает вывод полной статистики.")
                                .build()
                ).addOption(
                        Option.builder()
                                .option(OptionUtil.APPEND_MODE_FLAG)
                                .longOpt("append")
                                .hasArg(false)
                                .desc("Включает запись результатов в уже существующие файлы.")
                                .build()
                );
    }
}
