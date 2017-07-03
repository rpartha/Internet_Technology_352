import java.util.*;
import java.io.*;
import java.net.*;
public class SessionThread extends Thread{
	private Socket socket;

	public SessionThread(Socket socket){
		this.socket = socket;
	}

	public void run(){
		BufferedReader bufferedReaderFromClient;
		PrintWriter printWriterToClient;
		String s;
		StringBuffer stringBuffer;

		int i, limit, j;
		char temp;

		try{
			bufferedReaderFromClient = new BufferedReader(new InputStreamReader(socket.getINputStream()));
			printWriterToClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			while((s = bufferedReaderFromClient.readLine()) != null){
				stringBuffer = new StringBuffer(s);
				limit = stringBuffer.length()/2;
				for(int i = 0; i < limit; i++){
					j = stringBuffer.length() - 1 - i;
					temp = stringBuffer.charAt(j);
					stringBuffer.setCharAt(j, stringBuffer.charAt(i));
					stringBuffer.setCharAt(i, temp);
				}
			}
		}
	}
}
