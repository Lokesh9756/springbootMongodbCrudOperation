# springbootMongodbCrudOperation
Crud Operation project using spring boot and MongoDB.
In this project we have three operation given below.
1. Create : In this operation we can create new user using rest api call with payload.
 curl --location --request POST 'localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userName": "lp",
    "name": "Lokesh Pal",
    "dateOfBirth": "3 jan 1997"
}'

2. Update: In this operation we update user on basis of usrname.
curl --location -g --request PUT 'localhost:8080/users/{userName}' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userName": "lp",
    "name": "Lokesh",
    "dateOfBirth": "3 jan 2000"
}'

3. Get: In this operation we will get users sorted on date of birth.
curl --location --request GET 'localhost:8080/users' \
--data-raw ''
 

