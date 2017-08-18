# Homework 0

## Overview

For this assignment you will need to create a simple network client that communicates with an "echo" server.  The echo server will accept your incoming socket connection, read a line from your client, and send the line back to the client.  This assignment is intended as a very simple primer to get you familiar with the concepts of network hosts, TCP sockets, and ports.  

Keep in mind that one third (30%) of your grade is related to your source code, so make sure to create comments and use small, well-defined methods when appropriate.

It will be expected to take around **2 hours** to complete, though it may take more or less time depending on your abilities and background.

## Specifics of Operation

Your program MUST be completely contained in a file called "EchoClient.java" without any Java packaging. This means that compiling and running your program will be identical to the output below:

```bash
javac -cp . EchoClient.java

java -cp . EchoClient myhost.mydomain.com <port number>
```

If you are unfamiliar with the concepts of Java packages or classpaths, you should see a teaching assistant or the instructor during recitation or office hours. Within your class, you should create multiple methods in addition to the static main(String[]) method. An example skeleton class has been provided to assist you, though you do not need to use this file as long as your class has the same name (EchoClient). To successfully complete this assignment, your program must do the following:
1. Accept the echo server hostname/IP address as args[0] and the port as args[1] in the main method.  The port should be parsed into an int or Integer.
2. Construct a socket connected to the echo server based on the hostname/port specified in the command-line arguments.
3. Read a single line of text from System.in and send it to the echo server including any newline characters (CR/LF).
4. Read a single line response from the echo server and print it to System.out.
5. Close the Socket to the echo server and any IOStreams that you may have opened during the program's lifetime.
6. Exit your program.  At no point in your application should you call System.exit(int).

Two resources are available to you: an example outline of a client and a JAR archive containing an appropriate echo server.  The client example is only an example, and you do not need to use it when coding.  In fact, it will be better in the long run if you design your client from scratch.  The JAR is executable using the following command:

```bash
  java -jar echo-server-1.0.0.jar <port number>
```

 You may choose any port you wish, but be aware that ports below 1024 are reserved for the "root" user in most operating systems.

 ## Grading Outline

 * Source code organization and comments: 30%
 * Reading command-line parameters: 10%
 * Establishing socket to the server based on parameters: 20%
 * Reading user input and sending it to the server: 15%
 * Reading server response and printing it to the user: 15%
 * Closing all Sockets/IOStreams before exit: 10%

 ## Hints and Suggestions
 If this is your first time working directly with Sockets, IOStreams, and Strings, you may find the following classes/resources helpful:

 * http://docs.oracle.com/javase/tutorial/essential/io/
 * http://docs.oracle.com/javase/tutorial/essential/io/cl.html
 * http://docs.oracle.com/javase/tutorial/networking/sockets/
 * http://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
