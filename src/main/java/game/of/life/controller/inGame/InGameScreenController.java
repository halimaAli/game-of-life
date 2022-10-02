package game.of.life.controller.inGame;

import game.of.life.App;
import game.of.life.Controller;
import game.of.life.Resource;
import game.of.life.controller.LobbyScreenController;
import game.of.life.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import javax.inject.Inject;
import javax.inject.Provider;

public class InGameScreenController implements Controller {

    private final App app;
    private final Provider<LobbyScreenController> lobbyScreenControllerProvider;

    @FXML
    public Pane gamePane;
    private Game game;
    private Grid grid;

    int rows = 15;
    int columns = 30;
    double width = 550;
    double height = 260;


    @Inject
    public InGameScreenController(App app, Provider<LobbyScreenController> lobbyScreenControllerProvider){
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
            gamePane.getChildren().add(grid);

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
    }

    public void leave() {
        final LobbyScreenController lobbyController = lobbyScreenControllerProvider.get();
        app.show(lobbyController);
    }

    public void start() {

    }
}
