package fr.ensicaen.genielogiciel.mvp.presenter.command;

import java.io.IOException;
import java.util.List;

public interface CommandFileWriter {
    void writeCommands(List<Command> commands) throws IOException;
}
