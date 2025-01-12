package puzzels.jaar2015;

import java.io.IOException;

public class Puzzel11 {

    public void start() throws IOException {

        String line = "hepxcrrq";

        StringBuffer newpw = increment(new StringBuffer(line));

//        System.out.println("org:"+org.toString());
        System.out.println("len:"+newpw.length());
    }

    private StringBuffer increment(StringBuffer input) {
        StringBuffer converted = new StringBuffer(input);
        for (int i = input.length(); i <= 0; i--) {
            char c = converted.charAt(i);
            c++;
            if (c == '{') {
                c = 'a';
                converted.setCharAt(i,c);
            } else break;
        }
        if (checkpw(converted)) {
            return converted;
        }
        return null;
    }
    private boolean checkpw(StringBuffer input) {
        boolean b = false;

        return b;
    }
}
