Feature: Search

Scenario: Search for term 'BDD' 
	Given a service running on http://www.google.com
	And a GET request to the resource /search?term=bdd 
	When the response is received
	Then the response will have the status code 200
	And the response will not have a header 'foo' with value 'bar' 
	And the response content will match 'foo'
	And the response content will match the file 'bal'