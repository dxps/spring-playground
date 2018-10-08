
## About this sample

This sample is based on [Using Spring Oauth2 to secure REST](http://www.tinmegali.com/en/oauth2-using-spring/) excellent article.


### Running

Use `mvn spring-boot:run` in the project directory to run the sample.


### Testing


#### Before setting JWT

OAuth2 tokens are obtained by calling:

```bash
$ curl -s trusted-app:secret@localhost:8080/oauth/token -d "grant_type=password&username=user&password=password" | jq
{
  "access_token": "bf2f4774-af79-4462-8350-5ccd6a257edd",
  "token_type": "bearer",
  "refresh_token": "ef0eaf80-2311-4636-ad2d-9bb110507796",
  "expires_in": 9999,
  "scope": "read write"
}
$
```

Authorization Server provides:
- an access token
- a refresh token
- expiration of the access token (in seconds)
- the token type ("bearer")
- scopes (read and write privileges)

The output listed above was before adding JWT setup (included in `AuthorizationConfig`) in the mix.


#### After setting JWT

After that, the same command returns:

```bash
$ curl -s trusted-app:secret@localhost:8080/oauth/token -d "grant_type=password&username=user&password=password" | jq
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyX2lkIl0sInVzZXJfbmFtZSI6InVzZXIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTE1MTQ2NjI3LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiOTZlMGEzOTktOTIwNi00M2NiLTkyYjEtM2U3MGM1YjJiYmMzIiwiY2xpZW50X2lkIjoidHJ1c3RlZC1hcHAifQ.3eU60BIfOOzlbO2eeV7a9pLMUFlsrivbBZGn_-n_xaw",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyX2lkIl0sInVzZXJfbmFtZSI6InVzZXIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXRpIjoiOTZlMGEzOTktOTIwNi00M2NiLTkyYjEtM2U3MGM1YjJiYmMzIiwiZXhwIjoxNTE3Njk1NDI3LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiMWUzNTBmYTEtYjg4ZC00MWE3LWFjNDAtMjA1NmJiNWQ2NGFmIiwiY2xpZW50X2lkIjoidHJ1c3RlZC1hcHAifQ.UDA3pdJfuogslfHc4Ne_1ztr4f-w4IwriMUYlyILWCM",
  "expires_in": 43199,
  "scope": "read write",
  "jti": "96e0a399-9206-43cb-92b1-3e70c5b2bbc3"
}
$ 
```

The access and refresh tokens' values are now JWTs.

#### Refreshing the access token

The whole purpose of the refresh token is to renew the access token. And this is accomplished using:

```bash
$ curl -s trusted-app:secret@localhost:8080/oauth/token -d "grant_type=refresh_token&refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyX2lkIl0sInVzZXJfbmFtZSI6InVzZXIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXRpIjoiOTZlMGEzOTktOTIwNi00M2NiLTkyYjEtM2U3MGM1YjJiYmMzIiwiZXhwIjoxNTE3Njk1NDI3LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiMWUzNTBmYTEtYjg4ZC00MWE3LWFjNDAtMjA1NmJiNWQ2NGFmIiwiY2xpZW50X2lkIjoidHJ1c3RlZC1hcHAifQ.UDA3pdJfuogslfHc4Ne_1ztr4f-w4IwriMUYlyILWCM" | jq
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyX2lkIl0sInVzZXJfbmFtZSI6InVzZXIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTE1MTQ2OTQwLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiMjVjNWY0NzQtMDVhZC00ZWI0LWJkMjMtMWNlNTJhODMwNzFiIiwiY2xpZW50X2lkIjoidHJ1c3RlZC1hcHAifQ.G8c0Xx30630ior4KSV_STZmFfHyRM8f3xshAGJVHOXQ",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyX2lkIl0sInVzZXJfbmFtZSI6InVzZXIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXRpIjoiMjVjNWY0NzQtMDVhZC00ZWI0LWJkMjMtMWNlNTJhODMwNzFiIiwiZXhwIjoxNTE3Njk1NDI3LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiMWUzNTBmYTEtYjg4ZC00MWE3LWFjNDAtMjA1NmJiNWQ2NGFmIiwiY2xpZW50X2lkIjoidHJ1c3RlZC1hcHAifQ.Kry6QKSk9aWCEn2-jR4FZYi2HoZL7d2GSQmw4WSWa_M",
  "expires_in": 43199,
  "scope": "read write",
  "jti": "25c5f474-05ad-4eb4-bd23-1ce52a83071b"
}
$ 
```

The refresh token gets also refreshed as you can see in the output listed above.

