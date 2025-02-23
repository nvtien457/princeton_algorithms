/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWorld {
    public static void main(String[] args) {
        /*
         * Reads a sequence of words from standard input and prints one of those words uniformly at random.
         * Do not store the words in an array or list.
         * Instead, use Knuth’s method: when reading the i-th word, select it with probability 1/i to be the champion, replacing the previous champion.
         * After reading all of the words, print the surviving champion.
         */
        double i = 1;
        String res = "";
        while (!StdIn.isEmpty()) {
            String inp = StdIn.readString();
            if (StdRandom.bernoulli(1 / i)) {
                res = inp;
            }
            i += 1;
        }
        System.out.println(res);
    }
}
