package puzzels.common;

import java.util.Arrays;

public class TernaryCounter {
    private int[] digits;
    private static final int BASE = 3;

    public TernaryCounter(int size) {
        this.digits = new int[size];
    }
    public TernaryCounter(int size, String value) {
        this.digits = new int[size];
        String.
    }

    public void increment() {
        for (int i = 0; i < digits.length; i++) {
            if (verhoog(i)) break;
        }
        System.out.println("this = " + getValue());
    }

    private boolean verhoog(int index) {
        if (index<digits.length) {
            digits[index]++;
            if (digits[index] < BASE) {
                return true;
            } else {
                digits[index] = 0;
                return verhoog(index+1);
            }
        }
        return false;
    }

    public String getValue() {
        StringBuilder sb = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            sb.append(digits[i]);
        }
        return sb.toString();
    }

}