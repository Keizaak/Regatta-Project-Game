package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.LoginMain;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public final class GameView {
    private GamePresenter _gamePresenter;
    private Stage _stage;
    @FXML
    private Canvas _canva;

    public static GameView createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameView.class.getResource("SpotMap.fxml"), LoginMain.getMessageBundle());
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

    public Canvas getCanva() {
        return _canva;
    }

    public void show() {
        _stage.show();
    }

    @FXML
    private void onKeyPressed(KeyCode code) {
//        if (code == KeyCode.SPACE) {
//            _gamePresenter.runGameLoop();
//        }
        if (code == KeyCode.D) {
            _gamePresenter.boatRight();
        }
        if (code == KeyCode.Q) {
            _gamePresenter.boatRight();
        }
    }

    @FXML
    private void onClickStart(Event event) {
        _gamePresenter.runGameLoop();
    }
}
