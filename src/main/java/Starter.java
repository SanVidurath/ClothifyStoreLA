import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppModule;
import javafx.application.Application;
import javafx.stage.Stage;
import util.Navigator;

public class Starter extends Application {

    public static void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Injector injector = Guice.createInjector(new AppModule());

        Navigator.setStage(stage);
        Navigator.setInjector(injector);

        Navigator.navigateTo("login_form.fxml");
    }
}
