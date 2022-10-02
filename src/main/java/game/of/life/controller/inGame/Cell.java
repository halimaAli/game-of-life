package game.of.life.controller.inGame;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Cell extends Pane {

    public final int column;
    public final int row;
    private boolean filled = false;
    private String color;
    private Grid grid;

    public Cell(int column, int row) {
        this.column = column;
        this.row = row;

        getStyleClass().add("cell");
        setOpacity(0.9);
        setEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCell);
    }

    public void clickCell(MouseEvent mouseEvent) {
        if (!filled) {
            create();
        } else {
            clear();
        }
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void clear(){
        if (filled) {
            setStyle("-fx-background-color: white");
            grid.removeFromFilled(this);
            filled = false;
        }
    }

    public void create() {
        setStyle("-fx-background-color: " + color);
        grid.addToFilled(this);
        filled = true;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void check(int neighbors){
        if (filled) {
            if (neighbors < 2 || neighbors > 3){
                clear();
            }
        } else {
            if (neighbors == 3) {
                create();
            }
        }
    }
}
