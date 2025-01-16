package ru.arslanov.filefilter.statistics;

public interface Statistic {

    void addValue(String value);
    String getReport();
}
