package fr.ensicaen.genielogiciel.mvp.presenter.command;

import fr.ensicaen.genielogiciel.mvp.model.Model;

public abstract class Command {
    private Model _model;

    public Command(Model model) {
        _model = model;
    }

    public abstract void execute();
    public abstract void undo();
}
