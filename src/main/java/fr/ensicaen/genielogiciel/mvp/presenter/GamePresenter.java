package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.Model;
import fr.ensicaen.genielogiciel.mvp.model.boat.Boat;
import fr.ensicaen.genielogiciel.mvp.model.boat.Vector;
import fr.ensicaen.genielogiciel.mvp.presenter.command.CmdLeft;
import fr.ensicaen.genielogiciel.mvp.presenter.command.CmdRight;
import fr.ensicaen.genielogiciel.mvp.presenter.command.Command;
import fr.ensicaen.genielogiciel.mvp.presenter.command.CommandRegister;
import fr.ensicaen.genielogiciel.mvp.view.GameView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;

public final class GamePresenter {
    private final Model _model;
    private GameView _view;
    private GraphicsContext _context;
    private Image _boat_image;
    private int _img_size = 50;
    private CommandRegister _commandRegister;

    public GamePresenter(String nickName) {
        _model = new Model();
        _model.setNickname(nickName);
        _boat_image = new Image(GameView.class.getResource("boat.png").toString());
        _commandRegister = new CommandRegister();
    }

    public void setView(GameView view) {
        _view = view;
        _context = _view.getCanva().getGraphicsContext2D();
        _model.initPosition((float)_view.getCanva().getWidth()/2, (float)_view.getCanva().getHeight()/2);
        Vector position = _model.getRegalataPosition();
        _context.drawImage(_boat_image,position._x,position._y,_img_size,_img_size);
    }

    public void runGameLoop() {
        final int FRAME_PER_SECONDS = 20;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(FRAME_PER_SECONDS), onFinished -> {
            // What is done for each frame
            update();
            render();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void update() {
        // Update the model
        _model.movingForward();

    }

    private void render() {
        // Display the result on the view
        //Dummy boat rendering
        _context.clearRect(0, 0, _view.getCanva().getWidth(), _view.getCanva().getHeight());
        _context.save();
        Vector position = _model.getRegalataPosition();

        rotateBoatImage(_context, _model.getOrientation(),position._x  + _img_size/2.,position._y + _img_size/2.);
        _context.drawImage(_boat_image,position._x,position._y,_img_size,_img_size);
        _context.restore();
//        System.out.println("Un tour de jeu");
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




}