# Country Management Web Service

## Swagger Documentation
https://countrymanager.herokuapp.com/swagger-ui.html#/

### Country Management EndPoint <br/>

**Add Country Name to the list** <br/>
**PUT** /country <br/>
header: Authorization token <br/>
Request Body:

```
  {
    "countryName": "Nigeria"
  }
```
Response Body:

```
  {
    "success": true,
    "message": "Country name successfully added to the list",
    "data": {
      "name": "Nigeria",
      "code": "82231"
    }
  }
```

**Get list of countries** <br/>
**GET** /country <br/>
header: Authorization token <br/>

Response Body:

```
  {
    "success": true,
    "message": "Request successfull",
    "data": [
      {
        "name": "Nigeria",
        "code": "82231"
      },
      {
        "name": "Ghana",
        "code": "23665"
      }
    ]
  }
```
**Delete Country Name from the list** <br/>
**DELETE** /country <br/>
header: Authorization token <br/>
Request Body:

```
  {
    "countryName": "Nigeria"
  }
```
Response Body:

```
  {
    "success": true,
    "message": "Country name successfully deleted",
    "data": "Nigeria"
  }
```

### User Authetication Management EndPoint <br/>
**Login** <br/>
**POST** /user/login <br/>

Request Body:

```
  {
    "password": "password",
    "username": "habeex"
  }
```
Response Body:

```
  {
    "success": true,
    "message": "Login Successful",
    "data": {
      "access_token": "Authorization token",
      "token_type": "bearer",
      "refresh_token": "Refresh token",
      "expires_in": 43199,
      "scope": ""
    }
  }
```

