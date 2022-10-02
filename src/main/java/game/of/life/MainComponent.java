package game.of.life;

import dagger.BindsInstance;
import dagger.Component;
import game.of.life.controller.StartScreenController;

import javax.inject.Singleton;

@Component(modules = MainModule.class)
@Singleton
public interface MainComponent {
    StartScreenController startScreenController();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder mainApp(App app);

        MainComponent build();
    }
}
