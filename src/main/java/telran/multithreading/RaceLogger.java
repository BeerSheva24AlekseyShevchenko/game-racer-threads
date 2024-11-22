package telran.multithreading;

public class RaceLogger extends Thread {
    private final int FREQUENCY = 100;
    private Race race;

    public RaceLogger(Race race) {
        this.race = race;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < race.getDistance()) {
            int winner = race.getReports().get(i);
            if (winner != 0) {
                System.out.println("Rider ahead: " + winner);
                i++;
            }

            try {
                sleep(FREQUENCY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
