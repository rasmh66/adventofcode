package puzzels.jaar2023;

import org.junit.jupiter.api.Test;

class Puzzel8Test {

//    Puzzel8  p = new Puzzel8();

    @Test
    void arrayLCM() {
        int[] arr = {18, 12, 30};

        int lcm = Puzzel8.arrayLCM(arr);

        System.out.println("lcm = " + lcm);
    }

    @Test
    void test2() {
        int[] arr = {16, 20, 32};

        int lcm = Puzzel8.arrayLCM(arr);

        System.out.println("lcm = " + lcm);
    }

    @Test
    void test3() {
        int[] arr = {13939, 11309, 20777};//, 15517, 17621, 18673};

        int lcm = Puzzel8.arrayLCM(arr);

        System.out.println("lcm = " + lcm);
        //LCM = 599377
    }
}