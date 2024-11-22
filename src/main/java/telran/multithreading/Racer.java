package telran.multithreading;

import java.time.Duration;
import java.time.LocalDateTime;

public class Racer extends Thread {
    private Race race;
    private int number;
    private long runningTime;

    public Racer(Race race, int number) {
        this.race = race;
        this.number = number;
    }

    public void run() {
        for (int i = 0; i < race.getDistance(); i++) {
            try {
                sleep(race.getSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        runningTime = Duration.between(race.getStartTime(), LocalDateTime.now()).toMillis();
    }

    public int getNumber() {
        return number;
    }

    public long getRunningTime() {
        return runningTime;
    }
}
