# Project 2: Fetching Computation

## Overview

In this assignment you will extend the capabilities of your current webserver by laying the groundwork for more extensive client/server interactions by more fully implementing POST and adding CGI.

Keep in mind that one third (30%) of your grade is related to your source code, so make sure to create comments and use small, well-defined methods and classes when appropriate.

This assignment is expected to take around 5 to 10 hours to complete, though it may take more or less time depending on your abilities and background. It is essential that you work effectively with your group to complete it in a reasonable about of time.

## Specifics of Operation

Your program's main file must be named "HTTP1Server.java" without any Java packaging. This means that running your compiled program will be identical to the output below:

```bash
java -cp . HTTP1Server 3456
```
You should be sparing in the use of additional classes, however you should design as you feel fit.

Compress all your source files into a '.zip' (Windows zip) or 'tar.gz' (Linux gzipped tarball) named "HTTP1Server.zip" or "HTTP1Server.tar.gz"

Submit your compressed file. Do not use any Java packaging.

To successfully complete this assignment, your program must operate the same way as Project 1, except for two significant differences:

* POST operations must be supported as per RFC-1945.
* Support invoking server-side code using CGI.

### Features

#### POST
POST requests differ in that they are not requests for a data resource, but a computation resource. In a POST request the client requests the server either run or interpret some data. POST commands are often used for parsing HTML FORM data. A POST request's URL is expected to be some executable code on the server side. Since a POST request often results in code execution, it must carry parameters as well. Parameters are contained in the request body and each parameter is separated by a "&". Each parameter is listed by: <NAME>=<VALUE> . If the Content-Type is anything else, for now, you should regard it as something you can not parse and respond with a 'Internal Server Error' code.

Example: Request that a server run 'stuff.cgi' with 'Name=Me' and 'Cost = 0':

```
POST /orderForm.cgi? HTTP/1.0
       From: me@mycomputer
       User-Agent: telnet
       Content-Type: application/x-www-form-urlencoded
       Content-Length: 14

       Name=Me&cost=0
```
Parameters are URL-coded, meaning that certain characters that are not allowed in URLs are replaced with escape symbols. You should correctly translate URL-coded parameters as per RFC-3986. You do not need to support the full UTF-8 character set and command codes. Only characters that are considered reserved under HTTP:  
"!     *     '     (     )     ;     :     @     &     =     +     $     ,     /     ?     #     [     ]" and whitespace.

#### CGI

The Common Gateway Interface provides a single mechanism to invoke any type of code on any type of machine with an HTTP server. The most recent reliable spec is the CGI 1.1 draft, ca. 1995 (http://tools.ietf.org/html/draft-robinson-www-interface-00). Rather than support the full spec, since it is fairly dated, what you should do is execute the code named in the CGI request URL in an environment where the parameters sent exist as name/value pairs, capture all the output from the script (redirect STDOUT) and send it as a text/html response to the requester. If a CGI script runs to completion successfully, but does not produce output, you should return a "204 No Content" code to indicate a success with no output. You will need to invoke code outside of Java using Java. I would recommend you look up  Java Runtimes and Processes. CGI scripts have the file type '.cgi'.

## Notes

* In this project, a GET request to a CGI script should be treated as a GET request to a normal document, there's no need to execute the CGI script.
* When the POST request doesn't have the "Content-Length" header, or the value is not numeric, your server should return "HTTP/1.0 411 Length Required".
* When the POST request doesn't have the "Content-Type" header, your server should return "HTTP/1.0 500 Internal Server Error".
* Other POST headers are not mandatory.
* If the POST request has good headers, but its target is not a CGI script, your server should return "HTTP/1.0 405 Method Not Allowed".
* If everything is good in the POST request, your server should:
  1. decode the payload according to RFC-3986.
  2. set the CONTENT_LENGTH environment variable to the length of the decoded payload when executing the CGI script.
  3. send the decoded payload to the CGI script via STDIN.
* Your server only needs to support these environment variables in this version:

  1. CONTENT_LENGTH: the length of the decoded payload in bytes.
  2. SCRIPT_NAME: the path of the current CGI script. (Example: For the request "POST /cgi-bin/test.cgi HTTP/1.0", SCRIPT_NAME = /cgi-bin/test.cgi).
  3. SERVER_NAME: the IP of the server.
  4. SERVER_PORT: the port that the server is listening to.
  5. HTTP_FROM: if the POST request has the header "From", then set this to the value of "From".
  6. HTTP_USER_AGENT: if the POST request has the header "User-Agent", then set this to the value of "User-Agent".
*  If the CGI script doens't have the "execute" permission, then you should return "HTTP/1.0 403 Forbidden".
* Your server still needs to support all the features in Project 1.
* If a '.cgi' resource is fetch with GET assign it MIME type 'application/octet-stream'

## Grading Outline
* Source code organization and comments: 30%
* Correct parsing of all URL-coded arguments: 20%
* Efficient communication thread management: 10%
* Correct implemenation of all response codes: 10%
* Correct creation of server-side CGI environment variables and * Runtime: 15%
* Correct execution and response capture of CGI data: 15%

## Resources
* http://tools.ietf.org/html/rfc1945
* https://en.wikipedia.org/wiki/POST_%28HTTP%29
* http://www.w3schools.com/tags/ref_httpmethods.asp
* http://www.jmarshall.com/easy/http/#postmethod
* https://tools.ietf.org/html/rfc3986
* http://www.w3schools.com/tags/ref_urlencode.asp
* https://en.wikipedia.org/wiki/Percent-encoding
* http://tools.ietf.org/html/draft-robinson-www-interface-00
* http://www.jmarshall.com/easy/cgi/
* http://www.cgi101.com/book/ch3/text.html
