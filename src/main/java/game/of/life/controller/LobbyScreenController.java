package game.of.life.controller;

import game.of.life.App;
import game.of.life.Controller;
import game.of.life.Resource;
import game.of.life.controller.inGame.InGameScreenController;
import game.of.life.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import javax.inject.Inject;
import javax.inject.Provider;

public class LobbyScreenController implements Controller {

    private final App app;
    private final Provider<RulesScreenController> rulesScreenControllerProvider;
    private final Provider<InGameScreenController> inGameScreenControllerProvider;
    @FXML
    public Rectangle selectedColor1;
    @FXML
    public Rectangle selectedColor2;
    @FXML
    public Rectangle selectedColor3;
    private Game game;

    @Inject
    public LobbyScreenController(App app, Provider<RulesScreenController> rulesScreenControllerProvider, Provider<InGameScreenController> inGameScreenControllerProvider) {
        this.app = app;
        this.rulesScreenControllerProvider = rulesScreenControllerProvider;
        this.inGameScreenControllerProvider = inGameScreenControllerProvider;
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Parent render() {
        FXMLLoader loader = new FXMLLoader(Resource.fxml("LobbyScreen.fxml"));
        loader.setControllerFactory(c -> this);

        final Parent parent;
        try {
            parent = loader.load();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return parent;
    }

    public void rules() {
        final RulesScreenController rulesScreenController = rulesScreenControllerProvider.get();
        app.show(rulesScreenController);
    }

    public void create() {
        final InGameScreenController inGameScreenController = inGameScreenControllerProvider.get();
        if (game == null) {
            game = new Game(selectedColor1.getFill());
        }
        inGameScreenController.setGame(game);
        app.show(inGameScreenController);
    }

    public void colorSelect(MouseEvent mouseEvent) {
        final Rectangle color = (Rectangle) mouseEvent.getSource();
        if (selectedColor1.getFill().equals(color.getFill())) {
            return;
        }
        game = new Game(color.getFill());
        selectedColor1.setFill(color.getFill());
        selectedColor2.setFill(color.getFill());
        selectedColor3.setFill(color.getFill());
    }

    public Game getGame() {
        return game;
    }
}
