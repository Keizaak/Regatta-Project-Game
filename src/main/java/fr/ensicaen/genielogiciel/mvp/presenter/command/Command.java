package fr.ensicaen.genielogiciel.mvp.presenter.command;

import fr.ensicaen.genielogiciel.mvp.model.Model;

public abstract class Command {
    private final Model _model;

    private final long _time;

    public Command(Model model) {
        this(model, System.currentTimeMillis());
    }

    public Command(Model model, long time) {
        _model = model;
        _time = time;
    }

    protected Model getModel() {
        return _model;
    }

    public long getTime() { return _time; }

    public abstract void execute();
}
