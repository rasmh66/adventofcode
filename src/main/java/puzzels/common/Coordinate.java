package puzzels.common;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    // Constructor
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Methode om de coördinaten weer te geven
    @Override
    public String toString() {
        return "Coordinate{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null ||
                obj.getClass() != this.getClass()) {
            return false;
        }
        Coordinate param = (Coordinate) obj;
        return (this.getX()== param.getX() && this.getY()== param.getY());
    }

    @Override
    public int hashCode() {
        return x*y; //Objects.hash(x,y);
    }

    // Methode om de afstand tot een andere coördinaat te berekenen
    public double distanceTo(Coordinate other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}
