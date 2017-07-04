# Lecture 3, 07/03/17

## Project 0
* Format: `<command>` '/' `<unbroken text outer>` '/' `<unbroken text inner>` '/' '\n'
* Acceptable: GET .../scripts/script.js |  GET .../file.type | GET /index.html | GET / <--> GET /doc_root
* Unacceptable: `<anything not "GET">` .../file.type | GET/index.html | get /index.html
* Steps:
	* Check format of the input (400 Bad Request)
	* Extract the command from the input
	* Check that the command is equal to "GET" (501 Not Implemented)
	* Check to see if resource exists (404 Not Found)
	* Read the file, if possible (500 Internal Error)
* **Exceptions caused by the Client SHOULD NOT crash the Server**
