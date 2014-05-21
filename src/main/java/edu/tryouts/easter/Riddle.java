package edu.tryouts.easter;

import java.util.Arrays;

/**
 * Class Riddle.
 *
 * @author Erwin Dupont
 * @since 2014-04-10
 */
public class Riddle {
    /**
     * Command line application execution.
     *
     * @param args Array of Strings referencing the command line arguments.
     */
    public static void main(String[] args) {
        boolean[] lockers = new boolean[1000];
        Arrays.fill(lockers, false);

        for (int student = 0; student < lockers.length; student++) {
            for (int locker = 0; locker < lockers.length; locker++) {
                if ((locker+1) % (student+1) == 0) {
                    lockers[locker] = !lockers[locker];
                }
            }
        }

        for (int i = 0; i < lockers.length; i++) {
            if (lockers[i]) {
                System.out.printf("kastje %d is open (= kwadraat van %d)\n", (i + 1), ((int) Math.sqrt(i + 1)));
            }
        }
    }
}
