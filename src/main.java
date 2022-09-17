import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class main {
    public static void main (String Args[]) {
        var intmap = new ArrayList<ArrayList<Integer>>();
        var stringmap = new ArrayList<ArrayList<String>>();
        int counter = 0;

        var src = Path.of("src/map2.txt");

        try (BufferedReader reader = Files.newBufferedReader(src)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                var ylist = new ArrayList<Integer>();
                var sylist = new ArrayList<String>();
                for (int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)) {
                        case '$': {
                            ylist.add(-1);
                            sylist.add("#");
                            break;
                        }
                        case ',': {
                            ylist.add(0);
                            sylist.add(",");
                            break;
                        }
                    }
                }
                intmap.add(ylist);
                stringmap.add(sylist);
            }
        } catch (IOException e) {
            System.err.format("doesnt work");
        }
        counter = 0;
        for (int y = 0; y < intmap.size();y++) {
            for (int x = 0; x < intmap.get(0).size();x++) {
                if (intmap.get(y).get(Math.abs(x + 1)) == 0 || intmap.get(y).get(Math.abs(x - 1)) == 0 || intmap.get(Math.abs(y - 1)).get(x) == 0|| intmap.get(Math.abs(y + 1)).get(x) == 0) {
                    if (stringmap.get(y).get(x + 1) == "d" || stringmap.get(y).get(x - 1) == "d" || stringmap.get(y - 1).get(x) == "d"|| stringmap.get(y + 1).get(x) == "d") {
                        stringmap.get(y).set(x, "n");
                    }
                    else {
                        stringmap.get(y).set(x, "u");
                    }
                }
                else {
                    stringmap.get(y).set(x,"d");
                }
            }
            System.out.println(intmap.get(y));
        }
    }
}
