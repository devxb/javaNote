GET http://localhost:8080/new-quiz

HTTP 200
[Captures]
csrf_token : xpath "string(//input[@name='_csrf']/@value)"


#########################################################


POST http://localhost:8080/new-quiz
[FormParams]
name : Simpson
question0 : 16f897ab
question1: dd894cca
question2: 4edc1fdb
question3: 37b9eff3
question4: 0fec576c
_csrf: {{csrf_token}}

HTTP 302
[Captures]
detail_url : header "Location"

[Asserts]
header "Location" matches "/quiz/detail/[a-f0-9]{8}"

######################################################### 

GET {{detail_url}}

HTTP 200
