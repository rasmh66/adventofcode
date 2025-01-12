package puzzels.jaar2016;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Puzzel05 extends abstractPuzzel {

    public Puzzel05(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException, NoSuchAlgorithmException {
        String doorID = "abc";
        int index = 0;

        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] bytesOfMessage = doorID.getBytes("UTF-8");
        byte[] doorIDBytes = doorID.getBytes("US-ASCII");
        System.out.println("len:"+doorIDBytes.length);
        System.out.println(Arrays.toString(doorIDBytes));

        byte[] newArray = Arrays.copyOf(doorIDBytes, doorIDBytes.length);
        if (doorIDBytes.length < 5) {
            newArray = Arrays.copyOf(doorIDBytes, doorIDBytes.length + 5-doorIDBytes.length);
        }
        System.out.println(Arrays.toString(newArray));

        String s = Base64.getEncoder().encodeToString(doorIDBytes);
        System.out.println("MD5: " + s);

        while ((newArray[0] != (byte)0)
                || (newArray[1] != (byte)0)
                || (newArray[2] != (byte)0)
                || (newArray[3] != (byte)0)
                ) {
            byte[] theMD5digest = md.digest(doorIDBytes);
            index++;
            doorIDBytes = theMD5digest;
        }
        System.out.println("index:"+index);
        s = Base64.getEncoder().encodeToString(doorIDBytes);
        System.out.println("MD5: " + s);
    }
}

