package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.Model;
import fr.ensicaen.genielogiciel.mvp.model.boat.Boat;
import fr.ensicaen.genielogiciel.mvp.view.GameView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.net.URL;

public final class GamePresenter {
    private final Model _model;
    private GameView _view;
    private GraphicsContext _context;
    private Image _boat_image;
    private int _img_size = 50;

    public GamePresenter(String nickName) {
        _model = new Model();
        _model.setNickname(nickName);
        _boat_image = new Image(GameView.class.getResource("boat.png").toString());
    }

    public void setView(GameView view) {
        _view = view;
        _context = _view.getCanva().getGraphicsContext2D();
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
    }

    private void render() {
        // Display the result on the view
        //Dummy boat rendering

        //TODO: render at boat position
        _context.drawImage(_boat_image,150,150,_img_size,_img_size);
//        System.out.println("Un tour de jeu");
    }

    public void boatRight() {
        //something to make the boat turn right
    }

    public void boatLeft() {
        //something to make the boat turn left
    }




}