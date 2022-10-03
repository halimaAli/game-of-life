package game.of.life.controller.inGame;

import game.of.life.App;
import game.of.life.Controller;
import game.of.life.Resource;
import game.of.life.controller.LobbyScreenController;
import game.of.life.model.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javax.inject.Inject;
import javax.inject.Provider;

public class InGameScreenController implements Controller {

    private final App app;
    private final Provider<LobbyScreenController> lobbyScreenControllerProvider;

    @FXML
    public VBox gridSpace;
    @FXML
    public Button start_pause_button;
    private Game game;
    private Grid grid;
    private Timeline timeline;

    int rows = 30;
    int columns = 50;
    double width = 980;
    double height = 610;


    @Inject
    public InGameScreenController(App app, Provider<LobbyScreenController> lobbyScreenControllerProvider) {
        this.app = app;
        this.lobbyScreenControllerProvider = lobbyScreenControllerProvider;
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Parent render() {
        FXMLLoader loader = new FXMLLoader(Resource.fxml("InGameScreen.fxml"));
        loader.setControllerFactory(c -> this);

        final Parent parent;
        try {
            parent = loader.load();
            grid = new Grid(columns, rows, width, height, game);

            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    Cell cell = new Cell(column, row);
                    grid.add(cell, column, row);
                }
            }
            gridSpace.getChildren().add(grid);

            timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> grid.startLifeCycle()), new KeyFrame(Duration.millis(100)));

            timeline.setCycleCount(Timeline.INDEFINITE);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return parent;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void reset() {
        grid.reset();
        gridSpace.setDisable(false);
        start_pause_button.setText("START");
        timeline.stop();
    }

    public void leave() {
        final LobbyScreenController lobbyController = lobbyScreenControllerProvider.get();
        app.show(lobbyController);
    }

    public void start() {
        if (start_pause_button.getText().equals("START")) {
            gridSpace.setDisable(true);
            start_pause_button.setText("PAUSE");
            timeline.play();
        } else {
            gridSpace.setDisable(false);
            start_pause_button.setText("START");
            timeline.stop();
        }
    }
}
