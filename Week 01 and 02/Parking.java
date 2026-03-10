import java.util.*;

class ParkingLot {
    String[] table;
    int size;

    ParkingLot(int n) {
        size = n;
        table = new String[n];
    }

    int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    int park(String plate) {
        int i = hash(plate);

        while (table[i] != null) {
            i = (i + 1) % size;
        }

        table[i] = plate;
        return i;
    }

    void exit(String plate) {
        for (int i = 0; i < size; i++) {
            if (plate.equals(table[i])) {
                table[i] = null;
                return;
            }
        }
    }
}