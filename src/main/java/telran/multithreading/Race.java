package telran.multithreading;

import java.time.LocalDateTime;
import java.util.Random;

public class Race {
    private int distance;
    private int minSleepTime;
    private int maxSleepTime;
    private LocalDateTime startTime;

    public Race(int distance, int minSleepTime, int maxSleepTime) {
        this.distance = distance;
        this.minSleepTime = minSleepTime;
        this.maxSleepTime = maxSleepTime;
    }

    public int getDistance() {
        return distance;
    }

    public int getSleepTime() {
        Random random = new Random();
        return random.nextInt(maxSleepTime - minSleepTime + 1) + minSleepTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
