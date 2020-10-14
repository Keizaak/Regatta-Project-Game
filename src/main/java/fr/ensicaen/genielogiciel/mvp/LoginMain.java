package fr.ensicaen.genielogiciel.mvp;

import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public final class LoginMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static ResourceBundle getMessageBundle() {
        return ResourceBundle.getBundle("fr.ensicaen.genielogiciel.mvp.MessageBundle");
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle(getMessageBundle().getString("project.title"));
        LoginView view = LoginView.createView(primaryStage);
        LoginPresenter presenter = new LoginPresenter();
        view.setPresenter(presenter);
        presenter.setView(view);
        view.show();
    }
}
