package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public final class GameView {
    private GamePresenter _gamePresenter;
    private Stage _stage;

    public static GameView createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(GameView.class.getResource("SpotMap.fxml"));
        Parent root = fxmlLoader.load();
        final GameView view = fxmlLoader.getController();
        fxmlLoader.setController(view);
        Scene scene = new Scene(root, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        view._stage = stage;
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> view.onKeyPressed(event.getCode()));
        return view;
    }

    public void setPresenter(GamePresenter gamePresenter) {
        _gamePresenter = gamePresenter;
    }

    public void show() {
        _stage.show();
    }

    private void onKeyPressed(KeyCode code) {
        if (code == KeyCode.SPACE) {
            _gamePresenter.runGameLoop();
        }
    }
}
