import puzzels.jaar2024.*;

public class MijnAdvent {

    public static void main(String[] args) {

        try {
            var p = new Puzzel7(2024, 99);
//            var p = new PuzzelTest();
            p.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

