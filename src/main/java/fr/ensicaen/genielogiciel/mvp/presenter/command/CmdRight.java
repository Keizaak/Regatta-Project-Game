package fr.ensicaen.genielogiciel.mvp.presenter.command;

import fr.ensicaen.genielogiciel.mvp.model.Model;

public class CmdRight extends Command {
    public CmdRight(Model model) {
        super(model);
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
