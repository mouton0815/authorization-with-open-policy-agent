#!/bin/bash

user=${1:-inge}
source get-user-access-token.sh $user

curl -X DELETE "http://localhost:8090/companies/2" \
     -H "Authorization: Bearer ${TOKEN}"