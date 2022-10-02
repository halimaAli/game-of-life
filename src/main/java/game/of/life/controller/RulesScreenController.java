package game.of.life.controller;

import game.of.life.Controller;
import game.of.life.Resource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javax.inject.Inject;

public class RulesScreenController implements Controller {

    @Inject
    public RulesScreenController(){

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

    }
}
