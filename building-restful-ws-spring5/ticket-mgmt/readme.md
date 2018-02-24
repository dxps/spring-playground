## About this sample

This Spring-based sample showcases:
- RESTful API components: controllers, services, repos
- Reactive API components: routerfunctions, requestpredicates, handlers



#### RESTful

In `com.ticketmgmt.api.restful` package, there are two controllers: `HomeController` and `SecurityController`.



#### Reactive

In `com.ticketmgmt.api.reactive` package, there is a `RouterConfig`, being a configuration bean that sets the routes in the reactive way. One or more handlers (like `UserHandler`) are used while setting these routes.
  


#### JWT token ops

Generate a JWT token providing the subject by going to:
```
http://localhost:8080/security/generate/token?subject=vision8
```
The result is similar to this:
```json
{
  "result": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aXNpb244IiwiZXhwIjoxNTE5NDg3NzI2fQ.yV28VhKMgTbR4Lhg4AKV5dU6NAfB_fKUZbUM6YdYBxQ"
}
```

Get the subject of a JWT token bby going to:
```
http://localhost:8080/security/get/subject?token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aXNpb244IiwiZXhwIjoxNTE5NDg3NjU0fQ.ZfmzjUz-slasIP93i4rtgMCnmbDrw4YkLgtxIJnDk9g
```
The result is similar to this:
```json
{
  "result": "vision8"
}
```
