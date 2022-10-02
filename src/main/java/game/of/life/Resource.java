package game.of.life;

import java.net.URL;
import java.util.Objects;

public class Resource {
    public static URL fxml(String name) {
        return Objects.requireNonNull(Resource.class.getResource("views/" + name));
    }
}
