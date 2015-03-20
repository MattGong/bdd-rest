# Gherkin Grammar

## Request Steps

### Step: a service running on &lt;baseUri&gt;

A step that describes where the RESTful endpoint shall be located.  **Important:** this should be the first step used from BDD-rest in a scenario.

Example:

    Given a service running on http://example.com


### Step: a <method> request to resource &lt;uri&gt;

A step that describes the URI of a resource this is to be requested and the HTTP method that should be used for the request. 
The word 'a' or 'the' may be used before 'resource' 

Examples:

    Given a GET request to resource /foo
    Given a GET request to a resource /foo
    Given a GET request to the resource /foo


### Step: the request has header '&lt;headerName&gt;' with value '&lt;headerValue&gt;'

A step that describes a request header that should be included with the request.
The word 'a' or 'the' may be used before 'header' and 'value' 	

Examples:

    Given the request has header 'Content-Type' with value 'text/plain'
    Given the request a header 'Content-Type' with a value 'text/plain'
    Given the request has the header 'Content-Type' with the value 'text/plain'


### Step: the request has content '&lt;content&gt;'

A step that describes the content of the request.
The word 'the' may be used before 'content'.

Examples:

    Given the request has content 'example text'
    Given the request has the content 'example text'


### Step: the request has content from file '&lt;filename&gt;'

A step that describes the content of the request should be read from the file with the given filename.
The filename can be absolute or relative. Relative paths are resolved the current working directory. 
The word 'a' or 'the' may be used before 'file'.

Examples:

    Given the request has content from file 'example.txt'
    Given the request has content from a file 'example.txt'
    Given the request has content from the file 'example.txt'

	
## Response Steps

### Step: the response is received

A step that introduces the response to the scenario and when executed performs the request and receives the response.

Example:

    When the response is received


### Step: the response will have status code &lt;code&gt;

A step the verifies the status code of the response matches the status code given. The match is a numerical equality test.
The word 'a' or 'the' may be used before 'status code' 

Examples:

    Then the response will have status code 200
    Then the response will have a status code 200
    Then the response will have the status code 200


### Step: the response will have header '&lt;headerName&gt;' with value '&lt;headerValue&gt;'

A step the verifies the presence of a response header with the given name and value. The matches of both header name and value
are string equality tests. The word 'a' or 'the' may be used 'header' and 'value'

Examples:

    Then the response will have header 'Content-Type' with value 'text/plain'
    Then the response will have a header 'Content-Type' with a value 'text/plain'
    Then the response will have the header 'Content-Type' with the value 'text/plain'
 

### Step: the response will not have header '&lt;headerName&gt;' with value '&lt;headerValue&gt;' 

A step the verifies the absence of a response header with the given name and value. The matches of both header name and value
are string equality tests. The word 'a' or 'the' may be used 'header' and 'value'

Examples:

    Then the response will not have header 'Content-Type' with value 'text/plain'
    Then the response will not have a header 'Content-Type' with a value 'text/plain'
    Then the response will not have the header 'Content-Type' with the value 'text/plain'
 

### Step: the response content will match '&lt;content&gt;'

A step that verifies the content of the response matches the given content. The match is a byte array comparison of the
expected and actual values.

Examples:

    Then the response content will match 'example text'


### Step: the response content will match file '&lt;filename&gt;'

A step that verifies the content of the response matches the contents read from the file with the given filename. 
The match is a byte array comparison of the expected and actual values.  The word 'a' or 'the' may be used before 'file'.

Examples:

    Then the response content will match file 'example.txt'
    Then the response content will match a file 'example.txt'
    Then the response content will match the file 'example.txt'