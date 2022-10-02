package game.of.life.controller;

import game.of.life.App;
import game.of.life.Controller;
import game.of.life.Resource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javax.inject.Inject;
import javax.inject.Provider;

public class LobbyScreenController implements Controller {

    private final App app;
    private final Provider<RulesScreenController> rulesScreenControllerProvider;

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
}
