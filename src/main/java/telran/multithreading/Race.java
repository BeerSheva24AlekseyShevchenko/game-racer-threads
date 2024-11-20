package telran.multithreading;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private AtomicInteger activeDistance = new AtomicInteger(0);
    private int winner;

    private int distance;
    private int minSleepTime;
    private int maxSleepTime;

    public Race(int distance, int minSleepTime, int maxSleepTime) {
        this.distance = distance;
        this.minSleepTime = minSleepTime;
        this.maxSleepTime = maxSleepTime;
    }

    public int getDistance() {
        return distance;
    }

    public int getActiveDistance() {
        return activeDistance.get();
    }

    public int getSleepTime() {
        Random random = new Random();
        return random.nextInt(maxSleepTime - minSleepTime + 1) + minSleepTime;
    }

    public boolean setWinner(int winner, int distance) {
        boolean isValid = activeDistance.compareAndSet(distance - 1, distance);
        if (isValid) this.winner = winner;
        return isValid;
    }

    public int getWinner() {
        return winner;
    }

    public boolean isFinished() {
        return distance == activeDistance.get();
    }
}
