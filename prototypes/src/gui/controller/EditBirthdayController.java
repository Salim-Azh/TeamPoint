package gui.controller;

import business_logic.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description of EditBirthdayController.
 * 
 * @author 
 */
public class EditBirthdayController {

	/**
	 * Description of the property userFacade.
	 */
	private UserFacade userFacade;





	@FXML
	private Label messageLabel;



	@FXML
	public void validateOnAction(ActionEvent event) throws IOException{

				Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/profile.fxml"));
				Scene tableViewScene = new Scene(tableViewParent);
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
				messageLabel.setText("");


	}

	@FXML
	public void cancelOnAction(ActionEvent event) throws IOException{
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/profile.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	/**
	 * The constructor.
	 */
	public EditBirthdayController() {
		super();
		userFacade = UserFacade.getUserFacadeInstance();
	}




}
