#!/bin/bash

user=${1:-fred}
source get-user-access-token.sh $user

curl -X POST "http://localhost:8090/companies" \
     -H "Accept: application/json" \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer ${TOKEN}" \
     -d '{"name":"My Company", "country": "Sweden"}'