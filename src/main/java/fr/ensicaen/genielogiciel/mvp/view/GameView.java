package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.LoginMain;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
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
    private static Parent _root;

    public static GameView createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameView.class.getResource("SpotMap.fxml"), LoginMain.getMessageBundle());
        _root = fxmlLoader.load();
        final GameView view = fxmlLoader.getController();
        fxmlLoader.setController(view);
        Scene scene = new Scene(_root, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        view._stage = stage;
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> view.onKeyPressed(event.getCode()));
        _root.requestFocus();
        return view;
    }

    public void setPresenter(GamePresenter gamePresenter) {
    //  _canva.setTranslateX(_canva.getWidth()/2);
    // _canva.setTranslateY(_canva.getHeight()/2);
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
        if (code == KeyCode.D || code == KeyCode.RIGHT) {
            _gamePresenter.boatRight();
        }
        if (code == KeyCode.Q || code == KeyCode.LEFT) {
            _gamePresenter.boatLeft();
        }
    }

    @FXML
    private void onClickStart(Event event) {
        _gamePresenter.runGameLoop();
        _root.requestFocus();
        Button b = (Button)event.getSource();
        b.setDisable(true);
    }
}
