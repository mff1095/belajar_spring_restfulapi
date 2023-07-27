# User API spec

## Register User
Endpoint : Post /api/user

Request body :
```json
{
  "username"  :"Fahmi",
  "password"  :"rahasia",
  "name"      :"Muhamad Fahmi Firdaus"  
}
```

Response body (success) :
```json
{
  "data" : "OK"
}
```

Response body (failed) :
```json
{
  "errors"  :"username must not blank, ???" 
}
```

## Login User
Endpoint : Post /api/auth/login

Request body :
```json
{
  "username"  :"Fahmi",
  "password"  :"rahasia"
}
```

Response body (success) :
```json
{
  "data" : {
    "token" : "TOKEN",
    "expriedAt" : 2344213132 // millisecond
  }
}
```

Response body (failed, 401) :
```json
{
  "errors"  :"username or password is wrong" 
}
```
## Get User
Endpoint : Get /api/users/current

Request Header :
- X-API-TOKEN : Token(Mandatory)

Response body (success) :
```json
{
  "data" : {
    "username"  : "username",
    "name"      : "name" 
  }
}
```

Response body (failed, 401) :
```json
{
  "errors"  :"Unautorized" 
}
```

## Update User
Endpoint : Patch /api/users/current

Request Header :
- X-API-TOKEN : Token(Mandatory)

Request body :
```json
{
  "username"  : "username", // put if only want to update username
  "name"      : "name" // put if only want to update name
}
```

Response body (success) :
```json
{
  "data" : {
    "username"  : "username",
    "name"      : "name" 
  }
}
```

Response body (failed, 401) :
```json
{
  "errors"  :"Unautorized" 
}
```

## Logout User
Endpoint : Delete /api/auth/logout

Request Header :
- X-API-TOKEN : Token(Mandatory)
  
Response body (success) :
```json
{
  "data" : "OK"
}
```