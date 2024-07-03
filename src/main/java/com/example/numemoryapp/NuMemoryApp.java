package com.example.numemoryapp;

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


public class NuMemoryApp extends Application {

    private static final long DURATION_SECONDS = 6;
    //private ScheduledExecutorService timerThread = Executors.newSingleThreadScheduledExecutor();
    private Timeline timeline;
    private  VBox root;
    private Pane tilePane;
    private List<TileView> tileSequence = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        timeline.stop();
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
        tilePane = populateGrid();

        root.getChildren().set(0, tilePane);

        //timerThread.schedule(() -> {}, DURATION_SECONDS, SECONDS);
        Timeline timeline = new Timeline(new KeyFrame(
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

        for (int i = 1; i <= 9; i++) {
            int randomX = random.nextInt(1280 / 80) ;
            int randomY = random.nextInt(720 / 80);

            Point2D p = new Point2D(randomX, randomY);

            while (usedPoints.contains(p)) {
                randomX = random.nextInt(1280 / 80) ;
                randomY = random.nextInt(720 / 80);

                p = new Point2D(randomX, randomY);
            }

            usedPoints.add(p);

            var tile = new TileView(Integer.toString(i));
            tile.setTranslateX(randomX * 80);
            tile.setTranslateY(randomY * 80);
            tile.setOnMouseClicked(e -> {
                if(tileSequence.isEmpty()) {
                    System.out.println("Game is already Over");
                    return;
                }
                var correctTile = tileSequence.remove(0);

                if (tile == correctTile) {
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