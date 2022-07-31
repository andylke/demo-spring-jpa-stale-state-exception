# Demo Spring Data JPA

`curl "http://localhost:8080/users?title=Mr&firstName=Julio&lastName=Fletcher&gender=male"`

```
{"id":1,"gender":"male","title":"Mr","firstName":"Julio","lastName":"Fletcher","email":"julio.fletcher@example.com","username":"heavymeercat178","password":"football1","nationality":"IE"}
```


`curl "http://localhost:8080/users/full-name?id=1"`

```
{"title":"Mr","lastName":"Fletcher","firstName":"Julio"}
```
