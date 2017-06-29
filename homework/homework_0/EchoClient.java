import java.lang.*;
import java.net.*;
import java.io.*;

public class EchoClient{
  public static void main(String[] args) throws IOException {

    //user must input 2 arguements. no more, no less.
    if(args.length != 2){
        System.out.println("Usage: java -cp . EchoClient <host name> <port number>"); //usage statement
        System.exit(1);
    }

    Socket socket = null;
    PrintWriter outWriter = null;
    BufferedReader inReader = null;
    BufferedReader stdInReader = null;

    try{

      String host_name = args[0];
      int port_no = Integer.parseInt(args[1]);

      //initalize new socket to ip & port
      socket = new Socket(host_name, port_no);

      //fetch I/O streams from socket
      outWriter = new PrintWriter(socket.getOutputStream(), true);
      inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      //build I/O to read from user on prompt
      stdInReader = new BufferedReader(new InputStreamReader(System.in));

      String userIn; //manage user inputs

      //continuusly get user input
      while((userIn = stdInReader.readLine()) != null){
        //send line to server
        outWriter.println(userIn);

        //read line from server and write to sysout
        System.out.println(inReader.readLine()); //note that server responds in CAPS
      }

    } catch(UnknownHostException uhe){ //host does not exist for some reason...
        uhe.printStackTrace();
        System.exit(1);
    } catch(IOException ioe){ //some I/O exception may occur for some reason...
      ioe.printStackTrace();
      System.exit(1);
    } catch (NumberFormatException nfe){
      nfe.printStackTrace();
      System.exit(1);
    } finally {
      //close everything once done
      if(socket != null) socket.close();
      if(outWriter != null) outWriter.close();
      if(inReader != null) inReader.close();
      if(stdInReader != null) stdInReader.close();
    }
  }
}
