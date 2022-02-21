# mia-collab-app


Ejemplo de request

POST  new request

curl --location --request POST 'localhost:8080/mia/blinded-experiment/request' --header 'Content-Type: application/json' --data-raw '{
    "owner_id": "123abc",
    "associated_collab_id": "1"
}'


GET request by owner

curl --location --request GET 'localhost:8080/mia/blinded-experiment/request?owner.id=123abc'
