package fr.ensicaen.genielogiciel.mvp.presenter.command;

import fr.ensicaen.genielogiciel.mvp.model.Model;

import java.util.List;

public interface CommandFileReader {
    void loadCommands(List<Command> commands, Model model);
}
