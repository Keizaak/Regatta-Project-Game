package fr.ensicaen.genielogiciel.mvp.model.course;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PathFileReader {
    public void loadBuoys(List<Buoy> buoys) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("./src/main/resources/fr/ensicaen/genielogiciel/mvp/course/buoys.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] coords = line.split("[ \n]");
                buoys.add(new Buoy(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
