package fr.ensicaen.genielogiciel.mvp.presenter.command;

import fr.ensicaen.genielogiciel.mvp.model.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandRegister {
    private final List<Command> _commandHistory;

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

        Command finalCommand = null;
        for (Command c : _commandHistory) {
            previousTime = sleep(previousTime, c);

            c.execute();
            finalCommand = c;
        }
        if (finalCommand != null) {
            finalCommand.getModel().setReplayEnded(true);
        }
    }

    private long sleep(long previousTime, Command c) {
        try {
            Thread.sleep(c.getTime() - previousTime);
            previousTime = c.getTime();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return previousTime;
    }

    public void writeCommands(List<Command> commands) throws IOException{
        new CommandFileWriter().write(commands);
    }

    public void loadCommands(List<Command> commands, Model model) {
        new CommandFileReader().load(commands, model);
    }
}
