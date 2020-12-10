package fr.ensicaen.genielogiciel.mvp.presenter.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CommandFileWriter {
    void write(List<Command> commands) throws IOException {
        String commandsURL = "./src/main/resources/fr/ensicaen/genielogiciel/mvp/command/commands.txt";

        new File(commandsURL);

        FileWriter fw = new FileWriter(commandsURL);

        for (Command c : commands) {
            fw.write(c.getClass().getSimpleName() + " " + c.getTime() + "\n");
        }
        fw.close();
    }
}
