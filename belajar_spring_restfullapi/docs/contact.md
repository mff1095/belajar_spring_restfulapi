### CONTACT API spec

## Create Contact
Endpoint : Post /api/contacts

Request Header :
X-API-TOKEN : Token(Mandatory)

Request body :
```json
{
  "firstName" :"first",
  "lastName"  :"last",
  "email"     :"fam@example",
  "phone"     :"081111" 
}
```

Response body (success) :
```json
{
  "data" : {
    "id"        : "random string",
    "firstName" :"first",
    "lastName"  :"last",
    "email"     :"fam@example",
    "phone"     :"081111"
  }
}
```

Response body (Failed) :
```json
{
  "errors"  :"firstname or lastname , email is not valid ???" 
}
```

## Update Contact
Endpoint : Put /api/contact/{idContact}

Request Header :
X-API-TOKEN : Token(Mandatory)

Request body :
```json
{
  "firstName" :"first",
  "lastName"  :"last",
  "email"     :"fam@example",
  "phone"     :"081111" 
}
```

Response body (success) :
```json
{
  "data" : {
    "firstName" :"first",
    "lastName"  :"last",
    "email"     :"fam@example",
    "phone"     :"081111"
  }
}
```

Response body (Failed) :
```json
{
  "errors"  :"firstname or lastname , email is not valid ???" 
}
```
## Get Contact
Endpoint : GET /api/contact/{idContact}

Request Header :
X-API-TOKEN : Token(Mandatory)


Response body (success) :
```json
{
  "data" : {
    "firstName" :"first",
    "lastName"  :"last",
    "email"     :"fam@example",
    "phone"     :"081111"
  }
}
```

Response body (Failed 404) :
```json
{
  "errors"  :"Contact not found" 
}
```
## Remove Contact
Endpoint : DELETE /api/contacts/{idContact}

Request Header :
X-API-TOKEN : Token(Mandatory)


Response body (success) :
```json
{
  "data" : "OK"
}
```

Response body (Failed, 404) :
```json
{
  "errors"  :"Contact not found" 
}
```

## Search Contact
Endpoint : GET /api/contacts

Request Param :
- name : String, query like first or last name , optional.
- phone : String, query like phone , optional.
- email : String, query like email , optional.
- page : Integer , start from 0
- size : Integer , default 10

Request Header :
X-API-TOKEN : Token(Mandatory)


Response body (success) :
```json
{
  "data" : [ 
      {
        "firstName" :"first",
        "lastName"  :"last",
        "email"     :"fam@example",
        "phone"     :"081111"
      }
    ],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size": 10
  }
}
```

Response body (Failed) :
```json
{
  "errors"  :"Unautorized" 
}
```