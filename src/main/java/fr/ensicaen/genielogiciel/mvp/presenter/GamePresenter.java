package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.Model;
import fr.ensicaen.genielogiciel.mvp.model.Vector;
import fr.ensicaen.genielogiciel.mvp.model.course.Buoy;
import fr.ensicaen.genielogiciel.mvp.presenter.command.CmdLeft;
import fr.ensicaen.genielogiciel.mvp.presenter.command.CmdRight;
import fr.ensicaen.genielogiciel.mvp.presenter.command.Command;
import fr.ensicaen.genielogiciel.mvp.presenter.command.CommandRegister;
import fr.ensicaen.genielogiciel.mvp.view.GameView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.time.Instant;
import java.util.ResourceBundle;

public final class GamePresenter {
    private final Model _model;
    private GameView _view;
    private GraphicsContext _context;
    private Image _boatImage, _buoyImage;
    private int _img_size = 50;
    private CommandRegister _commandRegister;
    private Timeline _timeline;
    private long _unixTimeStart;
    private long _unixTimeEnd;
    private boolean _isReplay;

    public GamePresenter(String nickName) {
        _unixTimeStart = 0;
        _unixTimeEnd = 0;
        _isReplay = false;
        _model = new Model();
        _model.setNickname(nickName);
        _boatImage = new Image(GameView.class.getResource("boat.png").toString());
        _buoyImage = new Image(GameView.class.getResource("buoy.PNG").toString());
        _commandRegister = new CommandRegister();
    }

    public boolean isReplay() {
        return _isReplay;
    }

    public void setView( GameView view) {
        _view = view;
        _context = _view.getCanvas().getGraphicsContext2D();
        _model.initPosition((float)_view.getCanvas().getWidth()/2, (float)_view.getCanvas().getHeight()/2);
        Vector position = _model.getRegalataPosition();
        _context.drawImage(_boatImage,position.x,position.y,_img_size,_img_size);
    }

    public void runGameLoop() {
        //Ces 2 commandes permettent d'initialiser les timer du replay
        boatLeft();
        boatRight();

        _unixTimeStart = Instant.now().getEpochSecond();
        final int FRAME_PER_SECONDS = 20;
        _timeline = new Timeline(new KeyFrame(Duration.millis(FRAME_PER_SECONDS), onFinished -> {
            update();
            render();
        }));
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    public Timeline getTimeline() {
        return _timeline;
    }

    private void update() {
        // Update the model
        _model.movingForward();
        if (!isReplay()) {
            _view.getBoatDirection().setText(_model.getBoatDirection().toString());
            _view.getWindSpeed().setText("" + _model.getWeather().getWindSpeed());
            _view.getWindDirection().setText(_model.getWeather().getWindDirection().toString());
        }

        if (isReplay() && _model.hasReplayEnded()) {
            _timeline.stop();
        }

        if (_model.isGameFinished()) {
            _timeline.stop();
            _commandRegister.saveGame();
            _unixTimeEnd = Instant.now().getEpochSecond();

            _model.replay();
            _model.initPosition((float)_view.getCanvas().getWidth()/2, (float)_view.getCanvas().getHeight()/2);
            Vector position = _model.getRegalataPosition();
            _context.drawImage(_boatImage,position.x,position.y,_img_size,_img_size);

            _view.getBoatDirection().setText("");
            _view.getWindSpeed().setText("");
            _view.getWindDirection().setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle(ResourceBundle.getBundle("fr.ensicaen.genielogiciel.mvp.MessageBundle").getString("winning.title"));
            long timeTaken = _unixTimeEnd-_unixTimeStart;
            alert.setContentText(ResourceBundle.getBundle("fr.ensicaen.genielogiciel.mvp.MessageBundle").getString("winning.content") + timeTaken);
            alert.show();
        }
    }

    private void render() {
        _context.clearRect(0, 0, _view.getCanvas().getWidth(), _view.getCanvas().getHeight());
        _context.save();
        Vector position = _model.getRegalataPosition();

        rotateBoatImage(_context, _model.getOrientation(),position.x + _img_size/2.,position.y + _img_size/2.);
        _context.drawImage(_boatImage,position.x,position.y,_img_size,_img_size);
        _context.restore();
        for (Buoy b : _model.getPath().getBuoys()) {
            _context.drawImage(_buoyImage,b.getPosition().x,b.getPosition().y,_img_size/3,_img_size/3);
        }
    }

    private void rotateBoatImage(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(((angle+90)%360), px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    public void boatRight() {
        Command c = new CmdRight(_model);
        c.execute();
        _commandRegister.addCommand(c);

    }

    public void boatLeft() {
        Command c = new CmdLeft(_model);
        c.execute();
        _commandRegister.addCommand(c);
    }

    public void replay() {
        _isReplay = true;
        runGameLoop();
        _commandRegister.replay(_model);
    }

}