package telran.multithreading;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandardIO;

public class Main {
    private static final int MAX_THREADS = 10;
    private static final int MIN_THREADS = 2;
    private static final int MIN_DISTANCE = 100;
    private static final int MAX_DISTANCE = 3500;
    private static final int MIN_SLEEP = 2;
    private static final int MAX_SLEEP = 5;

    public static void main(String[] args) {
        InputOutput io = new StandardIO();
        Item[] items = getItems();
        Menu menu = new Menu("Race Game", items);
        menu.perform(io);
    }

    private static Item[] getItems() {
        Item[] res = {
                Item.of("Start new game", Main::startGame),
                Item.ofExit()
        };
        return res;
    }

    static void startGame(InputOutput io) {
        int nThreads = io.readNumberRange("Enter number of the racers", "Wrong number of the racers",
                MIN_THREADS, MAX_THREADS).intValue();
        int distance = io.readNumberRange("Enter distance",
                "Wrong Distance", MIN_DISTANCE, MAX_DISTANCE).intValue();
        Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
        Racer[] racers = new Racer[nThreads];
        startRacers(racers, race);
        witeRacers(racers);
        displayReport(racers);
    }

    private static void displayReport(Racer[] racers) {
        List<Racer> report = Arrays.stream(racers).sorted(Comparator.comparingLong(Racer::getRunningTime)).toList();
        for (int i = 0; i < racers.length; i++) {
            Racer racer = report.get(i);
            System.out.printf(
                "# %d - racer: %d, time: %dms \n",
                i,
                racer.getNumber(),
                racer.getRunningTime()
            );
        }
    }


    private static void startRacers(Racer[] racers, Race race) {
        race.setStartTime(LocalDateTime.now());
        for (int i = 0; i < racers.length; i++) {
            racers[i] = new Racer(race, i + 1);
            racers[i].start();
        }
    }

    private static void witeRacers(Racer[] racers) {
        Arrays.stream(racers).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {

            }
        });
    }
}