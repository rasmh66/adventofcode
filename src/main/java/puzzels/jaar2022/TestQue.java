package puzzels.jaar2022;

import java.util.ArrayDeque;
import java.util.Deque;

public class TestQue {
    public void TestQue() {
        Deque<String> dq = new ArrayDeque<String>();

        // add() method to insert
        dq.add("For");
        dq.addFirst("Geeks");
        dq.addLast("Geeks");

        System.out.println(dq);

        // We can remove the first element
        // or the last element.
        dq.removeFirst();
        dq.removeLast();
        System.out.println("Deque after removing "
                + "first and last: "
                + dq);
    }
}