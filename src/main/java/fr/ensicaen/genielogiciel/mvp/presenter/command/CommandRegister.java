package fr.ensicaen.genielogiciel.mvp.presenter.command;

import java.util.ArrayList;
import java.util.List;

public class CommandRegister extends CommandFileWriter {
    private List<Command> _commandHistory;

    public CommandRegister() {
        _commandHistory = new ArrayList<>();
    }

    public void addCommand(Command c) {
        _commandHistory.add(c);
    }

    public void saveGame() {
        writeCommands(_commandHistory);
    }
}
