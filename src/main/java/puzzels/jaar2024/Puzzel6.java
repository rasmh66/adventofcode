package puzzels.jaar2024;

import puzzels.abstractPuzzel;
import puzzels.common.ArrayGrid;
import puzzels.common.Coordinate;
import puzzels.common.Vector2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static puzzels.common.GridUtil.*;

public class Puzzel6 extends abstractPuzzel {

    public enum Compas {
        NOORD,
        OOST,
        ZUID,
        WEST
    }
//    static int aantalStappen =0;
    Set<Coordinate> coordinates = new HashSet<>();
    int blokkades = 0;
    static Vector2 nieuwPunt;

    public Puzzel6(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        Compas r = Compas.NOORD;

        ArrayGrid<Character> grid;
        List<Character[]> lijst = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                char[] charArray = line.toCharArray();

                // Converteer char array naar Character array
                Character[] charObjectArray = new Character[charArray.length];
                for (int i = 0; i < charArray.length; i++) {
                    charObjectArray[i] = charArray[i];
                }
                lijst.add(charObjectArray);
            }
            Character[][] matrix = convertListToCharacterArray(lijst);

            grid = new ArrayGrid<Character>(matrix);
        }
//        paint(grid);
        Vector2 v = findItem(grid, Character.valueOf('^'));
        System.out.println("v = " + v);
        System.out.println("grid = " + grid.rows()+","+grid.cols());

        coordinates.add(new Coordinate(v.getX(), v.getY()));
        try {
            move(grid, v, r);
        } catch (IndexOutOfBoundsException e){
            System.out.println("aantalStappen = " + coordinates.size());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("blokkades = " + blokkades);
    }

    void move(ArrayGrid<Character> grid, Vector2 punt, Compas richting) throws InterruptedException {

        nieuwPunt = new Vector2(punt);
        switch (richting) {
            case NOORD -> nieuwPunt.transform(Vector2.up());
            case OOST  -> nieuwPunt.transform(Vector2.right());
            case ZUID  -> nieuwPunt.transform(Vector2.down());
            case WEST  -> nieuwPunt.transform(Vector2.left());
        }
        if (grid.get(nieuwPunt.getX(),nieuwPunt.getY()).equals('#')) {
            // draai rechts
            switch (richting) {
                case NOORD -> richting = Compas.OOST;
                case OOST  -> richting = Compas.ZUID;
                case ZUID  -> richting = Compas.WEST;
                case WEST  -> richting = Compas.NOORD;
            }
            System.gc();
            Thread.sleep(50);
        } else {
            punt = nieuwPunt;
//            System.out.println("punt = " + punt);
            Coordinate c = new Coordinate(punt.getX(), punt.getY());
            if (coordinates.contains(c)) {
//                Vector2 blok = new Vector2(punt.getX(), punt.getY());
//                switch (richting) {
//                    case NOORD -> blok.transform(Vector2.up());
//                    case OOST  -> blok.transform(Vector2.right());
//                    case ZUID  -> blok.transform(Vector2.down());
//                    case WEST  -> blok.transform(Vector2.left());
//                }
                blokkades++;
            }
            coordinates.add(c);
        }
        move(grid,punt,richting);
    }
}
// 5564

