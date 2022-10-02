package game.of.life.controller;

import game.of.life.App;
import game.of.life.Controller;
import game.of.life.Resource;
import game.of.life.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import javax.inject.Inject;
import javax.inject.Provider;

public class LobbyScreenController implements Controller {

    private final App app;
    private final Provider<RulesScreenController> rulesScreenControllerProvider;
    @FXML
    public Circle selectedColor;
    private Game game;

    @Inject
    public LobbyScreenController(App app, Provider<RulesScreenController> rulesScreenControllerProvider) {
        this.app = app;
        this.rulesScreenControllerProvider = rulesScreenControllerProvider;
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

    }

    public void colorSelect(MouseEvent mouseEvent) {
        final Circle color = (Circle) mouseEvent.getSource();
        if (selectedColor.getFill().equals(color.getFill())) {
            return;
        }
        game = new Game(color.getFill());
        selectedColor.setFill(color.getFill());
    }

    public Game getGame(){
        return game;
    }
}
