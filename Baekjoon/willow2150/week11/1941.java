import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static class Seat {
        int row, col;
        int setValue;
        boolean isSom;
        boolean selected;

        public Seat(int row, int col, int setValue, boolean isSom) {
            this.row = row;
            this.col = col;
            this.setValue = setValue;
            this.isSom = isSom;
        }
    }

    private static final int NUM_OF_MEMBERS = 7;
    private static final int NUM_OF_PEOPLE = 25;
    private static final int MAP_SIZE = 5;
    private static final char SOM = 'S';
    private static int numOfCases = 0;

    public static void main(String[] args) throws Exception {
        int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Seat[] map = inputMap();
        countNumOfCases(0, 0, 0, 0, new Seat[NUM_OF_MEMBERS], deltas, map);
        System.out.print(numOfCases);
    }

    public static Seat[] inputMap() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Seat[] map = new Seat[NUM_OF_PEOPLE];
        for (int row = 0; row < MAP_SIZE; row++) {
            String line = reader.readLine();
            for (int col = 0; col < MAP_SIZE; col++) {
                int seatValue = row * MAP_SIZE + col;
                map[seatValue] = new Seat(row, col, seatValue, line.charAt(col) == SOM);
            }
        }
        return map;
    }

    public static void countNumOfCases(int seatValue, int pointIdx,
                                       int numOfSom, int numOfYeon,
                                       Seat[] seats, int[][] deltas, Seat[] map) {
        if (NUM_OF_MEMBERS - pointIdx + numOfSom <= numOfYeon)
            return;
        if (pointIdx == NUM_OF_MEMBERS) {
            if (calcNumOfAdjacentSeats(seats, deltas, map) == NUM_OF_MEMBERS)
                numOfCases++;
            for (Seat seat: seats)
                seat.selected = true;
            return;
        }
        for (int seatV = seatValue; seatV < NUM_OF_PEOPLE; seatV++) {
            seats[pointIdx] = map[seatV];
            seats[pointIdx].selected = true;
            if (seats[pointIdx].isSom) {
                countNumOfCases(
                        seatV + 1, pointIdx + 1,
                        numOfSom + 1, numOfYeon,
                        seats, deltas, map
                );
            } else {
                countNumOfCases(
                        seatV + 1, pointIdx + 1,
                        numOfSom, numOfYeon + 1,
                        seats, deltas, map
                );
            }
            seats[pointIdx].selected = false;
        }
    }

    public static int calcNumOfAdjacentSeats(Seat[] seats, int[][] deltas, Seat[] map) {
        Queue<Seat> queue = new ArrayDeque<>();
        int numOfAdjacentSeats = 0;

        queue.add(seats[0]);
        seats[0].selected = false;
        while (!queue.isEmpty()) {
            Seat seat = queue.poll();
            int row = seat.row;
            int col = seat.col;
            numOfAdjacentSeats++;
            for (int[] delta: deltas) {
                int nr = row + delta[0];
                int nc = col + delta[1];
                if (nr < 0 || nc < 0 || nr == MAP_SIZE || nc == MAP_SIZE) continue;
                Seat anotherSeat = map[nr * MAP_SIZE + nc];
                if (anotherSeat.selected) {
                    anotherSeat.selected = false;
                    queue.add(anotherSeat);
                }
            }
        }
        return numOfAdjacentSeats;
    }
}
