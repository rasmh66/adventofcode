package puzzels.jaar2016;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static puzzels.jaar2016.Point.Kompas.*;

public class Puntenlijst {

    List<Point> deLijst = new ArrayList<>();
    Point huidigPoint = null;
    static Point oplossing = null;

    public Puntenlijst() {
        huidigPoint = new Point( 0,0, NOORD);
        deLijst.add(huidigPoint);
    }

    private void addX(int value) {
        for (int i = 0; i < value; i++) {
            Point p = new Point(huidigPoint.getVarX()+1, huidigPoint.getVarY(), NOORD);
            if (!deLijst.contains(p)) {
                deLijst.add(p);
            } else {
                oplossing = p;
            }
            huidigPoint = p;
        }
    }

    private void minX(int value) {
        for (int i = value; i > 0; i--) {
            Point p = new Point(huidigPoint.getVarX()-1, huidigPoint.getVarY(), NOORD);
            if (!deLijst.contains(p)) {
                deLijst.add(p);
            } else {
                oplossing = p;
            }
            huidigPoint = p;
        }
    }

    private void addY(int value) {
        for (int i = 0; i < value; i++) {
            Point p = new Point(huidigPoint.getVarX(), huidigPoint.getVarY()+1, NOORD);
            if (!deLijst.contains(p)) {
                deLijst.add(p);
            } else {
                oplossing = p;
            }
            huidigPoint = p;
        }
    }

    private void minY(int value) {
        for (int i = value; i > 0; i--) {
            Point p = new Point(huidigPoint.getVarX(), huidigPoint.getVarY()-1, NOORD);
            if (!deLijst.contains(p)) {
                deLijst.add(p);
            } else {
                oplossing = p;
            }
            huidigPoint = p;
        }
    }

    public void addRight(int value) {
        Point.Kompas richting = huidigPoint.getKompas();

        switch (richting) {
            case NOORD -> {
                addX(value);
                huidigPoint.setKompas(OOST);
            }
            case OOST -> {
                minY(value);
                huidigPoint.setKompas(ZUID);
            }
            case WEST -> {
                addY(value);
                huidigPoint.setKompas(NOORD);
            }
            case ZUID -> {
                minX(value);
                huidigPoint.setKompas(WEST);
            }
        }
    }

    public void addLeft(int value) {
        Point.Kompas richting = huidigPoint.getKompas();
        switch (richting) {
            case NOORD -> {
                minX(value);
                huidigPoint.setKompas(WEST);
            }
            case OOST -> {
                addY(value);
                huidigPoint.setKompas(NOORD);
            }
            case WEST -> {
                minY(value);
                huidigPoint.setKompas(ZUID);
            }
            case ZUID -> {
                addX(value);
                huidigPoint.setKompas(OOST);
            }
        }
    }

    public Point getOplossing() {
        return oplossing;
    }

    public int getSom() {
        System.out.println("hp = " + huidigPoint);
        return abs(huidigPoint.getVarX()) + abs(huidigPoint.getVarY());
    }

}
