Exemplo de request

```bash
 print "GET http://echo-api.3scale.net/123456 HTTP/1.1\nAuthorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c\nHost: echo-api.3scale.net:80\nAccept: */*\n\n" | ncat --no-shutdown localhost 8080
 ```