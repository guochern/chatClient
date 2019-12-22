package application;

import java.net.*;
import java.io.*;
import java.util.*;
import application.Main;
import application.MainController;

public class Client {
	
	private BufferedReader sInput;		// to read from the socket
	private static PrintWriter sOutput;		// to write on the socket
	public static Socket socket;					// socket object
	private String chatMsg = new String();

	private String server;	
	private int port;	
	
	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
    Client(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	
	
	//need to connect to server, read input then rest server settle
	//user name set from iden function in connection
	
	public boolean start() {
		// try to connect to the server
		try {
			socket = new Socket(server, port);
		} 
		// exception handler if it failed
		catch(Exception ec) {
			System.out.println("Error connectiong to server:" + ec);
			return false;
		}
		
		System.out.println("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
			
		/* Creating both Data Stream */
		try
		{
			sInput  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sOutput = new PrintWriter(socket.getOutputStream(), true);
			
		}
		catch (IOException eIO) {
			System.out.println("Exception creating new Input/output Streams: " + eIO);
			return false;
		}

		 //creates the Thread to listen from the server 
		new ListenFromServer().start();

		
		
		return true;
	}
	


	
	public static void main(String[] args) {
		int portNumber = 9000;
		String serverAddress = "localhost";

		
		Client client = new Client(serverAddress, portNumber);
		// try to connect to the server and return if not connected
		if(!client.start())
			return;
		
		Scanner scan = new Scanner(System.in);

		
		//System.out.println("\nEnter username:");
		//String msg = scan.nextLine();		
		
		//String message ="IDEN "; 

		//String msgg = message + msg;


		//sendOverConnection(msgg);
		
		
		while(scan.hasNextLine()) {
			
			String msg = scan.nextLine();
			sendOverConnection(msg);

			
					
		}
		scan.close();


	}
	
	class ListenFromServer extends Thread {

		public void run() {
			while(true) {
				try {
					// read the message form the input datastream
					String msg = sInput.readLine();
					chatMsg=chatMsg+ msg + "\n";
					System.out.println(chatMsg);
					Main.mainController.textBox.setText(chatMsg);
					Main.mainController.textBox.setScrollTop(Double.MAX_VALUE);
				}
				catch(IOException e) {
					System.out.println("Server has closed the connection: " + e );
					break;
				}
			
			}
		}
	}
	
	public synchronized static void sendOverConnection (String message){
		sOutput.println(message);
	}
	


	
	
}

