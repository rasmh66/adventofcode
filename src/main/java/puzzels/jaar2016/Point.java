package puzzels.jaar2016;

public class Point {

    public enum Kompas {
        NOORD,
        WEST,
        ZUID,
        OOST
    }


    //    private final Puzzel01 puzzel;
    private int varX;
    private int varY;
    private Kompas k = Kompas.NOORD;
//        boolean vorige = true; // false = right

    public Point() {
        this.varX = 0;
        this.varY = 0;
    }

    public Point( int varX, int varY, Kompas k) {
        this.varX = varX;
        this.varY = varY;
        this.k = k;
    }

    public int getVarX() {
        return varX;
    }

    public int getVarY() {
        return varY;
    }
    public Kompas getKompas() {
        return k;
    }
    public void setKompas(Kompas richting) {
        k=richting;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Point))
            return false;
        Point other = (Point) o;

        return this.varX == other.varX && this.varY == other.varY;
    }

    @Override
    public String toString() {
        return "Point{" + varX +
                "," + varY +
                "," + k +
                '}';
    }
}
