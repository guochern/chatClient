package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import java.net.ServerSocket;
import java.net.Socket;


public class Main extends Application {
	
	public static MainController mainController;
	private static Client client = new Client("localhost",9000);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientInterface.fxml"));
			Parent root = loader.load();
			mainController = (MainController)loader.getController();
			Scene scene = new Scene(root,700,500);
			scene.getStylesheets().add(getClass().getResource("ClientInterface.fxml").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Client");
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(!client.start())
			return;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


	
	
}
