package game.of.life;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {
    private final Controller initController;
    private Stage stage;
    private Controller controller;

    public App() {
        final MainComponent mainComponent = DaggerMainComponent.builder().mainApp(this).build();
        initController = mainComponent.startScreenController();
    }


    public App(Controller controller) {
        this.initController = controller;
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setMinWidth(800);
        stage.setMinHeight(500);
        stage.setTitle("Game Of Life");

        final Scene scene = new Scene(new Label("loading"));
        stage.setScene(scene);

        primaryStage.show();

        if (initController != null) {
            show(initController);
        }
    }

    @Override
    public void stop() {
        cleanup();
    }

    public void show(Controller controller) {
        cleanup();
        this.controller = controller;
        controller.init();
        getStage().getScene().setRoot(controller.render());
    }

    private void cleanup() {
        if (controller != null) {
            controller.destroy();
            controller = null;
        }
    }

    public Stage getStage() {
        return stage;
    }
}
