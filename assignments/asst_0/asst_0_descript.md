# Project 0: HTTP "0.8"

## Overview

In this assignment you will design a web server in Java that supports a subset of the HTTP 0.8 protocol, the initial testing protocol for Hypertext Transfer.
Your simple web server will have to accept incoming socket connections, read a request from the client, and answer with the appropriate HTTP status code.
This assignment is inteded as a low-weight primer to get you familiar with the layout of a network service that deals with a protocol and multiple connections.

Keep in mind that one third (30%) of your grade is related to your source code, so make sure to create comments and use small, well-defined methods when appropriate.

This assignment is expected to take around 8 hours to complete, though it may take more or less time depending on your abilities and background.

## Specifics of Operation

Your program MUST be completely contained in a file called "SimpleHTTPServer.java" without any Java packaging. This means that compiling and running your program will be identical to the output below:

```
javac -cp . SimpleHTTPServer.java

java -cp . SimpleHTTPServer 3456
```
To successfully complete this assignment, your program must do the following:

* Accept the port as args[0] in the main method.  The port should be parsed into an int or Integer.
* Construct a ServerSocket that accepts connections on the port specified in the command-line argument.
* When a client connects to your ServerSocket, you are to spawn a new thread to handle all the client's communication. Absolutely no reading or writing to or from a Socket should occur in the class that the ServerSocket is accepting connections in.
* Your communication thread should be a properly-constructed Java thread.
* In your thread you should read a single String from the client, parse it as an HTTP 0.8 request and send back an appropriate response according to the HTTP 0.8 protocol below.
* Your responses should correctly refer to the resource directory supplied, see below for more details.
* Once your response has been sent, you should flush() your output streams, wait a quarter second, close down all communication objects and cleanly exit the thread.
* Be careful to handle Exceptions intelligently. No Exceptions should be capable of crashing your server.

## Resources
Your code should serve the resources in "doc_root.zip". Unzip it into the current working directory (where you run your code). If a properly-formatted HTTP 0.8 request is made for a resource, your server should read the resource from the disk and send it to client. See the '200 OK' status code below.


## HTTP 0.8 details

### Requests

HTTP 0.8 is a subset of the HTTP 0.9 protocol. An HTTP 0.8 request is structured as:

&lt;command&gt;&lt;space&gt;&lt;resource&gt;

&lt;command&gt;: a series of capital letters  
&lt;resource&gt;: the full path to requested file, starting with '/'

For a file in the root directory: GET /index.html

Notes:
* &lt;command&gt; and &lt;resource&gt; must be separated by a space
* There must be a new line at the end of a request

### Responses

Every HTTP 0.8 request must be given a HTTP 0.8 response string. There are only 6 valid HTTP 0.8 response strings:

1. **400 Bad Request**: Request with improper format. Basically any request that does not fit another response type.
2. **404 Not Found**: A properly-formatted request but the requested resource does not exist.
3. **501 Not Impleented**: A properly-formatted request with the wrong command (i.e. any command other than "GET")
4. **500 Internal Error**: Any error or exception that prevents you from handling the request (e.g. can't access file or read a resource)
5. **200 OK**: If everything goes right, meaning a properly-formatted request with no problems accessing the resource, return this code.
6. _**408 Request Timeout**_: If the client opens a connection with the server but does not send any request within 3 seconds, your server should send this response (_Extra Credit, optional_).

## Grading Outline
* Source code organization and comments: 30%
* Isolation of Socket communiction from ServerSocket: 10%
* Correct parsing of requests: 10%
* Correct responses to all test requests: 30%
* Correct implemenation of request timeout: 5%
* Isolation of Thread Exceptions from server code: 10%    
* Closing all Sockets/IOStreams before Thread termination: 10%
