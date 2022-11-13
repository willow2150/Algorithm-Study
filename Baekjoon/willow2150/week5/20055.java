import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        Space space;
        Node prev, next;

        public Node(Space space, Node prev) {
            this.space = space;
            this.prev = prev;
        }
    }

    static class Space {
        int number;
        int durability;
        boolean isOccupied;

        public Space(int number, int durability) {
            this.number = number;
            this.durability = durability;
        }
    }

    private static int N, K;

    public static void main(String[] args) {
        Space[] conveyor = input();
        if (conveyor == null) return;
        System.out.print(startMovingConveyor(conveyor));
    }

    public static Space[] input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());
            int numOfConveyorSpaces = N << 1;
            Space[] conveyor = new Space[numOfConveyorSpaces];
            tokenizer = new StringTokenizer(reader.readLine());
            for (int spaceIdx = 0; spaceIdx < numOfConveyorSpaces; spaceIdx++) {
                conveyor[spaceIdx] = new Space(
                        spaceIdx, Integer.parseInt(tokenizer.nextToken())
                );
            }
            return conveyor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int startMovingConveyor(Space[] conveyor) {
        Node head = null, tail = null;
        int up = 0, down = N - 1;
        int conveyorLength = conveyor.length;
        int turn = 0;
        int numOfUnavailable = 0;
        while (numOfUnavailable < K) {
            turn++;
            up = (up - 1 + conveyorLength) % conveyorLength;
            down = (down - 1 + conveyorLength) % conveyorLength;

            if (conveyor[down].isOccupied) {
                conveyor[down].isOccupied = false;
                if (head == tail) {
                    head = tail = null;
                } else {
                    head = head.next;
                    head.prev = null;
                }
            }

            Node iterator = head;
            while (iterator != null) {
                Space space = iterator.space;
                Space nextSpace = conveyor[(iterator.space.number + 1) % conveyorLength];
                if (nextSpace.durability > 0 && !nextSpace.isOccupied) {
                    space.isOccupied = false;
                    nextSpace.isOccupied = true;
                    nextSpace.durability--;
                    iterator.space = nextSpace;
                    if (nextSpace.durability == 0)
                        numOfUnavailable++;
                    if (nextSpace == conveyor[down]) {
                        nextSpace.isOccupied = false;
                        if (head == tail) {
                            head = tail = null;
                        } else {
                            head = head.next;
                            head.prev = null;
                        }
                    }
                }
                iterator = iterator.next;
            }

            if (conveyor[up].durability > 0) {
                conveyor[up].isOccupied = true;
                conveyor[up].durability--;
                if (conveyor[up].durability == 0)
                    numOfUnavailable++;
                if (tail == null) {
                    head = tail = new Node(conveyor[up], null);
                } else {
                    tail.next = new Node(conveyor[up], tail);
                    tail = tail.next;
                }
            }
        }
        return turn;
    }
}
