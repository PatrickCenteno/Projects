import java.io.*;
import java.net.*;
import java.lang.*;

public class ChatterServer {
	public static void main(String [] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		System.err.println("Waiting for a client");
		Socket clientSocket = serverSocket.accept();

		System.out.println("Connection requested from: " + clientSocket.getLocalAddress());

		PrintStream toClient = new PrintStream(clientSocket.getOutputStream(), true);
		BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

		toClient.println("Whatcha want?");
		String incoming = fromClient.readLine();
		while(incoming != null) {
			System.out.println(incoming);
                        if (incoming.charAt(incoming.length()-1) == '.'){
                            System.out.print("Your turn> ");
                            String myReply;
                            myReply = keyboard.readLine();
                            toClient.println(myReply);
                            incoming = fromClient.readLine();    
                                
                        }
                        else
                            
                            incoming = fromClient.readLine();
                }
        }

	final static int SERVER_PORT = 3333;
}