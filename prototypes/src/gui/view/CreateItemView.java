package gui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateItemView {
    public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("createItem.fxml"));
		primaryStage.setScene(new Scene(root, 450, 500));
		primaryStage.show();
	}
}
