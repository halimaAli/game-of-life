package game.of.life.controller;

import game.of.life.App;
import game.of.life.Controller;
import game.of.life.Resource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javax.inject.Inject;
import javax.inject.Provider;

public class StartScreenController implements Controller {

    private final App app;
    private final Provider<LobbyScreenController> lobbyControllerProvider;
    @FXML
    public Button startButton;

    @Inject
    public StartScreenController(App app, Provider<LobbyScreenController> lobbyControllerProvider){
        this.app = app;
        this.lobbyControllerProvider = lobbyControllerProvider;
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Parent render() {
        FXMLLoader loader = new FXMLLoader(Resource.fxml("StartScreen.fxml"));
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

    public void start() {
        final LobbyScreenController lobbyController = lobbyControllerProvider.get();
        app.show(lobbyController);
    }
}
