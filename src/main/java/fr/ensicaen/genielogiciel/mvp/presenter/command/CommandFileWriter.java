package fr.ensicaen.genielogiciel.mvp.presenter.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CommandFileWriter {
    public void writeCommands(List<Command> commands) {
        String commandsUrl = "./src/main/resources/fr/ensicaen/genielogiciel/mvp/command/commands.txt";

        try {
            new File(commandsUrl);

            FileWriter fw = new FileWriter(commandsUrl);

            for (Command c : commands) {
                fw.write(c.getClass().getName() + " \n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
