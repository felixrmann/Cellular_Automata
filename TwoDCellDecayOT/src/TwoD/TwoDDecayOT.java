package TwoD;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2021-April-29
 */

public class TwoDDecayOT extends Application {

    @Override
    public void start(Stage primaryStage) {
        int size = 90;
        int pixelSize = 10;

        primaryStage.setTitle("2D Automata decay over time");
        primaryStage.setScene(new Scene(new Pane(new TwoDLogicDecayOT(size, pixelSize)), (size * pixelSize), (size * pixelSize)));
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
