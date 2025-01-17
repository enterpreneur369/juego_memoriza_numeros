package com.example.numemoryapp;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TileView extends StackPane {

    private Text text;

    TileView(String content) {
        var border = new Rectangle(80, 80, null);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(4);
        border.setStrokeType(StrokeType.INSIDE);

        text = new Text(content);
        text.setFont(Font.font(64));

        getChildren().addAll(border, text);

        setPickOnBounds(true);
    }

    void hide() {
        text.setVisible(false);
    }

    void show() {
        text.setVisible(true);
    }
}
