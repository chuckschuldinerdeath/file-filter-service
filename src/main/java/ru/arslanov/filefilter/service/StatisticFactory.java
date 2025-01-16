package ru.arslanov.filefilter.service;

import ru.arslanov.filefilter.enums.DataType;
import ru.arslanov.filefilter.enums.StatisticMode;
import ru.arslanov.filefilter.statistics.BaseStatistic;
import ru.arslanov.filefilter.statistics.FloatFullStatistic;
import ru.arslanov.filefilter.statistics.IntegerFullStatistic;
import ru.arslanov.filefilter.statistics.Statistic;
import ru.arslanov.filefilter.statistics.StringFullStatistic;

import java.util.Map;

public class StatisticFactory {
    private final Map<DataType, Statistic> statistics;

    public StatisticFactory(StatisticMode statisticMode) {
        this.statistics = initialize(statisticMode);
    }

    private Map<DataType, Statistic> initialize(StatisticMode statisticMode) {
        return switch (statisticMode) {
            case SHORT -> Map.of(
                    DataType.INTEGER, new BaseStatistic(),
                    DataType.FLOAT, new BaseStatistic(),
                    DataType.STRING, new BaseStatistic()
            );
            case FULL -> Map.of(
                    DataType.INTEGER, new IntegerFullStatistic(new BaseStatistic()),
                    DataType.FLOAT, new FloatFullStatistic(new BaseStatistic()),
                    DataType.STRING, new StringFullStatistic(new BaseStatistic())
            );
        };
    }

    public Statistic getStatisticHandler(DataType dataType) {
        return statistics.get(dataType);
    }

    public Map<DataType, Statistic> getStatistics() {
        return statistics;
    }

}
