package ru.arslanov.filefilter.statistics;

public class FloatFullStatistic extends StatisticDecorator {

    private double minValue = Double.MAX_VALUE;
    private double maxValue = Double.MIN_VALUE;
    private double sum = 0.0;

    public FloatFullStatistic(Statistic wrapped) {
        super(wrapped);
    }

    @Override
    public void addValue(String value) {
        super.addValue(value);
        double doubleValue = Double.parseDouble(value);
        minValue = Math.min(minValue, doubleValue);
        maxValue = Math.max(maxValue, doubleValue);
        sum += doubleValue;
    }

    @Override
    public String getReport() {
        long count = ((BaseStatistic) wrapped).getCount();
        return """
                Статистика чисел с плавающей точкой:
                Минимальное: %.32f
                Максимальное: %.32f
                Сумма: %.32f
                Среднее: %.32f
                %s
                """.formatted(minValue, maxValue, sum, sum / count, super.getReport());
    }
}
