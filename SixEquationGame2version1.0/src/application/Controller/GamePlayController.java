package application.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import application.Engine.GameEngine;
import application.StartGUI.RunGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GamePlayController implements Initializable {

	@FXML private AnchorPane anpGamePlay;
    @FXML private ImageView imvGameView;
    @FXML private Label lblAvtName;
    @FXML private Label lblLivesRemaining;
    @FXML private Label lblLiveScore;
    @FXML private Label lblHighestScore;
    
    
	GameEngine myGame = null;
	Image currentGame = null;
	int noOfLives = 3;
	
    /**
	 * Initialises the login controller.
	 */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1)
    {

    		lblAvtName.setText("Welcome Guest");
    
    	
    	// load game engine and pick a game location
    	myGame = new GameEngine("");
		currentGame = myGame.nextGame();
		imvGameView.setImage(currentGame);
    	
    	
    	// set score
    	lblLiveScore.setText("0");
    	
    	// set lives
    	lblLivesRemaining.setText(""+noOfLives);
		
	}
    
    
    /**
	 * Action is performed when button is click.
     * @throws SQLException 
	 */
    public void GameActionPerformed(ActionEvent event) throws SQLException
    {
    	if (noOfLives > 0) {
    	
	    	Button source = (Button)event.getSource();
	    	String source_text = source.getText();
	    	int playerSolution = Integer.parseInt(source_text);
	    	
	    	// setting session cookies
	    	myGame.setSessionCookies();
	    	
	    	boolean isCorrect = myGame.checkSolution(currentGame, playerSolution);
			int score = myGame.getScore();
			
			if (isCorrect)
			{
				// update score
				lblLiveScore.setText(""+score);
				
				// next game
				currentGame = myGame.nextGame();
				imvGameView.setImage(currentGame);
			} 
			else 
			{ 
				// reduce number of lives remaining
				noOfLives = noOfLives - 1;
				lblLivesRemaining.setText(""+noOfLives);
			}
    	}
    	else {
    		
    		// game over
    		File file = new File("src/application/Images/gameover.png");
    		Image gameover = new Image(file.toURI().toString());
    		imvGameView.setImage(gameover);
    	}
    			
	}
    
    
    
    /**
	 * Logs player out and redirects to login window
	 * @param event
	 * @throws IOException
	 */
	public void Logout(ActionEvent event) throws IOException
	{
		
		((Node)event.getSource()).getScene().getWindow().hide();
		
		Stage primaryStage = new Stage();		
		Parent root = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Six Equation G2");
		primaryStage.getIcons().add(new Image(RunGUI.class.getResourceAsStream("../Images/se_logo.png")));
		primaryStage.show();
	}
    
    
    
}
