package fr.ensicaen.genielogiciel.mvp.presenter.command;

import fr.ensicaen.genielogiciel.mvp.model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CommandFileReader {
    void load(List<Command> commands, Model model) {
        String commandsURL = "./src/main/resources/fr/ensicaen/genielogiciel/mvp/command/commands.txt";

        try (BufferedReader br = Files.newBufferedReader(Paths.get(commandsURL))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] cmdParams = line.split("[ \n]");

                if (cmdParams[0].equals("CmdLeft")) {
                    commands.add(new CmdLeft(model, Long.parseLong(cmdParams[1])));
                } else {
                    commands.add(new CmdRight(model, Long.parseLong(cmdParams[1])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
