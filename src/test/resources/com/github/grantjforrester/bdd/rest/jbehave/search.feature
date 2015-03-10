Feature: Search

Scenario: Search for term 'BDD' 
	Given a service running on http://bookstore.getsandbox.com/
	And a GET request to the resource /books 
	When the response is received
	Then the response will have the status code 200