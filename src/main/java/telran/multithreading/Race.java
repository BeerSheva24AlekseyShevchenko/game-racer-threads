package telran.multithreading;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

record ReportItem(int number, long time) {
}
public class Race {
    private List<ReportItem> reports = new ArrayList<>();

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

    synchronized public void reportOfFinish(int racer) {
        reports.add(new ReportItem(
            racer,
            Duration.between(startTime, LocalDateTime.now()).toMillis()
        ));
    }

    public List<ReportItem> getReport() {
        return new ArrayList<>(reports);
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
