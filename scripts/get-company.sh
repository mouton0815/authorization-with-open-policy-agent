#!/bin/bash

user=${1:-inge}
source get-user-access-token.sh $user

curl "http://localhost:8090/companies/2" \
     -H "Accept: application/json" \
     -H "Authorization: Bearer ${TOKEN}"