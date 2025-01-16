package ru.arslanov.filefilter.statistics;

public class StringFullStatistic extends StatisticDecorator {

    private int minLength = Integer.MAX_VALUE;
    private int maxLength = Integer.MIN_VALUE;

    public StringFullStatistic(Statistic wrapped) {
        super(wrapped);
    }

    @Override
    public void addValue(String value) {
        super.addValue(value);
        minLength = Math.min(minLength, value.length());
        maxLength = Math.max(maxLength, value.length());
    }

    @Override
    public String getReport() {
        return """
                Статистика строк:
                Минимальная длина строки: %d
                Максимальная длина строки: %d
                %s
                """.formatted(minLength, maxLength, wrapped.getReport());
    }
}
