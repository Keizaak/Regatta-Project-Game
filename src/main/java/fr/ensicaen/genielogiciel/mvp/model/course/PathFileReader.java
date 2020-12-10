package fr.ensicaen.genielogiciel.mvp.model.course;

import fr.ensicaen.genielogiciel.mvp.model.Vector;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PathFileReader {
    public void loadBuoys(List<Buoy> buoys) {
        String buoysUrl = "./src/main/resources/fr/ensicaen/genielogiciel/mvp/course/buoys.txt";

        try (BufferedReader br = Files.newBufferedReader(Paths.get(buoysUrl))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] coords = line.split("[ \n]");
                buoys.add(new Buoy(new Vector(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])),
                        new Vector(Integer.parseInt(coords[2]), Integer.parseInt(coords[3]))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
