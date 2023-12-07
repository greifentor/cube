# cube

A web application which manages the authentication and authorization for other cooperating applications.


## What does it do?

The cube manages user and application data for related web applications. It provides a login dialog and a dashboard 
which allows the user to jump into one of related applications.

Related applications will be called with a token which contains all necessary information about the user and the rights
the user has in the called application.


## Requirement

* Maven: 3.5.4+
* Java: 17


## Security

### Example Token

```
{
  "applicationName": "test-app",
  "applicationRights": [
  ],
  "endOfValidity": "2022-12-31 23:59:59",
  "userGlobalId": "TEST-USER",
  "userName": "T.User",
  "userToken": "TU"
}
```