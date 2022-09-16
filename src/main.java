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
        //for (ArrayList<Integer> integers : intmap) {
        //    System.out.println(integers);
        //}

        for (int y = 5; y < intmap.size() + 5;) {
            for (int x = 5; x < intmap.get(0).size() + 5;) {
                if (intmap.get(y).get(x + 1) == 0 || intmap.get(y).get(x - 1) == 0 || intmap.get(y - 1).get(x) == 0|| intmap.get(y + 1).get(x) == 0) {
                    stringmap.get(y).set(x,"n");
                }
                if (stringmap.get(y).get(x) == "n") {
                }
            }
        }
    }
}
