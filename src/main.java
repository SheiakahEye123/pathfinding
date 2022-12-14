import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class main {
    static ArrayList<ArrayList<Integer>> intmap = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Character>> charmap = new ArrayList<ArrayList<Character>>();
    static int counter = 0;
    static int undiscoverednodes;

    public static void print() {
        for (ArrayList<Character> chare : charmap) {
            System.out.println(chare);
        }
        for (ArrayList<Integer> inte : intmap) {
            for (int inte2 : inte) {
                System.out.printf("%5d",inte2);
            }
            System.out.println();
        }
    }
    public static int arrayGet(int y, int x) {
        if (x <= -1) {
            x = 0;
        }
        if (y <= -1) {
            y = 0;
        }
        if (y >= intmap.size()) {
            y = intmap.size() - 1;
        }
        if (x >= intmap.get(0).size()) {
            x = intmap.get(0).size();
        }
        return intmap.get(y).get(x);
    }
    public static char sArrayGet(int y, int x) {
        if (x <= -1) {
            x = 0;
        }
        if (y <= -1) {
            y = 0;
        }
        if (y >= intmap.size()) {
            y = intmap.size() - 1;
        }
        if (x >= intmap.get(0).size()) {
            x = intmap.get(0).size() - 1;
        }
        return charmap.get(y).get(x);
    }
    public static void main (String[] args) {

        var src = Path.of("src/map2.txt");

        try (BufferedReader reader = Files.newBufferedReader(src)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                var ylist = new ArrayList<Integer>();
                var sylist = new ArrayList<Character>();
                for (int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)) {
                        case '$': {
                            ylist.add(-1);
                            sylist.add('#');
                            break;
                        }
                        case ',': {
                            ylist.add(0);
                            sylist.add('u');
                            break;
                        }
                    }
                }
                intmap.add(ylist);
                charmap.add(sylist);
            }
        } catch (IOException e) {
            System.err.format("doesnt work");
        }
        charmap.get(13).set(45,'d');
        for (int i = 0; i <= 100; i++) {
            var intcopy = new ArrayList<ArrayList<Integer>>();
            var charcopy = new ArrayList<ArrayList<Character>>();
            for (int y = 0; y < intmap.size();y++) {
                var intcope = new ArrayList<Integer>();
                for (int x = 0; x < intmap.get(0).size(); x++) {
                    intcope.add(intmap.get(y).get(x));
                }
                intcopy.add(intcope);
            }
            for (int y = 0; y < charmap.size();y++) {
                var charcope = new ArrayList<Character>();
                for (int x = 0; x < charmap.get(0).size(); x++) {
                    charcope.add(charmap.get(y).get(x));
                }
                charcopy.add(charcope);
            }
            for (int y = 0; y < intmap.size();y++) {
                for (int x = 0; x < intmap.get(0).size();x++) {
                    if ((sArrayGet(y,x - 1) == 'u' || sArrayGet(y,x + 1) == 'u' || sArrayGet(y - 1,x) == 'u' || sArrayGet(y + 1,x) == 'u') && sArrayGet(y,x) == 'd') {
                        charcopy.get(y).set(x, 'n');
                    }
                    if ((sArrayGet(y,x - 1) == 'u' || sArrayGet(y,x + 1) == 'u' || sArrayGet(y - 1,x) == 'u' || sArrayGet(y + 1,x) == 'u') && sArrayGet(y,x) == 'n') {
                        charcopy.get(y).set(x,'d');
                        if (sArrayGet(y,x - 1) == 'u') {
                            charcopy.get(y).set(x - 1,'n');
                            intcopy.get(y).set(x - 1,intmap.get(y).get(x) + 1);
                        }
                        if (sArrayGet(y,x + 1) == 'u') {
                            charcopy.get(y).set(x + 1,'n');
                            intcopy.get(y).set(x + 1,intmap.get(y).get(x) + 1);
                        }
                        if (sArrayGet(y - 1,x) == 'u') {
                            charcopy.get(y - 1).set(x,'n');
                            intcopy.get(y - 1).set(x,intmap.get(y).get(x) + 1);
                        }
                        if (sArrayGet(y + 1,x) == 'u') {
                            charcopy.get(y + 1).set(x,'n');
                            intcopy.get(y + 1).set(x,intmap.get(y).get(x) + 1);
                        }
                    }
                }
            }
            intmap = intcopy;
            charmap = charcopy;
        }
        print();

    }
}
