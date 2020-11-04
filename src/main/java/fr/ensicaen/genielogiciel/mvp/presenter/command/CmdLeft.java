package fr.ensicaen.genielogiciel.mvp.presenter.command;

import fr.ensicaen.genielogiciel.mvp.model.Model;

public class CmdLeft extends Command {

    public CmdLeft(Model model) {
        super(model);
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
