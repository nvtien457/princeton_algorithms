/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class HelloGoodbye {
    public static void main(String[] args) {
        String hello = String.format("Hello %s and %s.", args[0], args[1]);
        String goodbye = String.format("Goodbye %s and %s.", args[1], args[0]);

        System.out.println(hello);
        System.out.println(goodbye);
    }
}
