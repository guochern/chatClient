package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import application.Client;
import java.io.IOException;
import javafx.scene.input.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.text.JTextComponent;
import javafx.event.EventHandler;
import java.awt.event.KeyListener;

public class MainController implements KeyListener{
	@FXML
	public TextArea textBox;
	public TextArea chatBox;
	private String messages;
	boolean isShiftPressed = false;

	public void sendMessage(ActionEvent  event)
	{
		messages = chatBox.getText();
		Client.sendOverConnection(messages);
		chatBox.setText("");
	}
	
	public void showList(ActionEvent  event)
	{
		Client.sendOverConnection("LIST");
		chatBox.setText("");
	}
	
	public void showStat(ActionEvent  event)
	{
		Client.sendOverConnection("STAT");
		chatBox.setText("");
	}
	
	public void quitChat(ActionEvent  event) throws IOException
	{
		Client.sendOverConnection("QUIT");
		Client.socket.close();
		Platform.exit();
		System.exit(0);

	}



	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getCode()==KeyCode.SHIFT)
		{
			isShiftPressed = true;
		}
		
		if (e.getCode()==KeyCode.ENTER && isShiftPressed == true)
		{
			chatBox.appendText("\n");
		}
		
		else if (e.getCode()==KeyCode.ENTER && isShiftPressed == false)
		{
			messages = chatBox.getText();
			Client.sendOverConnection(messages);
			chatBox.setText("");
			e.consume();
		}
		

	}


	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getCode()==KeyCode.SHIFT)
		{
			isShiftPressed = false;
		}
	}

	
}
