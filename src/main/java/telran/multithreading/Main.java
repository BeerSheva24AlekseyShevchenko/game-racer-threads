package telran.multithreading;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // TODO Add CLI
        int distance = 10;
        int nRacers = 10;

        Race race = new Race(distance, 0, 300);
        RaceLogger raceLogger = new RaceLogger(race);
        Racer[] racers = new Racer[nRacers];
        raceLogger.start();
        startRace(racers, race);
        witeRace(racers);

        try {
            raceLogger.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Racer won: " + race.getWinner());
    }

    private static void witeRace(Racer[] racers) {
        Arrays.stream(racers).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                
            }
        });
    }

    private static void startRace(Racer[] racers, Race race) {
        for (int i = 0; i < racers.length; i++) {
            racers[i] = new Racer(race, i + 1);
            racers[i].start();
        }
    }
}