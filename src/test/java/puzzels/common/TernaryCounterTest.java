package puzzels.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TernaryCounterTest {

    TernaryCounter tc = new TernaryCounter(3);

    @Test
    void increment1() {
        tc.increment();
        String v = tc.getValue();
//        System.out.println("1 = " + v);
        assertEquals("001",v);
    }

    @Test
    void overloop() {
        System.out.println("TEST: Overloop");
        tc.increment();
        tc.increment();
        tc.increment();
        tc.increment();
        tc.increment();
        tc.increment();
        System.out.println("v = " + tc.getValue());
        String v = tc.getValue();
        assertEquals("020",v);
    }
}