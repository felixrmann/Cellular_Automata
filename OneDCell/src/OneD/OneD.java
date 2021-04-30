package OneD;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2021-April-27
 */

public class OneD extends Application {
    @Override
    public void start(Stage primaryStage) {
        int size = 200;
        int pixelSize = 5;

        primaryStage.setTitle("1D Automata");
        primaryStage.setScene(new Scene(new Pane(new OneDLogic(size, pixelSize)), (size * pixelSize), (size * pixelSize)));
        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
