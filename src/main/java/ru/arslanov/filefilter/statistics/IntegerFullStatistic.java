package ru.arslanov.filefilter.statistics;

public class IntegerFullStatistic extends StatisticDecorator {

    private long minValue = Long.MAX_VALUE;
    private long maxValue = Long.MIN_VALUE;
    private long sum = 0L;

    public IntegerFullStatistic(Statistic wrapped) {
        super(wrapped);
    }

    @Override
    public void addValue(String value) {
        super.addValue(value);
        long integerValue = Long.parseLong(value);
        minValue = Math.min(minValue, integerValue);
        maxValue = Math.max(maxValue, integerValue);
        sum += integerValue;
    }

    @Override
    public String getReport() {
        long count = ((BaseStatistic) wrapped).getCount();
        return """
                Статистика целых чисел:
                Минимальное: %d
                Максимальное: %d
                Сумма: %d
                Среднее: %f
                %s
                """.formatted(minValue, maxValue, sum, sum / (double) count, super.getReport());
    }
}
