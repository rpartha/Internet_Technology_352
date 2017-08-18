# Project 3: Fetching Dynamic Resources

## Overview

In this assignment you will enhance the computational resources your server can deliver by adding structure and state to your server-side CGI scripting to enable dynamic content creation by using Cookies and generating markup elements based on client data. Rather than emitting raw text, your script will generate dynamic HTML based on user state. In order to add state to HTML you'll need to add Cookies to your responses.

Keep in mind that one third (30%) of your grade is related to your source code, so make sure to create comments and use small, well-defined methods and classes when appropriate.

This assignment is expected to take around 8 to 16 hours to complete, though it may take more or less time depending on your abilities and background.

## Specifics of Operation

Your program's main file must be named "HTTP1ServerASP.java" without any Java packaging. This means that running your compiled program will be identical to the output below:

```
 java -cp . HTTP1ServerASP 3456
```

You should be sparing in the use of additional classes, however you should design as you feel fit.

Compress all your source files into a '.zip' (Windows zip) or 'tar.gz' (Linux gzipped tarball) named "HTTP1ServerASP.zip" or "HTTP1ServerASP.tar.gz"

Submit your compressed file. Do not use any Java packaging.

### Features

To successfully complete this assignment, your program must support all Project 2 features, in addition:

* Implement a simple web service that uses both Cookies and dynamically-generated HTML to implement a simple store-type interaction in a single CGI script named 'store.cgi':

 When the client requests 'store.cgi':

 1. If the client requests with a GET, emit an HTML page with a FORM textfield and password field that asks the client their name, and password. The FORM should have a 'Submit' button that calls on 'store.cgi' with action POST.

 2.  If the client requests with a POST with no name Cookie set, they should have a login name and password in the payload, with FORM action indicating 'Submit' was pressed.  
 if they _do not_:   
 respond with the same response as scenario 0, above.  
 if they _do_:   
 In your response, have the user set a 'name' Cookie to their name and a 'cart' Cookie to be empty and never expire. Have the name Cookie expire in 3 minutes. Include in your response an HTML page greeting the user by name with a FORM dropdown list of  products and prices and an 'Add to Cart' button that calls on 'store.cgi' with action POST.  

 3. If the client requests with a POST and a name Cookie set, the payload should contain a reference to a product (a name or code).  
      if it _does not_:   
      respond as if the user just logged in correctly as in 1, above.  
      if it _does_:  
      if the FORM action is _'Add to Cart'_:  
      In your response, have the user set a 'cart' Cookie to hold their previous data as well as the new product data. Include in your response an HTML page greeting the user by name with a FORM dropdown list of products and prices and an 'Add to Cart' button that calls on 'store.cgi' with action POST, as well as a dropdown list representing their current cart and sum of all products in their cart, and a 'Remove from Cart' button that calls on 'store.cgi' with action POST.
      if the FORM action is _'Remove from Cart'_:  
      In your response, have the user set a 'cart' Cookie to hold their previous data minus the POSTed product's data. Include in your response an HTML page greeting the user by name with a FORM dropdown list of products and prices and an 'Add to Cart' button that calls on 'store.cgi' with action POST, as well as a dropdown list representing their current cart and total, and a 'Remove from Cart' button that calls on 'store.cgi' with action POST.

      .. the user's cart and available products should be mutually exclusive. If a user puts a product in their cart, it should be removed from the product list, and vice versa.

      .. if the user's login expires, they should be redirected to the login page, part of which is setting their cart to be empty. All cart data should be cleared if a user's login expires.

      .. all HTML pages should be correct HTML. The page does not have to be high art, but should be correctly-formed.
 If the client requests with a GET, emit an HTML page with a FORM textfield and a 'Browse' button that allows the user to select a file, as well as a textfield for the MD5 hash and a 'Submit' button that calls on 'service.cgi' with action POST.

