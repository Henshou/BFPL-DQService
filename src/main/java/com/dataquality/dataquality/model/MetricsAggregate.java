package com.dataquality.dataquality.model;

public class MetricsAggregate {
    private long count;
    private long sum;
    private long min = Long.MAX_VALUE;
    private long max = Long.MIN_VALUE;

    public synchronized void addValue(long value) {
        count++;
        sum += value;

        if (value < min) {
            min = value;
        }

        if (value > max) {
            max = value;
        }
    }

    public long getCount() {
        return count;
    }

    public long getSum() {
        return sum;
    }

    public long getMin() {
        return count == 0 ? 0 : min;
    }

    public long getMax() {
        return count == 0 ? 0 : max;
    }

    public double getAverage() {
        return count == 0 ? 0 : (double) sum / count;
    }
}
