#!/bin/bash

user=${1:-lars}
source get-user-access-token.sh $user

curl -X PUT "http://localhost:8090/companies/2" \
     -H "Accept: application/json" \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer ${TOKEN}" \
     -d '{"name":"Bar AG & Co KG"}'