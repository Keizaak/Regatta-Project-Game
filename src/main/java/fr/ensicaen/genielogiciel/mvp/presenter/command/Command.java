package fr.ensicaen.genielogiciel.mvp.presenter.command;

public abstract class Command {
    public abstract void execute();
    public abstract void undo();
}