* Implement a simple web service that uses dynamically-generated HTML to compute MD5 hashes to help people validate their downloads in a single CGI script named 'service.cgi':

  When the client requests 'service.cgi':

  1.  If the client requests with a GET, emit an HTML page with a FORM textfield and a 'Browse' button that allows the user to select a file, as well as a textfield for the MD5 hash and a 'Submit' button that calls on 'service.cgi' with action POST.

  2. If the client requests with a POST they should have a file and MD5 hash in the payload, with FORM action indicating 'Submit' was pressed.  
      if they _do not_:  respond with the same response as scenario 0, above.  
      if they _do_: read in the file and compute its MD5 hash. You do not need to (and should not) store the file on disk. Your code should calculate the MD5 hash of the file and compare it to the hash in the payload.  
      if the hashes are the _same_:   
        emit an HTML page that informs the user that the hashes are the same.
      if the hashes are _different_:   
        emit an HTML page that informs the user that the hashes are different.

  ### Cookies

  **Cookies**:   
  Arbitrary pieces of data, usually chosen by server, and stored on the client. The client sends them to the server with every request, introducing state into originally stateless HTTP connections.

  #### Usage of Cookie

  1. 'Set-Cookie'

    * Cookies can be set by the server using the `Set-Cookie` header in HTTP responses. This will instruct the client to store certain key-value pairs (sometimes called Cookie Crumbs) in their cookie storage, in order to keep some states. Cookies are stored on the client side per domain. For instance, if a 'Set-Cookie' message was sent in a response while communicating with 'mail.yahoo.com', the client would set the Cookie, and then any time they sent a request to any page under the domain 'yahoo.com' they would include the Cookie in the request header.

    * The format of `Set-Cookie` header is:
      1. Without Attributes:
      ```
      Set-Cookie: <key>=<value>
      ```
      2. With Attributes:
      ```
      Set-Cookie: <key>=<value>; attribute[0] ; attribute[1] ; ...; attribute[n]
      ```
      Every `Set-Cookie` will instruct the client to store ONE key-value pair in a certain manner designated by the optional attribtues. If the server wants to set multiple key-value pairs, it should use multiple `Set-Cookie` headers. However, servers SHOULD NOT include more than one Set-Cookie header field in the same response with the same cookie-name

        For instance:

        Set-Cookie: UserID=foo
        Set-Cookie: GroupID=bar; Expires=Wed, 18 May 2033 03:33:20 GMT

        will set the two cookie crumbs "UserID" and "GroupID", and for "GroupID" it has the attribute "Expires", which will be introduced below.

  2. 'Cookie'

  * The client can use `Cookie` header in the HTTP requests to inform the server about certain cookie crumbs in order to maintain state.

    The format of `Cookie` header is:
    1. One Cookie Crumb:
    ```
    Cookie: <key>=<value>
    ```

    2. Several Cookie Crumbs
    ```
    Cookie: <key[0]>=<value[0]>; <key[1]>=<value[1]>; ...; <key[n]>=<value[n]>
    ```
    When the user agent generates an HTTP request, the user agent MUST NOT attach more than one Cookie header field.

      For instance:  
      Cookie: foo=bar will carry one cookie crumb  
      Cookie: UserID=foo; GroupID=bar will carry two cookie crumbs.

#### Cookie Attributes

`Set-Cookie` can instruct the client to store cookie crumbs in a certain manner, which is described by cookie attributes.

There are 6 possible cookie attributes:

* Expires:   
sets a specific date and time for when the client should delete the cookie.
If this attribute is not set, then the cookie crumb will be treated as a session cookie by the client.
As an example, most browsers will remove the session cookies when they exit.
* Max-Age: sets the cookie's expiration as an interval of SECONDS in the future, relative to the time the client received the cookie
* Domain:   
defines the domain scope of the cookie. If not specified, the behavior is undefined.
Usually the client will use the domain of the requested resource as a default.
* Path: defines the path scope of the cookie.
* Secure:   
this attribute has no value. If it appears in the attribute list, it means to keep cookie communication limited to encrypted transmission.
* HttpOnly:   
this attribute has no value. If it appears in the attribute list, it instructs the client not to expose cookies through channels other than HTTP and HTTPS requests.


## Grading Outline
* Source code organization and comments: 30%
* Correct implementation of all Project 2 services: 10%
* Correct implementation of 'service.cgi': 20%
* Correct implementation of 'store.cgi': 30%
* Study for the final =D  : 10%
