package game.of.life;

import javafx.scene.Parent;

public interface Controller {
    void init();

    void destroy();

    Parent render();
}
