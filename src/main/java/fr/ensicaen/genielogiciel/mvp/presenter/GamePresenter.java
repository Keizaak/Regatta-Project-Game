package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.Model;
import fr.ensicaen.genielogiciel.mvp.view.GameView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public final class GamePresenter {
    private final Model _model;
    private GameView _view;

    public GamePresenter(String nickName) {
        _model = new Model();
        _model.setNickname(nickName);
    }

    public void setView(GameView view) {
        _view = view;
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
        System.out.println("Un tour de jeu");
    }
}