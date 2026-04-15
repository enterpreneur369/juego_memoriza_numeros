package com.example.numemoryapp;

import com.example.numemoryapp.logic.GameEngine;
import com.example.numemoryapp.logic.GameSession;
import com.example.numemoryapp.logic.ValidationReason;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class NuMemoryApp extends Application {

    private static final long DURATION_SECONDS = 6;
    private static final int GRID_SIZE = 9;
    private Timeline timeline;
    private VBox root;
    private Pane tilePane;
    private final List<TileView> tileSequence = new ArrayList<>();
    private final GameEngine gameEngine = new GameEngine();
    private GameSession gameSession;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        if (timeline != null) {
            timeline.stop();
        }
    }

    private Parent createContent() {
        root = new VBox();
        root.setPrefSize(1280, 720 + 100);

        var button = new Button("Start");
        button.setOnAction(e -> startGame());
        root.getChildren().addAll(new Pane(), button);

        return root;
    }

    private void startGame() {
        tileSequence.clear();
        gameSession = gameEngine.createSession(IntStream.rangeClosed(1, GRID_SIZE).boxed().toList());
        tilePane = populateGrid();

        root.getChildren().set(0, tilePane);

        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(
                Duration.seconds(DURATION_SECONDS),
                event -> {
                    tilePane.getChildren()
                            .stream()
                            .map(n -> (TileView) n)
                            .forEach(TileView::hide);
                }
        ));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private Pane populateGrid() {
        var pane = new Pane();
        pane.setPrefSize(1280, 720);

        Random random = new Random();

        List<Point2D> usedPoints = new ArrayList<>();

        for (int i = 1; i <= GRID_SIZE; i++) {
            int randomX = random.nextInt(1280 / 80) ;
            int randomY = random.nextInt(720 / 80);

            Point2D p = new Point2D(randomX, randomY);

            while (usedPoints.contains(p)) {
                randomX = random.nextInt(1280 / 80) ;
                randomY = random.nextInt(720 / 80);

                p = new Point2D(randomX, randomY);
            }

            usedPoints.add(p);

            final int tileValue = i;
            var tile = new TileView(Integer.toString(tileValue));
            tile.setTranslateX(randomX * 80);
            tile.setTranslateY(randomY * 80);
            tile.setOnMouseClicked(e -> {
                var validationResult = gameEngine.evaluateAttempt(gameSession, tileValue);

                if (validationResult.reason() == ValidationReason.TERMINAL_STATE) {
                    System.out.println("Game is already Over");
                    return;
                }

                if (validationResult.correct()) {
                    if (!tileSequence.isEmpty()) {
                        tileSequence.remove(0);
                    }
                    tile.show();
                } else {
                    tileSequence.clear();
                    System.out.println("Fail: game over");
                }
            });
            pane.getChildren().add(tile);
            tileSequence.add(tile);
        }

        return pane;
    }

    public static void main(String[] args) {
        launch();
    }
}