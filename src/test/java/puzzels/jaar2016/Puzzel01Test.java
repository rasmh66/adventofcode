package puzzels.jaar2016;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class Puzzel01Test {

    Puntenlijst testobject;

    @BeforeEach
    void setUp() {
        testobject = new Puntenlijst();
    }

    @Test
    void eersteTest() {
        assertTrue(true);
    }

    @Test
    @DisplayName("add point right test")
    void pointRightTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String direc = "R3";
        testobject.addRight(3);

        assertEquals(3, testobject.getSom());

    }

    @Test
    @DisplayName("handle line test1")
    void handleLineTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String line = "R3, R4";

        testobject.addRight(3);
        testobject.addRight(4);

        System.out.println("p = " + testobject);
        assertEquals(7, testobject.getSom());
    }

    @Test
    @DisplayName("handle line test2")
    void handleLineTest2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("handle line test2");

        String line = "R2, R2, R2";
        testobject.addRight(3);
        testobject.addRight(4);

        System.out.println("p = " + testobject);
        assertEquals(7, testobject.getSom());
    }

    @Test
    @DisplayName("handle line test check oplossing")
    void handleLineTest4() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("handle line test3");

        String line = "R8, R4, R4, R8";

        testobject.addRight(8);
        testobject.addRight(4);
        testobject.addRight(4);
        testobject.addRight(8);

        assertNotEquals(testobject.getOplossing(), null);

        Point o = new Point(4, 0, Point.Kompas.NOORD);
        assertEquals(testobject.getOplossing(), o);
    }
}