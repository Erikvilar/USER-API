
# USER API PROVA PRÁTICA 
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

## POST - CREATE NEW USER
* URL PATH : [ http://localhost:6680/user/save ]

### RESULT AND BODY REQUEST

```JSON

{
  "id": "6754e6ec90b2141fc1f48a5b",
  "name": "erik",
  "cpf": "23819039012",
  "email": "erik@gmail.com",
  "telefone": "213812783",
  "address": {
    "street": "rua teste",
    "number": 21
  }
}
```


## GET - RETURN ALL USERS

* URL PATH : [ http://localhost:6680/user/users]

### RESULT

```JSON
[
  {
    "id": {
      "timestamp": 1733608976,
      "date": "2024-12-07T22:02:56.000+00:00"
    },
    "name": "erik",
    "cpf": "23819039012",
    "email": "erik@gmail.com",
    "telefone": "213812783",
    "address": {
      "street": "rua teste",
      "number": 213
    }
  },

  [more..]
]

```
## GET - RETURN USER BY ID

* URL PATH : [ http://localhost:6680/user/id/6754c8da6e7607357dd655d3 ]

### RESULT
```JSON

{
  "id": "6754c8da6e7607357dd655d3",
  "name": "erik",
  "cpf": "23819039012",
  "email": "erik@gmail.com",
  "telefone": "213812783",
  "address": {
    "street": "rua teste",
    "number": 21
  }
}

```

## GET - RETURN BY CPF

* URL PATH :[ http://localhost:6680/user/cpf/23819039012 ]
### RESULT
```JSON

{
  "id": "6754c8da6e7607357dd655d3",
  "name": "erik",
  "cpf": "23819039012",
  "email": "erik@gmail.com",
  "telefone": "213812783",
  "address": {
    "street": "rua teste",
    "number": 21
  }
}

```

## GET - SEARCH BY NAME

* URL PATH :[ http://localhost:6680/user/nameuser/erik ]

### RESULT
```JSON

{
  "id": "6754c8da6e7607357dd655d3",
  "name": "erik",
  "cpf": "23819039012",
  "email": "erik@gmail.com",
  "telefone": "213812783",
  "address": {
    "street": "rua teste",
    "number": 21
  }
}

```

## GET - RETURN PAGE SIZE=1&PAGE=1

* URL PATH:[http://localhost:6680/user/pageable?size=1&page=1]

### RESULT

```JSON
{
  "content": [
    {
      "id": "6754c8da6e7607357dd655d3",
      "name": "eri",
      "cpf": "23819039012",
      "email": "erik@gmail.com",
      "telefone": "213812783",
      "address": {
        "street": "rua teste",
        "number": 23
      }
    }
  ],
  "pageable": {
    "pageNumber": 1,
    "pageSize": 1,
    "sort": {
      "empty": true,
      "unsorted": true,
      "sorted": false
    },
    "offset": 1,
    "unpaged": false,
    "paged": true
  },
  "totalPages": 15,
  "totalElements": 15,
  "last": false,
  "size": 1,
  "number": 1,
  "sort": {
    "empty": true,
    "unsorted": true,
    "sorted": false
  },
  "numberOfElements": 1,
  "first": false,
  "empty": false
}
```
## PUT - UPDATE USER

* URL PATH:[http://localhost:6680/user]

### RESULT AND BODY REQUEST

```JSON
{
  "id": "6754c8da6e7607357dd655d3",
  "name": "eri",
  "cpf": "23819039012",
  "email": "erik@gmail.com",
  "telefone": "213812783",
  "address": {
    "street": "rua teste",
    "number": 23
  }
}
```
## DELETE - DELETE BY ID
* URL PATH:[http://localhost:6680/id/6754c8da6e7607357dd655d]

### RESULT REQUEST
Status: <span style="color: green;">204 </span>No Content
Size: 0 Bytes
Time: 177 ms
#### JAVA NET HTTP RESPONSE
```JAVA
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("http://localhost:6680/user/id/6754e373ab10eb631d446992"))
    .header("Accept", "*/*")
    .header("User-Agent", "Thunder Client (https://www.thunderclient.com)")
    .method("DELETE", HttpRequest.BodyPublishers.noBody())
    .build();
HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
System.out.println(response.body());
```



