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
                if (race.reportRacer(number, i)) {
                    System.out.println("Rider ahead: " + number);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}