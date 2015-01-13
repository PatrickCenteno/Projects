import java.io.*;
import java.net.*;
import java.lang.*;

public class ChatterClient {
	public static void main(String [] args) throws Exception {
		Socket serverSocket = new Socket(args[0], SERVER_PORT);

		PrintStream toServer = new PrintStream(serverSocket.getOutputStream(), true);
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

		String incoming = fromServer.readLine();
		while(incoming != null) {
			System.out.println(incoming);
			if(incoming.charAt(incoming.length()-1) == '.'){
                            System.out.print("Your turn> ");
                            String myReply;
                            myReply = keyboard.readLine();
                            toServer.println(myReply);
                            incoming = fromServer.readLine();
                        }else      
                            
                            incoming = fromServer.readLine();    
		}
	}
	final static int SERVER_PORT = 3333;
}