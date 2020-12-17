import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int j = Integer.parseInt(args[0]);
        RandomizedQueue<String> stringRandomizedQueue = new RandomizedQueue<>();
        String string;

        while (!StdIn.isEmpty()) {
            string = StdIn.readString();
            stringRandomizedQueue.enqueue(string);
        }

        Iterator<String> stringIterator = stringRandomizedQueue.iterator();
        for (int i = 0; i < j; i++) {
            if (stringIterator.hasNext()) {
                StdOut.println(stringIterator.next());
            }
        }
    }
}
