package telran.multithreading;

import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Race {
    private AtomicIntegerArray reports;

    private int distance;
    private int minSleepTime;
    private int maxSleepTime;

    public Race(int distance, int minSleepTime, int maxSleepTime) {
        this.distance = distance;
        this.minSleepTime = minSleepTime;
        this.maxSleepTime = maxSleepTime;
        this.reports = new AtomicIntegerArray(distance);
    }

    public int getDistance() {
        return distance;
    }

    public int getSleepTime() {
        Random random = new Random();
        return random.nextInt(maxSleepTime - minSleepTime + 1) + minSleepTime;
    }

    public boolean reportRacer(int racer, int distance) {
        return reports.compareAndSet(distance, 0, racer);
    }

    public int getWinner() {
        return reports.get(distance - 1);
    }

    public AtomicIntegerArray getReports() {
        return reports;
    }
}
