package game.of.life.controller.inGame;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Cell extends Pane {

    int column;
    int row;

    public Cell(int column, int row) {

        this.column = column;
        this.row = row;

        setStyle("-fx-border-color: black");
        setOpacity(0.9);
        setEventHandler(MouseEvent.MOUSE_CLICKED, this::clickCell);
    }

    public void clickCell(MouseEvent mouseEvent) {
        setStyle("-fx-background-color: black");
    }

}
