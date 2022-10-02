package game.of.life.controller.inGame;

import game.of.life.model.Game;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Grid extends Pane {
    int rows;
    int columns;

    double width;
    double height;
    private final Game game;

    private final Cell[][] cells;
    private final List<Cell> filledCells = new ArrayList<>();
    private final List<Cell> updatedFilledCells = new ArrayList<>();
    private final List<Cell> neighbors = new ArrayList<>();

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
        cell.setGrid(this);

        getChildren().add(cell);

    }

    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].clear();
            }
        }
    }

    public void addToFilled(Cell cell) {
        updatedFilledCells.add(cell);
    }

    public void removeFromFilled(Cell cell) {
        updatedFilledCells.remove(cell);
    }

    public void startLifeCycle() {
        Cell[][] oldCells = cells;
        filledCells.clear();
        filledCells.addAll(updatedFilledCells);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = oldCells[i][j];

                int aboveRow = i - 1 >= 0 ? i - 1 : i;
                int belowRow = i + 1 < rows ? i + 1 : i;
                int nextColumn = j + 1 < columns ? j + 1 : j;
                int prevColumn = j - 1 >= 0 ? j - 1 : j;

                neighbors.add(oldCells[aboveRow][cell.column]); //north
                neighbors.add(oldCells[aboveRow][nextColumn]); //northeast
                neighbors.add(oldCells[cell.row][nextColumn]);//east
                neighbors.add(oldCells[belowRow][nextColumn]);//southeast
                neighbors.add(oldCells[belowRow][cell.column]);//south
                neighbors.add(oldCells[belowRow][prevColumn]);//southWest
                neighbors.add(oldCells[cell.row][prevColumn]);//west
                neighbors.add(oldCells[aboveRow][prevColumn]);//northwest
                cell.check(getAmountNeighbors(neighbors));
                neighbors.clear();
            }
        }
    }

    public int getAmountNeighbors(List<Cell> cells) {
        int amount = 0;
        for (Cell cell : cells) {
            if (filledCells.contains(cell)) {
                amount++;
            }
        }
        return amount;
    }
}
