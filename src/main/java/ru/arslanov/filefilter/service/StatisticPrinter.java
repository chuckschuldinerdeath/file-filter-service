package ru.arslanov.filefilter.service;

import ru.arslanov.filefilter.enums.DataType;
import ru.arslanov.filefilter.statistics.Statistic;

import java.util.Map;

public class StatisticPrinter {

    public void print(Map<DataType, Statistic> statistics) {
        statistics.values().forEach(statistic -> System.out.println(statistic.getReport()));
    }
}
