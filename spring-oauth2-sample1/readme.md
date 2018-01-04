
## About this sample

This sample is based on []()

### Running

Use `mvn spring-boot:run` in the project directory to run the sample.

### Testing

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


