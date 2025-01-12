package puzzels.jaar2022;

public class Iod {
    private String naam;
    private typeIod type;
    private int grote;

    Iod(String naam, typeIod type, int grote) {
        this.naam = naam;
        this.type = type;
        this.grote = grote;
    }

    public String getNaam() {
        return naam;
    }

    public typeIod getType() {
        return type;
    }

    public int getGrote() {
        return grote;
    }

    @Override
    public String toString() {
        return "puzzels.Iod{" +
                "naam='" + naam + '\'' +
                ", type=" + type +
                ", grote=" + grote +
                '}';
    }

    public void verhoog(Integer i) {
        if(this.type == typeIod.D) {
            this.grote += i;
        } else {
            System.out.println("Error: File kan niet worden verhoogd !!!");
        }
    }
}
