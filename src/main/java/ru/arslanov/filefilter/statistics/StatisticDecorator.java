package ru.arslanov.filefilter.statistics;

public abstract class StatisticDecorator implements Statistic {
    protected final Statistic wrapped;

    protected StatisticDecorator(Statistic wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void addValue(String value) {
        wrapped.addValue(value);
    }

    @Override
    public String getReport() {
        return wrapped.getReport();
    }
}
