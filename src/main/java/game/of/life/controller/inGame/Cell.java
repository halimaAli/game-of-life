package game.of.life.controller.inGame;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Cell extends Pane {

    private final int column;
    private final int row;
    private boolean filled = false;
    private String color;

    public Cell(int column, int row) {
        this.column = column;
        this.row = row;

        getStyleClass().add("cell");
        setOpacity(0.9);
        setEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCell);
    }

    public void clickCell(MouseEvent mouseEvent) {
        if (!filled) {
            setStyle("-fx-background-color: " + color);
            filled = true;
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
            filled = false;
        }
    }
}
