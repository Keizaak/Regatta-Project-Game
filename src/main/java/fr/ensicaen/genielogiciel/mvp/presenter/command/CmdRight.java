package fr.ensicaen.genielogiciel.mvp.presenter.command;

import fr.ensicaen.genielogiciel.mvp.model.Model;

public class CmdRight extends Command {
    public CmdRight(Model model) {
        super(model);
    }
    public CmdRight(Model model, long time) {
        super(model, time);
    }

    @Override
    public void execute() {
        getModel().turnBoatRight();
    }
}
