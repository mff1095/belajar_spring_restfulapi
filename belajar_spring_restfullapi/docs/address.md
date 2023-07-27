## Address API Spec

## Create Addrress

Endpont : /api/contacts/{idContact}/addresses

Request Header :
X-API-TOKEN : Token(Mandatory)

Request body : 
```json
{
  "street" : "jalan",
  "city" : "kota",
  "province" : "provinsi",
  "country" : "negara",
  "postalCode" : "1233"
}
```

Response body (success) :

```json
{
  "data": {
    "id": "random-string",
    "street": "jalan",
    "city": "kota",
    "province": "provinsi",
    "country": "negara",
    "postalCode": "1233"
  }
}
```

Response body (Failed) :

```json
{
  "errors": "Contact is not found"
}
```

## Update Address

Endpoint : PUT /api/contacts/{idContact}/addresses/{idAddress}

Request Header :
X-API-TOKEN : Token(Mandatory)

Request body :
```json
{
  "street" : "jalan",
  "city" : "kota",
  "province" : "provinsi",
  "country" : "negara",
  "postalCode" : "1233"
}
```

Response body (success) :

```json
{
  "data": {
    "id": "random-string",
    "street": "jalan",
    "city": "kota",
    "province": "provinsi",
    "country": "negara",
    "postalCode": "1233"
  }
}
```

Response body (Failed) :

```json
{
  "errors": "Address is not found"
}
```

## Get Address

Endpoint : GET api/contacts/{idContact}/addresses/{idAddress}

Request Header :
X-API-TOKEN : Token(Mandatory)

Response body (success) :

```json
{
  "data": {
    "id": "random-string",
    "street": "jalan",
    "city": "kota",
    "province": "provinsi",
    "country": "negara",
    "postalCode": "1233"
  }
}
```

Response body (Failed) :

```json
{
  "errors": "Address is not found"
}
```

## Remove Address

Endpoint : DELETE /api/contacts/{idContact}/addresses/{idAddress}

Request Header :
X-API-TOKEN : Token(Mandatory)

Response body (success) :

```json
{
  "data": "OK"
}
```

Response body (Failed) :

```json
{
  "errors": "Address is not found"
}
```
## List Address

Endpoint : GET /api/contacts/{idContact}/addresses

Request Header :
X-API-TOKEN : Token(Mandatory)

Response body (success) :
````json
{
"data": [ {
"id": "random-string",
"street": "jalan",
"city": "kota",
"province": "provinsi",
"country": "negara",
"postalCode": "1233"
} ]
}
````

Response body (Failed) :

```json
{
  "errors": "contact is not found"
}
```