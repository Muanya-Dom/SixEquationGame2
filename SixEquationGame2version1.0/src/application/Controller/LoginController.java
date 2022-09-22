package application.Controller;

import java.io.IOException;
import application.StartGUI.RunGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController{

    @FXML private AnchorPane anpLogin;
    

    
    /**
	 * Play as guest
     * @throws IOException 
	 */
    public void GuestPlay(ActionEvent event) throws IOException 
    {
    	// Open Game Play
		openGamePlay(event);
	}
    
    
    /**
	 * Open game play view
     * @throws IOException 
	 */
    private void openGamePlay(ActionEvent event) throws IOException 
    {
		((Node)event.getSource()).getScene().getWindow().hide();
		
		Stage primaryStage = new Stage();		
		Parent root = FXMLLoader.load(getClass().getResource("../View/GamePlay.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Six Equation G2");
		primaryStage.getIcons().add(new Image(RunGUI.class.getResourceAsStream("../Images/se_logo.png")));
		primaryStage.show();
    }
    
    
    
    
}
