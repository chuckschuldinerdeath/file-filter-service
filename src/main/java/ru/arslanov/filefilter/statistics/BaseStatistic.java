package ru.arslanov.filefilter.statistics;

public class BaseStatistic implements Statistic {

    private long count = 0;


    @Override
    public void addValue(String value) {
        count++;
    }

    @Override
    public String getReport() {
        return "Количество элементов: %d".formatted(count);
    }

    public long getCount() {
        return count;
    }
}
