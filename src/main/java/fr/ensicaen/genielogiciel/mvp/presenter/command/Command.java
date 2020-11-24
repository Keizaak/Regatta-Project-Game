package fr.ensicaen.genielogiciel.mvp.presenter.command;

import fr.ensicaen.genielogiciel.mvp.model.Model;

public abstract class Command {
    private final Model _model;

    public Command(Model model) {
        _model = model;
    }

    protected Model getModel() {
        return _model;
    }

    public abstract void execute();
}
