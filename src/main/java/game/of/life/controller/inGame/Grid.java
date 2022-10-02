package game.of.life.controller.inGame;

import game.of.life.model.Game;
import javafx.scene.layout.Pane;

public class Grid extends Pane {
    int rows;
    int columns;

    double width;
    double height;
    private final Game game;

    private final Cell[][] cells;

    public Grid(int columns, int rows, double width, double height, Game game) {
        this.columns = columns;
        this.rows = rows;
        this.width = width;
        this.height = height;
        this.game = game;

        cells = new Cell[rows][columns];

    }

    public void add(Cell cell, int column, int row) {

        cells[row][column] = cell;

        double w = width / columns;
        double h = height / rows;
        double x = w * column;
        double y = h * row;

        cell.setColor("#" + game.color().toString().substring(2, 8));
        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefWidth(w);
        cell.setPrefHeight(h);

        getChildren().add(cell);

    }
}
