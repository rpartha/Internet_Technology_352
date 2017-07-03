import java.util.*;
import java.io.*;
import java.net.*;

public class ServerEx{ //client code is similar to homework_0
		public static void main(String[] args) {
			ServerSocket serverSocket = new ServerSocket(3000, 2);
			Socket socket;

			serverSocket.setReuseAddress(true);
			while((socket = serverSocket.accept())!=null){
				System.out.println("Accepting connection");
				new Thread()
			}

		}
}
