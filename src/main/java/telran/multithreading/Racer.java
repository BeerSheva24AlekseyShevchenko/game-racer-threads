package telran.multithreading;

public class Racer extends Thread {
    private Race race;
    private int number;

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

        synchronized (race) {
            race.reportOfFinish(number);
        }
    }
}
