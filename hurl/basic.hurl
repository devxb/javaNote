GET http://localhost:8080

HTTP 200
[Asserts]
xpath "string(//head/title)" == "Welcome to Quiz!"
xpath "//button" count == 2
xpath "string((//button)[1])" contains "Play"
xpath "string((//button)[2])" contains "Create"

header "Content-type" == "text/html;charset=UTF-8"
header "Set-Cookie" startsWith "JSESSIONID="

cookie "JSESSIONID" exists
cookie "JSESSIONID[HttpOnly]" exists

###############################################################

GET http://localhost:8080/not-found

HTTP 404
[Asserts]
header "Content-type" == "text/html;charset=UTF-8"

xpath "string(//h1)" == "Error 404, Page not Found!"

###############################################################

GET http://localhost:8080/api/health

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.status" == "RUNNING"
jsonpath "$.healthy" == true
jsonpath "$.operationId" exists

###############################################################

GET http://localhost:8080/api/questions
[Options]
very-verbose: true

[QueryStringParams]
offset : 0
size : 20
sort : oldest

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$" count == 20
jsonpath "$[0].id" == "c0d80047"
jsonpath "$[0].created" == "1995-12-17T12:24:00+09:00"
jsonpath "$[0].title" == "What is a pennyroyal?"
jsonpath "$[0].stars" == 4256
