package game.of.life.controller;

import game.of.life.App;
import game.of.life.Controller;
import game.of.life.Resource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javax.inject.Inject;
import javax.inject.Provider;

public class RulesScreenController implements Controller {

    private final App app;
    private final Provider<LobbyScreenController> lobbyScreenControllerProvider;

    @Inject
    public RulesScreenController(App app, Provider<LobbyScreenController> lobbyScreenControllerProvider){

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
        FXMLLoader loader = new FXMLLoader(Resource.fxml("RulesScreen.fxml"));
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

    public void back() {
        final LobbyScreenController lobbyScreenController = lobbyScreenControllerProvider.get();
        app.show(lobbyScreenController);
    }
}
