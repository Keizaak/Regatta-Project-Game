package fr.ensicaen.genielogiciel.mvp.presenter.command;

import fr.ensicaen.genielogiciel.mvp.model.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CommandRegister implements CommandFileWriter, CommandFileReader {
    private final String COMMANDS_URL = "./src/main/resources/fr/ensicaen/genielogiciel/mvp/command/commands.txt";
    private List<Command> _commandHistory;

    public CommandRegister() {
        _commandHistory = new ArrayList<>();
    }

    public void addCommand(Command c) {
        _commandHistory.add(c);
    }

    public void saveGame() {
        try {
            writeCommands(_commandHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(Model model) {
        _commandHistory.clear();
        loadCommands(_commandHistory, model);
    }

    public void replay(Model model) {
        loadGame(model);
        new Thread(this::executeAllCommands).start();
    }

    public void executeAllCommands() {
        long previousTime = 0;
        if (!_commandHistory.isEmpty()) {
            previousTime = _commandHistory.get(0).getTime();
        }
        Command finale = null;
        for (Command c : _commandHistory) {
            try {
                Thread.sleep(c.getTime() - previousTime);
                previousTime = c.getTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            c.execute();
            finale = c;
        }
        if (finale != null) {
            finale.getModel().setReplayEnded(true);
        }
    }

    @Override
    public void writeCommands(List<Command> commands) throws IOException{
        new File(COMMANDS_URL);

        FileWriter fw = new FileWriter(COMMANDS_URL);

        for (Command c : commands) {
            fw.write(c.getClass().getSimpleName() + " " + c.getTime() + "\n");
        }
        fw.close();
    }

    @Override
    public void loadCommands(List<Command> commands, Model model) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(COMMANDS_URL))) {
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
