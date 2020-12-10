package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.LoginMain;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public final class GameView {
    private GamePresenter _gamePresenter;
    private Stage _stage;
    @FXML
    private Canvas _canvas;
    @FXML
    private Label _boatDirection;
    @FXML
    private Label _windSpeed;
    @FXML
    private Label _windDirection;

    private static Parent _root;
    private boolean _hasStarted;

    public static GameView createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameView.class.getResource("SpotMap.fxml"), LoginMain.getMessageBundle());
        _root = fxmlLoader.load();
        final GameView view = fxmlLoader.getController();
        fxmlLoader.setController(view);
        Scene scene = new Scene(_root, 800, 600);
        Stage stage = new Stage();
        stage.setTitle(ResourceBundle.getBundle("fr.ensicaen.genielogiciel.mvp.MessageBundle").getString("project.title"));
        stage.setScene(scene);
        view._stage = stage;
        _root.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.UP) {
                event.consume();
            }
            view.onKeyPressed(event.getCode());
        });
        _root.requestFocus();
        return view;
    }

    public void setPresenter(GamePresenter gamePresenter) {
        _hasStarted = false;
        _gamePresenter = gamePresenter;
        _boatDirection.setText("");
        _windDirection.setText("");
        _windSpeed.setText("");
    }

    public Canvas getCanvas() {
        return _canvas;
    }

    public Label getBoatDirection() {
        return _boatDirection;
    }

    public Label getWindSpeed() {
        return _windSpeed;
    }

    public Label getWindDirection() {
        return _windDirection;
    }

    public void show() {
        _stage.show();
    }

    @FXML
    private void onKeyPressed(KeyCode code) {
//        if (code == KeyCode.SPACE) {
//            _gamePresenter.runGameLoop();
//        }
        if (_hasStarted) {
            if (code == KeyCode.D || code == KeyCode.RIGHT) {
                _gamePresenter.boatRight();
            }
            if (code == KeyCode.Q || code == KeyCode.LEFT) {
                _gamePresenter.boatLeft();
            }
        }
    }

    @FXML
    private void onClickStart(Event event) {
        _hasStarted = true;
        _gamePresenter.runGameLoop();
        Button b = (Button)event.getSource();
        b.setDisable(true);
        _canvas.requestFocus();
    }

    @FXML
    private void onClickMenu(Event event) throws IOException {
        _gamePresenter.getTimeline().stop();

        FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("LoginDialog.fxml"), LoginMain.getMessageBundle());
        Parent root = loader.load();
        LoginView view = loader.getController();
        Scene scene = new Scene(root); //, 400, 120);

        LoginView loginView = LoginView.createView(_stage);
        LoginPresenter presenter = new LoginPresenter();
        view.setPresenter(presenter);
        presenter.setView(loginView);

        _stage.setScene(scene);
    }
}
