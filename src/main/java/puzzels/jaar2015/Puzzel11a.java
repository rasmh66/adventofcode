package puzzels.jaar2015;
import java.io.IOException;
import java.util.Random;

public class Puzzel11a {

        public static boolean isValidPassword(String password) {
            // Check length
            if (password.length() != 8) {
                return false;
            }

            // Check for forbidden letters
            if (password.contains("i") || password.contains("o") || password.contains("l")) {
                return false;
            }

            // Check for pairs of letters
            int pairCount = 0;
            for (int i = 0; i < password.length() - 1; i++) {
                if (password.charAt(i) == password.charAt(i+1)) {
                    pairCount++;
                    i++;  // Skip the pair to avoid overlapping pairs
                }
            }
            if (pairCount < 2) return false;

            // Check increasing straight
            for (int i = 0; i < password.length() - 2; i++) {
                if (password.charAt(i) == password.charAt(i+1) - 1 &&
                        password.charAt(i) == password.charAt(i+2) - 2) {
                    return true;
                }
            }

            return false;
        }

        public static String generateNewPassword(String previousPassword) {
            String password = previousPassword;
            while (true) {
                StringBuilder newPassword = new StringBuilder(password);
                for (int i = newPassword.length()-1; i >= 0; i--) {
//                    newPassword.insert(0, password.substring(0, i));
                    char c = newPassword.charAt(i);
                    if (c != 'z') {
                        c = (char) (c + 1);
                        newPassword.setCharAt(i,c);
                        break;
                    } else {
                        newPassword.setCharAt(i,'a');
                    }
                }
                password = newPassword.toString();
                if (isValidPassword(password)) {
                    return password;
                }
            }
        }

        public void start() throws IOException {
            String previousPassword = "hepxxyzz";
            String newPassword = generateNewPassword(previousPassword);
            System.out.println("Santa's new password: " + newPassword);
        }

}
